package com.dofast.module.hr.controller.admin.employeefamiles;

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

import com.dofast.module.hr.controller.admin.employeefamiles.vo.*;
import com.dofast.module.hr.dal.dataobject.employeefamiles.EmployeeFamilesDO;
import com.dofast.module.hr.convert.employeefamiles.EmployeeFamilesConvert;
import com.dofast.module.hr.service.employeefamiles.EmployeeFamilesService;

@Tag(name = "管理后台 - 员工家庭成员")
@RestController
@RequestMapping("/hr/employee-familes")
@Validated
public class EmployeeFamilesController {

    @Resource
    private EmployeeFamilesService employeeFamilesService;

    @PostMapping("/create")
    @Operation(summary = "创建员工家庭成员")
    @PreAuthorize("@ss.hasPermission('hr:employee-familes:create')")
    public CommonResult<Long> createEmployeeFamiles(@Valid @RequestBody EmployeeFamilesCreateReqVO createReqVO) {
        return success(employeeFamilesService.createEmployeeFamiles(createReqVO));
    }

    @PostMapping("/create-my")
    @Operation(summary = "创建员工个人家庭成员")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-familes:create')")
    public CommonResult<Long> createEmployeeFamilesMy(@Valid @RequestBody EmployeeFamilesCreateReqVO createReqVO) {
        return success(employeeFamilesService.createEmployeeFamilesMy(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新员工家庭成员")
    @PreAuthorize("@ss.hasPermission('hr:employee-familes:update')")
    public CommonResult<Boolean> updateEmployeeFamiles(@Valid @RequestBody EmployeeFamilesUpdateReqVO updateReqVO) {
        employeeFamilesService.updateEmployeeFamiles(updateReqVO);
        return success(true);
    }


    @PutMapping("/update-my")
    @Operation(summary = "更新员工个人家庭成员")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-familes:update')")
    public CommonResult<Boolean> updateEmployeeFamilesMy(@Valid @RequestBody EmployeeFamilesUpdateReqVO updateReqVO) {
        employeeFamilesService.updateEmployeeFamilesMy(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除员工家庭成员")
    @PreAuthorize("@ss.hasPermission('hr:employee-familes:delete')")
    public CommonResult<Boolean> deleteEmployeeFamiles() {
        employeeFamilesService.deleteEmployeeFamiles(getLoginUserId());
        return success(true);
    }

    @DeleteMapping("/delete-my")
    @Operation(summary = "删除员工个人家庭成员")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-familes:delete')")
    public CommonResult<Boolean> deleteEmployeeFamilesMy() {
        employeeFamilesService.deleteEmployeeFamilesMy(getLoginUserId());
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得员工家庭成员")
    @PreAuthorize("@ss.hasPermission('hr:employee-familes:query')")
    public CommonResult<EmployeeFamilesRespVO> getEmployeeFamiles() {
        EmployeeFamilesDO employeeFamiles = employeeFamilesService.getEmployeeFamiles(getLoginUserId());
        return success(EmployeeFamilesConvert.INSTANCE.convert(employeeFamiles));
    }



    @GetMapping("/get-my")
    @Operation(summary = "获得员工个人家庭成员")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-familes:query')")
    public CommonResult<List<EmployeeFamilesRespVO>> getEmployeeFamilesMy() {
        List<EmployeeFamilesDO> employeeFamiles = employeeFamilesService.getEmployeeFamilesMy(getLoginUserId());
        return success(EmployeeFamilesConvert.INSTANCE.convertList(employeeFamiles));
    }

    @GetMapping("/get/all")
    @Operation(summary = "审核员获得员工家庭成员")
    @PreAuthorize("@ss.hasPermission('hr:employee-familes:query')")
    public CommonResult<List<EmployeeFamilesRespVO>> getEmployeeFamilesAll() {
        List<EmployeeFamilesDO> list = employeeFamilesService.getEmployeeFamilesList(new EmployeeFamilesExportReqVO());
        return success(EmployeeFamilesConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list")
    @Operation(summary = "获得员工家庭成员列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hr:employee-familes:query')")
    public CommonResult<List<EmployeeFamilesRespVO>> getEmployeeFamilesList(@RequestParam("ids") Collection<Integer> ids) {
        List<EmployeeFamilesDO> list = employeeFamilesService.getEmployeeFamilesList(ids);
        return success(EmployeeFamilesConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得员工家庭成员分页")
    @PreAuthorize("@ss.hasPermission('hr:employee-familes:query')")
    public CommonResult<PageResult<EmployeeFamilesRespVO>> getEmployeeFamilesPage(@Valid EmployeeFamilesPageReqVO pageVO) {
        PageResult<EmployeeFamilesDO> pageResult = employeeFamilesService.getEmployeeFamilesPage(pageVO);
        return success(EmployeeFamilesConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出员工家庭成员 Excel")
    @PreAuthorize("@ss.hasPermission('hr:employee-familes:export')")
    @OperateLog(type = EXPORT)
    public void exportEmployeeFamilesExcel(@Valid EmployeeFamilesExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EmployeeFamilesDO> list = employeeFamilesService.getEmployeeFamilesList(exportReqVO);
        // 导出 Excel
        List<EmployeeFamilesExcelVO> datas = EmployeeFamilesConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "员工家庭成员.xls", "数据", EmployeeFamilesExcelVO.class, datas);
    }

}
