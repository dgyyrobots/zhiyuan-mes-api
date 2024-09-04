package com.dofast.module.hr.controller.admin.employeebasic;

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

import com.dofast.module.hr.controller.admin.employeebasic.vo.*;
import com.dofast.module.hr.dal.dataobject.employeebasic.EmployeeBasicDO;
import com.dofast.module.hr.convert.employeebasic.EmployeeBasicConvert;
import com.dofast.module.hr.service.employeebasic.EmployeeBasicService;

@Tag(name = "管理后台 - 员工基本信息")
@RestController
@RequestMapping("/hr/employee-basic")
@Validated
public class EmployeeBasicController {

    @Resource
    private EmployeeBasicService employeeBasicService;

    @PostMapping("/create")
    @Operation(summary = "创建员工基本信息")
    @PreAuthorize("@ss.hasPermission('hr:employee-basic:create')")
    public CommonResult<Long> createEmployeeBasic(@Valid @RequestBody EmployeeBasicCreateReqVO createReqVO) {
        return success(employeeBasicService.createEmployeeBasic(createReqVO));
    }

    @PostMapping("/create-my")
    @Operation(summary = "创建员工个人基本信息")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-basic:create')")
    public CommonResult<Long> createEmployeeBasicMy(@Valid @RequestBody EmployeeBasicCreateReqVO createReqVO) {
        return success(employeeBasicService.createEmployeeBasicMy(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新员工基本信息")
    @PreAuthorize("@ss.hasPermission('hr:employee-basic:update')")
    public CommonResult<Boolean> updateEmployeeBasic(@Valid @RequestBody EmployeeBasicUpdateReqVO updateReqVO) {
        employeeBasicService.updateEmployeeBasic(updateReqVO);
        return success(true);
    }

    @PutMapping("/update-my")
    @Operation(summary = "更新员工个人基本信息")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-basic:update')")
    public CommonResult<Boolean> updateEmployeeBasicMy(@Valid @RequestBody EmployeeBasicUpdateReqVO updateReqVO) {
        employeeBasicService.updateEmployeeBasicMy(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除员工基本信息")
    @PreAuthorize("@ss.hasPermission('hr:employee-basic:delete')")
    public CommonResult<Boolean> deleteEmployeeBasic() {
        employeeBasicService.deleteEmployeeBasic(getLoginUserId());
        return success(true);
    }


    @DeleteMapping("/delete-my")
    @Operation(summary = "删除员工个人基本信息")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-basic:delete')")
    public CommonResult<Boolean> deleteEmployeeBasicMy() {
        employeeBasicService.deleteEmployeeBasicMy(getLoginUserId());
        return success(true);
    }


    @GetMapping("/get")
    @Operation(summary = "获得员工基本信息")
    @PreAuthorize("@ss.hasPermission('hr:employee-basic:query')")
    public CommonResult<EmployeeBasicRespVO> getEmployeeBasic() {
        EmployeeBasicDO employeeBasic = employeeBasicService.getEmployeeBasic(getLoginUserId());
        return success(EmployeeBasicConvert.INSTANCE.convert(employeeBasic));
    }


    @GetMapping("/get-my")
    @Operation(summary = "获得员工个人基本信息")
    @PreAuthorize("@ss.hasPermission('hr:myemployee-basic:query')")
    public CommonResult<EmployeeBasicRespVO> getEmployeeBasicMy() {
        EmployeeBasicDO employeeBasic = employeeBasicService.getEmployeeBasicMy(getLoginUserId());
        CommonResult<EmployeeBasicRespVO> success = success(EmployeeBasicConvert.INSTANCE.convert(employeeBasic));
        if (success.getData()!=null){
            success.getData().setNickImg(employeeBasic.getNickImg());
        }
        return success;
    }

    @GetMapping("/get/all")
    @Operation(summary = "审核员获得员工基本信息")
    @PreAuthorize("@ss.hasPermission('hr:employee-basic:query')")
    public CommonResult<List<EmployeeBasicRespVO>> getEmployeeBasicAll() {
        List<EmployeeBasicDO> list = employeeBasicService.getEmployeeBasicList(new EmployeeBasicExportReqVO());
        return success(EmployeeBasicConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list")
    @Operation(summary = "获得员工基本信息列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hr:employee-basic:query')")
    public CommonResult<List<EmployeeBasicRespVO>> getEmployeeBasicList(@RequestParam("ids") Collection<Integer> ids) {
        List<EmployeeBasicDO> list = employeeBasicService.getEmployeeBasicList(ids);
        return success(EmployeeBasicConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得员工基本信息分页")
    @PreAuthorize("@ss.hasPermission('hr:employee-basic:query')")
    public CommonResult<PageResult<EmployeeBasicRespVO>> getEmployeeBasicPage(@Valid EmployeeBasicPageReqVO pageVO) {
        PageResult<EmployeeBasicDO> pageResult = employeeBasicService.getEmployeeBasicPage(pageVO);
        return success(EmployeeBasicConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出员工基本信息 Excel")
    @PreAuthorize("@ss.hasPermission('hr:employee-basic:export')")
    @OperateLog(type = EXPORT)
    public void exportEmployeeBasicExcel(@Valid EmployeeBasicExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EmployeeBasicDO> list = employeeBasicService.getEmployeeBasicList(exportReqVO);
        // 导出 Excel
        List<EmployeeBasicExcelVO> datas = EmployeeBasicConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "员工基本信息.xls", "数据", EmployeeBasicExcelVO.class, datas);
    }



}
