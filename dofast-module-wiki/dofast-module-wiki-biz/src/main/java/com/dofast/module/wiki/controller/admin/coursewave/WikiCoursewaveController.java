package com.dofast.module.wiki.controller.admin.coursewave;

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

import com.dofast.module.wiki.controller.admin.coursewave.vo.*;
import com.dofast.module.wiki.dal.dataobject.coursewave.WikiCoursewaveDO;
import com.dofast.module.wiki.convert.coursewave.WikiCoursewaveConvert;
import com.dofast.module.wiki.service.coursewave.WikiCoursewaveService;

@Tag(name = "管理后台 - 首页知识列表的信息	")
@RestController
@RequestMapping("/wiki/coursewave")
@Validated
public class WikiCoursewaveController {

    @Resource
    private WikiCoursewaveService coursewaveService;

    @PostMapping("/create")
    @Operation(summary = "创建首页知识列表的信息	")
    @PreAuthorize("@ss.hasPermission('wiki:coursewave:create')")
    public CommonResult<Long> createCoursewave(@Valid @RequestBody WikiCoursewaveCreateReqVO createReqVO) {
        return success(coursewaveService.createCoursewave(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新首页知识列表的信息	")
    @PreAuthorize("@ss.hasPermission('wiki:coursewave:update')")
    public CommonResult<Boolean> updateCoursewave(@Valid @RequestBody WikiCoursewaveUpdateReqVO updateReqVO) {
        coursewaveService.updateCoursewave(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除首页知识列表的信息	")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wiki:coursewave:delete')")
    public CommonResult<Boolean> deleteCoursewave(@RequestParam("id") Long id) {
        coursewaveService.deleteCoursewave(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得首页知识列表的信息	")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wiki:coursewave:query')")
    public CommonResult<WikiCoursewaveRespVO> getCoursewave(@RequestParam("id") Long id) {
        WikiCoursewaveDO coursewave = coursewaveService.getCoursewave(id);
        return success(WikiCoursewaveConvert.INSTANCE.convert(coursewave));
    }

    @GetMapping("/list")
    @Operation(summary = "获得首页知识列表的信息	列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wiki:coursewave:query')")
    public CommonResult<List<WikiCoursewaveRespVO>> getCoursewaveList(@RequestParam("ids") Collection<Long> ids) {
        List<WikiCoursewaveDO> list = coursewaveService.getCoursewaveList(ids);
        return success(WikiCoursewaveConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得首页知识列表的信息	分页")
    @PreAuthorize("@ss.hasPermission('wiki:coursewave:query')")
    public CommonResult<PageResult<WikiCoursewaveRespVO>> getCoursewavePage(@Valid WikiCoursewavePageReqVO pageVO) {
        PageResult<WikiCoursewaveDO> pageResult = coursewaveService.getCoursewavePage(pageVO);
        return success(WikiCoursewaveConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出首页知识列表的信息	 Excel")
    @PreAuthorize("@ss.hasPermission('wiki:coursewave:export')")
    @OperateLog(type = EXPORT)
    public void exportCoursewaveExcel(@Valid WikiCoursewaveExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<WikiCoursewaveDO> list = coursewaveService.getCoursewaveList(exportReqVO);
        // 导出 Excel
        List<WikiCoursewaveExcelVO> datas = WikiCoursewaveConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "首页知识列表的信息	.xls", "数据", WikiCoursewaveExcelVO.class, datas);
    }

}
