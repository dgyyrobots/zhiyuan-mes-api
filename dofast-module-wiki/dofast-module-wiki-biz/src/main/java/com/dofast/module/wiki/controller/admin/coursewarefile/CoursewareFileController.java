package com.dofast.module.wiki.controller.admin.coursewarefile;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.wiki.controller.admin.coursewarefile.vo.*;
import com.dofast.module.wiki.dal.dataobject.coursewarefile.CoursewareFileDO;
import com.dofast.module.wiki.convert.coursewarefile.CoursewareFileConvert;
import com.dofast.module.wiki.service.coursewarefile.CoursewareFileService;

@Tag(name = "管理后台 - 课件文件的保存地址")
@RestController
@RequestMapping("/wiki/courseware-file")
@Validated
public class CoursewareFileController {

    @Resource
    private CoursewareFileService coursewareFileService;

    @PostMapping("/create")
    @Operation(summary = "创建课件文件的保存地址")
    @PreAuthorize("@ss.hasPermission('wiki:courseware-file:create')")
    public CommonResult<Long> createCoursewareFile(@Valid @RequestBody CoursewareFileCreateReqVO createReqVO) {
        return success(coursewareFileService.createCoursewareFile(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新课件文件的保存地址")
    @PreAuthorize("@ss.hasPermission('wiki:courseware-file:update')")
    public CommonResult<Boolean> updateCoursewareFile(@Valid @RequestBody CoursewareFileUpdateReqVO updateReqVO) {
        coursewareFileService.updateCoursewareFile(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除课件文件的保存地址")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wiki:courseware-file:delete')")
    public CommonResult<Boolean> deleteCoursewareFile(@RequestParam("id") Long id) {
        coursewareFileService.deleteCoursewareFile(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得课件文件的保存地址")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wiki:courseware-file:query')")
    public CommonResult<CoursewareFileRespVO> getCoursewareFile(@RequestParam("id") Long id) {
        CoursewareFileDO coursewareFile = coursewareFileService.getCoursewareFile(id);
        return success(CoursewareFileConvert.INSTANCE.convert(coursewareFile));
    }

    @GetMapping("/list")
    @Operation(summary = "获得课件文件的保存地址列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wiki:courseware-file:query')")
    public CommonResult<List<CoursewareFileRespVO>> getCoursewareFileList(@RequestParam("ids") Collection<Long> ids) {
        List<CoursewareFileDO> list = coursewareFileService.getCoursewareFileList(ids);
        return success(CoursewareFileConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得课件文件的保存地址分页")
    @PreAuthorize("@ss.hasPermission('wiki:courseware-file:query')")
    public CommonResult<PageResult<CoursewareFileRespVO>> getCoursewareFilePage(@Valid CoursewareFilePageReqVO pageVO) {
        PageResult<CoursewareFileDO> pageResult = coursewareFileService.getCoursewareFilePage(pageVO);
        return success(CoursewareFileConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出课件文件的保存地址 Excel")
    @PreAuthorize("@ss.hasPermission('wiki:courseware-file:export')")
    @OperateLog(type = EXPORT)
    public void exportCoursewareFileExcel(@Valid CoursewareFileExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CoursewareFileDO> list = coursewareFileService.getCoursewareFileList(exportReqVO);
        // 导出 Excel
        List<CoursewareFileExcelVO> datas = CoursewareFileConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "课件文件的保存地址.xls", "数据", CoursewareFileExcelVO.class, datas);
    }

}
