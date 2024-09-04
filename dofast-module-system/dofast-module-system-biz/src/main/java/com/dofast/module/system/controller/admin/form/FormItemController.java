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
import com.dofast.module.system.dal.dataobject.form.FormItemDO;
import com.dofast.module.system.convert.form.FormItemConvert;
import com.dofast.module.system.service.form.FormItemService;

@Tag(name = "管理后台 - 字段")
@RestController
@RequestMapping("/system/form-item")
@Validated
public class FormItemController {

    @Resource
    private FormItemService formItemService;

    @PostMapping("/create")
    @Operation(summary = "创建字段")
    @PreAuthorize("@ss.hasPermission('system:form-item:create')")
    public CommonResult<Integer> createFormItem(@Valid @RequestBody FormItemCreateReqVO createReqVO) {
        return success(formItemService.createFormItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新字段")
    @PreAuthorize("@ss.hasPermission('system:form-item:update')")
    public CommonResult<Boolean> updateFormItem(@Valid @RequestBody FormItemUpdateReqVO updateReqVO) {
        formItemService.updateFormItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除字段")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:form-item:delete')")
    public CommonResult<Boolean> deleteFormItem(@RequestParam("id") Integer id) {
        formItemService.deleteFormItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得字段")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:form-item:query')")
    public CommonResult<FormItemRespVO> getFormItem(@RequestParam("id") Integer id) {
        FormItemDO formItem = formItemService.getFormItem(id);
        return success(FormItemConvert.INSTANCE.convert(formItem));
    }

    @GetMapping("/list")
    @Operation(summary = "获得字段列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('system:form-item:query')")
    public CommonResult<List<FormItemRespVO>> getFormItemList(@RequestParam(name = "ids", required = false) Collection<Integer> ids, @RequestParam(name = "formId", required = false) Long formId) {
        List<FormItemDO> list = formItemService.getFormItemList(ids, formId);
        return success(FormItemConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得字段分页")
    @PreAuthorize("@ss.hasPermission('system:form-item:query')")
    public CommonResult<PageResult<FormItemRespVO>> getFormItemPage(@Valid FormItemPageReqVO pageVO) {
        PageResult<FormItemDO> pageResult = formItemService.getFormItemPage(pageVO);
        return success(FormItemConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出字段 Excel")
    @PreAuthorize("@ss.hasPermission('system:form-item:export')")
    @OperateLog(type = EXPORT)
    public void exportFormItemExcel(@Valid FormItemExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<FormItemDO> list = formItemService.getFormItemList(exportReqVO);
        // 导出 Excel
        List<FormItemExcelVO> datas = FormItemConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "字段.xls", "数据", FormItemExcelVO.class, datas);
    }

}
