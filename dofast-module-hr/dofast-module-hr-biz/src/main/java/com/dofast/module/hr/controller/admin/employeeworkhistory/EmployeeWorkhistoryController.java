package com.dofast.module.hr.controller.admin.employeeworkhistory;

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

import com.dofast.module.hr.controller.admin.employeeworkhistory.vo.*;
import com.dofast.module.hr.dal.dataobject.employeeworkhistory.EmployeeWorkhistoryDO;
import com.dofast.module.hr.convert.employeeworkhistory.EmployeeWorkhistoryConvert;
import com.dofast.module.hr.service.employeeworkhistory.EmployeeWorkhistoryService;

@Tag(name = "管理后台 - 员工工作经历")
@RestController
@RequestMapping("/hr/employee-workhistory")
@Validated
public class EmployeeWorkhistoryController {

    @Resource
    private EmployeeWorkhistoryService employeeWorkhistoryService;

    @PostMapping("/create")
    @Operation(summary = "创建员工工作经历")
    @PreAuthorize("@ss.hasPermission('hr:employee-workhistory:create')")
    public CommonResult<Long> createEmployeeWorkhistory(@Valid @RequestBody EmployeeWorkhistoryCreateReqVO createReqVO) {
        return success(employeeWorkhistoryService.createEmployeeWorkhistory(createReqVO));
    }

    @PostMapping("/create-my")
    @Operation(summary = "创建员工个人工作经历")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-workhistory:create')")
    public CommonResult<Long> createEmployeeWorkhistoryMy(@Valid @RequestBody EmployeeWorkhistoryCreateReqVO createReqVO) {
        return success(employeeWorkhistoryService.createEmployeeWorkhistoryMy(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新员工工作经历")
    @PreAuthorize("@ss.hasPermission('hr:employee-workhistory:update')")
    public CommonResult<Boolean> updateEmployeeWorkhistory(@Valid @RequestBody EmployeeWorkhistoryUpdateReqVO updateReqVO) {
        employeeWorkhistoryService.updateEmployeeWorkhistory(updateReqVO);
        return success(true);
    }

    @PutMapping("/update-my")
    @Operation(summary = "更新员工个人工作经历")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-workhistory:update')")
    public CommonResult<Boolean> updateEmployeeWorkhistoryMy(@Valid @RequestBody EmployeeWorkhistoryUpdateReqVO updateReqVO) {
        employeeWorkhistoryService.updateEmployeeWorkhistoryMy(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除员工工作经历")
    @PreAuthorize("@ss.hasPermission('hr:employee-workhistory:delete')")
    public CommonResult<Boolean> deleteEmployeeWorkhistory() {
        employeeWorkhistoryService.deleteEmployeeWorkhistory(getLoginUserId());
        return success(true);
    }

    @DeleteMapping("/delete-my")
    @Operation(summary = "删除员工个人工作经历")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-workhistory:delete')")
    public CommonResult<Boolean> deleteEmployeeWorkhistoryMy() {
        employeeWorkhistoryService.deleteEmployeeWorkhistoryMy(getLoginUserId());
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得员工工作经历")
    @PreAuthorize("@ss.hasPermission('hr:employee-workhistory:query')")
    public CommonResult<EmployeeWorkhistoryRespVO> getEmployeeWorkhistory() {
        EmployeeWorkhistoryDO employeeWorkhistory = employeeWorkhistoryService.getEmployeeWorkhistory(getLoginUserId());
        return success(EmployeeWorkhistoryConvert.INSTANCE.convert(employeeWorkhistory));
    }


    @GetMapping("/get-my")
    @Operation(summary = "获得员工个人工作经历")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-workhistory:query')")
    public CommonResult<List<EmployeeWorkhistoryRespVO>> getEmployeeWorkhistoryMy() {
        List<EmployeeWorkhistoryDO> employeeWorkhistory = employeeWorkhistoryService.getEmployeeWorkhistoryMy(getLoginUserId());
        return success(EmployeeWorkhistoryConvert.INSTANCE.convertList(employeeWorkhistory));
    }

    @GetMapping("/get/all")
    @Operation(summary = "审核员获得员工工作经历")
    @PreAuthorize("@ss.hasPermission('hr:employee-workhistory:query')")
    public CommonResult<List<EmployeeWorkhistoryRespVO>> getEmployeeWorkhistoryAll() {
        List<EmployeeWorkhistoryDO> list = employeeWorkhistoryService.getEmployeeWorkhistoryList(new EmployeeWorkhistoryExportReqVO());
        return success(EmployeeWorkhistoryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list")
    @Operation(summary = "获得员工工作经历列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hr:employee-workhistory:query')")
    public CommonResult<List<EmployeeWorkhistoryRespVO>> getEmployeeWorkhistoryList(@RequestParam("ids") Collection<Long> ids) {
        List<EmployeeWorkhistoryDO> list = employeeWorkhistoryService.getEmployeeWorkhistoryList(ids);
        return success(EmployeeWorkhistoryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得员工工作经历分页")
    @PreAuthorize("@ss.hasPermission('hr:employee-workhistory:query')")
    public CommonResult<PageResult<EmployeeWorkhistoryRespVO>> getEmployeeWorkhistoryPage(@Valid EmployeeWorkhistoryPageReqVO pageVO) {
        PageResult<EmployeeWorkhistoryDO> pageResult = employeeWorkhistoryService.getEmployeeWorkhistoryPage(pageVO);
        return success(EmployeeWorkhistoryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出员工工作经历 Excel")
    @PreAuthorize("@ss.hasPermission('hr:employee-workhistory:export')")
    @OperateLog(type = EXPORT)
    public void exportEmployeeWorkhistoryExcel(@Valid EmployeeWorkhistoryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EmployeeWorkhistoryDO> list = employeeWorkhistoryService.getEmployeeWorkhistoryList(exportReqVO);
        // 导出 Excel
        List<EmployeeWorkhistoryExcelVO> datas = EmployeeWorkhistoryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "员工工作经历.xls", "数据", EmployeeWorkhistoryExcelVO.class, datas);
    }

}
