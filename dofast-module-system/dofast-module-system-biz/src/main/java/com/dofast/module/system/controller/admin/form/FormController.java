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
import com.dofast.module.system.dal.dataobject.form.FormDO;
import com.dofast.module.system.convert.form.FormConvert;
import com.dofast.module.system.service.form.FormService;

@Tag(name = "管理后台 - 系统表单")
@RestController
@RequestMapping("/system/form")
@Validated
public class FormController {

    @Resource
    private FormService formService;

    @PostMapping("/create")
    @Operation(summary = "创建系统表单")
    @PreAuthorize("@ss.hasPermission('system:form:create')")
    public CommonResult<Integer> createForm(@Valid @RequestBody FormCreateReqVO createReqVO) {
        return success(formService.createForm(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新系统表单")
    @PreAuthorize("@ss.hasPermission('system:form:update')")
    public CommonResult<Boolean> updateForm(@Valid @RequestBody FormUpdateReqVO updateReqVO) {
        formService.updateForm(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除系统表单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:form:delete')")
    public CommonResult<Boolean> deleteForm(@RequestParam("id") Integer id) {
        formService.deleteForm(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得系统表单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:form:query')")
    public CommonResult<FormRespVO> getForm(@RequestParam("id") Integer id) {
        FormDO form = formService.getForm(id);
        return success(FormConvert.INSTANCE.convert(form));
    }

    @GetMapping("/list")
    @Operation(summary = "获得系统表单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('system:form:query')")
    public CommonResult<List<FormRespVO>> getFormList(@RequestParam("ids") Collection<Integer> ids) {
        List<FormDO> list = formService.getFormList(ids);
        return success(FormConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得系统表单分页")
    @PreAuthorize("@ss.hasPermission('system:form:query')")
    public CommonResult<PageResult<FormRespVO>> getFormPage(@Valid FormPageReqVO pageVO) {
        PageResult<FormDO> pageResult = formService.getFormPage(pageVO);
        return success(FormConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/list-simple")
    @Operation(summary = "获得系统表单简单列表")
    @PreAuthorize("@ss.hasPermission('system:form:query')")
    public CommonResult<List<FormSimpleRespVO>> getFormPage(@Valid FormExportReqVO pageVO) {
        List<FormDO> listResult = formService.getFormList(pageVO);
        return success(FormConvert.INSTANCE.convertList3(listResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出系统表单 Excel")
    @PreAuthorize("@ss.hasPermission('system:form:export')")
    @OperateLog(type = EXPORT)
    public void exportFormExcel(@Valid FormExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<FormDO> list = formService.getFormList(exportReqVO);
        // 导出 Excel
        List<FormExcelVO> datas = FormConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "系统表单.xls", "数据", FormExcelVO.class, datas);
    }

}
