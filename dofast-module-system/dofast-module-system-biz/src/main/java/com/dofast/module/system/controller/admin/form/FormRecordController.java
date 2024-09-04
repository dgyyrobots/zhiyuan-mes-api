package com.dofast.module.system.controller.admin.form;

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

import com.dofast.module.system.controller.admin.form.vo.*;
import com.dofast.module.system.dal.dataobject.form.FormRecordDO;
import com.dofast.module.system.convert.form.FormRecordConvert;
import com.dofast.module.system.service.form.FormRecordService;

@Tag(name = "管理后台 - 表单历史")
@RestController
@RequestMapping("/system/form-record")
@Validated
public class FormRecordController {

    @Resource
    private FormRecordService formRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建表单历史")
    @PreAuthorize("@ss.hasPermission('system:form-record:create')")
    public CommonResult<Long> createFormRecord(@Valid @RequestBody FormRecordCreateReqVO createReqVO) {
        return success(formRecordService.createFormRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新表单历史")
    @PreAuthorize("@ss.hasPermission('system:form-record:update')")
    public CommonResult<Boolean> updateFormRecord(@Valid @RequestBody FormRecordUpdateReqVO updateReqVO) {
        formRecordService.updateFormRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除表单历史")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:form-record:delete')")
    public CommonResult<Boolean> deleteFormRecord(@RequestParam("id") Long id) {
        formRecordService.deleteFormRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得表单历史")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:form-record:query')")
    public CommonResult<FormRecordRespVO> getFormRecord(@RequestParam("id") Long id) {
        FormRecordDO formRecord = formRecordService.getFormRecord(id);
        return success(FormRecordConvert.INSTANCE.convert(formRecord));
    }

    @GetMapping("/list")
    @Operation(summary = "获得表单历史列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('system:form-record:query')")
    public CommonResult<List<FormRecordRespVO>> getFormRecordList(@RequestParam("ids") Collection<Long> ids) {
        List<FormRecordDO> list = formRecordService.getFormRecordList(ids);
        return success(FormRecordConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得表单历史分页")
    @PreAuthorize("@ss.hasPermission('system:form-record:query')")
    public CommonResult<PageResult<FormRecordRespVO>> getFormRecordPage(@Valid FormRecordPageReqVO pageVO) {
        PageResult<FormRecordDO> pageResult = formRecordService.getFormRecordPage(pageVO);
        return success(FormRecordConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出表单历史 Excel")
    @PreAuthorize("@ss.hasPermission('system:form-record:export')")
    @OperateLog(type = EXPORT)
    public void exportFormRecordExcel(@Valid FormRecordExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<FormRecordDO> list = formRecordService.getFormRecordList(exportReqVO);
        // 导出 Excel
        List<FormRecordExcelVO> datas = FormRecordConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "表单历史.xls", "数据", FormRecordExcelVO.class, datas);
    }

}
