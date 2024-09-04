package com.dofast.module.hr.controller.admin.employeefile;

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

import com.dofast.module.hr.controller.admin.employeefile.vo.*;
import com.dofast.module.hr.dal.dataobject.employeefile.EmployeeFileDO;
import com.dofast.module.hr.convert.employeefile.EmployeeFileConvert;
import com.dofast.module.hr.service.employeefile.EmployeeFileService;

@Tag(name = "管理后台 - 员工信息文件")
@RestController
@RequestMapping("/hr/employee-file")
@Validated
public class EmployeeFileController {

    @Resource
    private EmployeeFileService employeeFileService;

    @PostMapping("/create")
    @Operation(summary = "创建员工信息文件")
    @PreAuthorize("@ss.hasPermission('hr:employee-file:create')")
    public CommonResult<Long> createEmployeeFile(@Valid @RequestBody EmployeeFileCreateReqVO createReqVO) {
        return success(employeeFileService.createEmployeeFile(createReqVO));
    }

    @PostMapping("/create-my")
    @Operation(summary = "创建员工个人信息文件")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-file:create')")
    public CommonResult<Long> createEmployeeFileMy(@Valid @RequestBody EmployeeFileCreateReqVO createReqVO) {
        return success(employeeFileService.createEmployeeFileMy(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新员工信息文件")
    @PreAuthorize("@ss.hasPermission('hr:employee-file:update')")
    public CommonResult<Boolean> updateEmployeeFile(@Valid @RequestBody EmployeeFileUpdateReqVO updateReqVO) {
        employeeFileService.updateEmployeeFile(updateReqVO);
        return success(true);
    }

    @PutMapping("/update-my")
    @Operation(summary = "更新员工个人信息文件")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-file:update')")
    public CommonResult<Boolean> updateEmployeeFileMy(@Valid @RequestBody EmployeeFileUpdateReqVO updateReqVO) {
        employeeFileService.updateEmployeeFileMy(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除员工信息文件")
    @PreAuthorize("@ss.hasPermission('hr:employee-file:delete')")
    public CommonResult<Boolean> deleteEmployeeFile() {
        employeeFileService.deleteEmployeeFile(getLoginUserId());
        return success(true);
    }

    @DeleteMapping("/delete-my")
    @Operation(summary = "删除员工个人信息文件")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-file:delete')")
    public CommonResult<Boolean> deleteEmployeeFileMy() {
        employeeFileService.deleteEmployeeFileMy(getLoginUserId());
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得员工信息文件")
    @PreAuthorize("@ss.hasPermission('hr:employee-file:query')")
    public CommonResult<EmployeeFileRespVO> getEmployeeFile() {
        EmployeeFileDO employeeFile = employeeFileService.getEmployeeFile(getLoginUserId());
        return success(EmployeeFileConvert.INSTANCE.convert(employeeFile));
    }


    @GetMapping("/get-my")
    @Operation(summary = "获得员工个人信息文件")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-file:query')")
    public CommonResult<EmployeeFileRespVO> getEmployeeFileMy() {
        EmployeeFileDO employeeFile = employeeFileService.getEmployeeFileMy(getLoginUserId());
        return success(EmployeeFileConvert.INSTANCE.convert(employeeFile));
    }

    @GetMapping("/get/all")
    @Operation(summary = "审核员获得员工信息文件")
    @PreAuthorize("@ss.hasPermission('hr:employee-file:query')")
    public CommonResult<List<EmployeeFileRespVO>> getEmployeeFileAll() {
        List<EmployeeFileDO> list = employeeFileService.getEmployeeFileList(new EmployeeFileExportReqVO());
        return success(EmployeeFileConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list")
    @Operation(summary = "获得员工信息文件列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hr:employee-file:query')")
    public CommonResult<List<EmployeeFileRespVO>> getEmployeeFileList(@RequestParam("ids") Collection<Long> ids) {
        List<EmployeeFileDO> list = employeeFileService.getEmployeeFileList(ids);
        return success(EmployeeFileConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得员工信息文件分页")
    @PreAuthorize("@ss.hasPermission('hr:employee-file:query')")
    public CommonResult<PageResult<EmployeeFileRespVO>> getEmployeeFilePage(@Valid EmployeeFilePageReqVO pageVO) {
        PageResult<EmployeeFileDO> pageResult = employeeFileService.getEmployeeFilePage(pageVO);
        return success(EmployeeFileConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出员工信息文件 Excel")
    @PreAuthorize("@ss.hasPermission('hr:employee-file:export')")
    @OperateLog(type = EXPORT)
    public void exportEmployeeFileExcel(@Valid EmployeeFileExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EmployeeFileDO> list = employeeFileService.getEmployeeFileList(exportReqVO);
        // 导出 Excel
        List<EmployeeFileExcelVO> datas = EmployeeFileConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "员工信息文件.xls", "数据", EmployeeFileExcelVO.class, datas);
    }

}
