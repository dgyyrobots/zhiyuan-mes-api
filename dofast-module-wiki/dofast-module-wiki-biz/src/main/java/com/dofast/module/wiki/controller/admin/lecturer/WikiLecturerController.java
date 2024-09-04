package com.dofast.module.wiki.controller.admin.lecturer;

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

import com.dofast.module.wiki.controller.admin.lecturer.vo.*;
import com.dofast.module.wiki.dal.dataobject.lecturer.WikiLecturerDO;
import com.dofast.module.wiki.convert.lecturer.WikiLecturerConvert;
import com.dofast.module.wiki.service.lecturer.WikiLecturerService;

@Tag(name = "管理后台 - 讲师的信息	")
@RestController
@RequestMapping("/wiki/lecturer")
@Validated
public class WikiLecturerController {

    @Resource
    private WikiLecturerService lecturerService;

    @PostMapping("/create")
    @Operation(summary = "创建讲师的信息	")
    @PreAuthorize("@ss.hasPermission('wiki:lecturer:create')")
    public CommonResult<Long> createLecturer(@Valid @RequestBody WikiLecturerCreateReqVO createReqVO) {
        return success(lecturerService.createLecturer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新讲师的信息	")
    @PreAuthorize("@ss.hasPermission('wiki:lecturer:update')")
    public CommonResult<Boolean> updateLecturer(@Valid @RequestBody WikiLecturerUpdateReqVO updateReqVO) {
        lecturerService.updateLecturer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除讲师的信息	")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wiki:lecturer:delete')")
    public CommonResult<Boolean> deleteLecturer(@RequestParam("id") Long id) {
        lecturerService.deleteLecturer(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得讲师的信息	")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wiki:lecturer:query')")
    public CommonResult<WikiLecturerRespVO> getLecturer(@RequestParam("id") Long id) {
        WikiLecturerDO lecturer = lecturerService.getLecturer(id);
        return success(WikiLecturerConvert.INSTANCE.convert(lecturer));
    }

    @GetMapping("/list")
    @Operation(summary = "获得讲师的信息	列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wiki:lecturer:query')")
    public CommonResult<List<WikiLecturerRespVO>> getLecturerList(@RequestParam("ids") Collection<Long> ids) {
        List<WikiLecturerDO> list = lecturerService.getLecturerList(ids);
        return success(WikiLecturerConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得讲师的信息	分页")
    @PreAuthorize("@ss.hasPermission('wiki:lecturer:query')")
    public CommonResult<PageResult<WikiLecturerRespVO>> getLecturerPage(@Valid WikiLecturerPageReqVO pageVO) {
        PageResult<WikiLecturerDO> pageResult = lecturerService.getLecturerPage(pageVO);
        return success(WikiLecturerConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出讲师的信息	 Excel")
    @PreAuthorize("@ss.hasPermission('wiki:lecturer:export')")
    @OperateLog(type = EXPORT)
    public void exportLecturerExcel(@Valid WikiLecturerExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<WikiLecturerDO> list = lecturerService.getLecturerList(exportReqVO);
        // 导出 Excel
        List<WikiLecturerExcelVO> datas = WikiLecturerConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "讲师的信息	.xls", "数据", WikiLecturerExcelVO.class, datas);
    }

}
