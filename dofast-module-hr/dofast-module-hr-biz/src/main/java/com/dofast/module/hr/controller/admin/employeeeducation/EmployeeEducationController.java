package com.dofast.module.hr.controller.admin.employeeeducation;

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
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import com.dofast.module.hr.controller.admin.employeeeducation.vo.*;
import com.dofast.module.hr.dal.dataobject.employeeeducation.EmployeeEducationDO;
import com.dofast.module.hr.convert.employeeeducation.EmployeeEducationConvert;
import com.dofast.module.hr.service.employeeeducation.EmployeeEducationService;

@Tag(name = "管理后台 - 员工教育培训经历")
@RestController
@RequestMapping("/hr/employee-education")
@Validated
public class EmployeeEducationController {

    @Resource
    private EmployeeEducationService employeeEducationService;

    @PostMapping("/create")
    @Operation(summary = "创建员工教育培训经历")
    @PreAuthorize("@ss.hasPermission('hr:employee-education:create')")
    public CommonResult<Long> createEmployeeEducation(@Valid @RequestBody EmployeeEducationCreateReqVO createReqVO) {
        return success(employeeEducationService.createEmployeeEducation(createReqVO));
    }


    @PostMapping("/create-my")
    @Operation(summary = "创建员工个人教育培训经历")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-education:create')")
    public CommonResult<Long> createEmployeeEducationMy(@Valid @RequestBody EmployeeEducationCreateReqVO createReqVO) {
        return success(employeeEducationService.createEmployeeEducationMy(createReqVO));
    }


    @PutMapping("/update")
    @Operation(summary = "更新员工教育培训经历")
    @PreAuthorize("@ss.hasPermission('hr:employee-education:update')")
    public CommonResult<Boolean> updateEmployeeEducation(@Valid @RequestBody EmployeeEducationUpdateReqVO updateReqVO) {
        employeeEducationService.updateEmployeeEducation(updateReqVO);
        return success(true);
    }



    @PutMapping("/update-my")
    @Operation(summary = "更新员工个人教育培训经历")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-education:update')")
    public CommonResult<Boolean> updateEmployeeEducationMy(@Valid @RequestBody EmployeeEducationUpdateReqVO updateReqVO) {
        employeeEducationService.updateEmployeeEducation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除员工教育培训经历")
    @PreAuthorize("@ss.hasPermission('hr:employee-education:delete')")
    public CommonResult<Boolean> deleteEmployeeEducation() {
        employeeEducationService.deleteEmployeeEducation(getLoginUserId());
        return success(true);
    }

    @DeleteMapping("/delete-my")
    @Operation(summary = "删除员工个人教育培训经历")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-education:delete')")
    public CommonResult<Boolean> deleteEmployeeEducationMy() {
        employeeEducationService.deleteEmployeeEducation(getLoginUserId());
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得员工教育培训经历")
    @PreAuthorize("@ss.hasPermission('hr:employee-education:query')")
    public CommonResult<EmployeeEducationRespVO> getEmployeeEducation() {
        EmployeeEducationDO employeeEducation = employeeEducationService.getEmployeeEducation(getLoginUserId());
        return success(EmployeeEducationConvert.INSTANCE.convert(employeeEducation));
    }

    @GetMapping("/get-my")
    @Operation(summary = "获得员工个人教育培训经历")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-education:query')")
    public CommonResult<List<EmployeeEducationRespVO>> getEmployeeEducationMy() {
        List<EmployeeEducationDO> employeeEducation = employeeEducationService.getEmployeeEducationMy(getLoginUserId());
        return success(EmployeeEducationConvert.INSTANCE.convertList(employeeEducation));
    }

    @GetMapping("/get/all")
    @Operation(summary = "审核员获得员工教育培训经历")
    @PreAuthorize("@ss.hasPermission('hr:employee-education:query')")
    public CommonResult<List<EmployeeEducationRespVO>> getEmployeeEducationAll() {
        List<EmployeeEducationDO> list = employeeEducationService.getEmployeeEducationList(new EmployeeEducationExportReqVO());
        return success(EmployeeEducationConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list")
    @Operation(summary = "获得员工教育培训经历列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hr:employee-education:query')")
    public CommonResult<List<EmployeeEducationRespVO>> getEmployeeEducationList(@RequestParam("ids") Collection<Long> ids) {
        List<EmployeeEducationDO> list = employeeEducationService.getEmployeeEducationList(ids);
        return success(EmployeeEducationConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得员工教育培训经历分页")
    @PreAuthorize("@ss.hasPermission('hr:employee-education:query')")
    public CommonResult<PageResult<EmployeeEducationRespVO>> getEmployeeEducationPage(@Valid EmployeeEducationPageReqVO pageVO) {
        PageResult<EmployeeEducationDO> pageResult = employeeEducationService.getEmployeeEducationPage(pageVO);
        return success(EmployeeEducationConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出员工教育培训经历 Excel")
    @PreAuthorize("@ss.hasPermission('hr:employee-education:export')")
    @OperateLog(type = EXPORT)
    public void exportEmployeeEducationExcel(@Valid EmployeeEducationExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EmployeeEducationDO> list = employeeEducationService.getEmployeeEducationList(exportReqVO);
        // 导出 Excel
        List<EmployeeEducationExcelVO> datas = EmployeeEducationConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "员工教育培训经历.xls", "数据", EmployeeEducationExcelVO.class, datas);
    }

}
