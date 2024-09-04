package com.dofast.module.hr.controller.admin.salarycommission;

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

import com.dofast.module.hr.controller.admin.salarycommission.vo.*;
import com.dofast.module.hr.dal.dataobject.salarycommission.SalarycommissionDO;
import com.dofast.module.hr.convert.salarycommission.SalarycommissionConvert;
import com.dofast.module.hr.service.salarycommission.SalarycommissionService;

@Tag(name = "管理后台 - 绩效工资")
@RestController
@RequestMapping("/hr/salarycommission")
@Validated
public class SalarycommissionController {

    @Resource
    private SalarycommissionService salarycommissionService;

    @PostMapping("/create")
    @Operation(summary = "创建绩效工资")
    @PreAuthorize("@ss.hasPermission('hr:salarycommission:create')")
    public CommonResult<Integer> createSalarycommission(@Valid @RequestBody SalarycommissionCreateReqVO createReqVO) {
        return success(salarycommissionService.createSalarycommission(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新绩效工资")
    @PreAuthorize("@ss.hasPermission('hr:salarycommission:update')")
    public CommonResult<Boolean> updateSalarycommission(@Valid @RequestBody SalarycommissionUpdateReqVO updateReqVO) {
        salarycommissionService.updateSalarycommission(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除绩效工资")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hr:salarycommission:delete')")
    public CommonResult<Boolean> deleteSalarycommission(@RequestParam("id") Integer id) {
        salarycommissionService.deleteSalarycommission(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得绩效工资")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hr:salarycommission:query')")
    public CommonResult<SalarycommissionRespVO> getSalarycommission(@RequestParam("id") Integer id) {
        SalarycommissionDO salarycommission = salarycommissionService.getSalarycommission(id);
        return success(SalarycommissionConvert.INSTANCE.convert(salarycommission));
    }

    @GetMapping("/list")
    @Operation(summary = "获得绩效工资列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hr:salarycommission:query')")
    public CommonResult<List<SalarycommissionRespVO>> getSalarycommissionList(@RequestParam("ids") Collection<Integer> ids) {
        List<SalarycommissionDO> list = salarycommissionService.getSalarycommissionList(ids);
        return success(SalarycommissionConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得绩效工资分页")
    @PreAuthorize("@ss.hasPermission('hr:salarycommission:query')")
    public CommonResult<PageResult<SalarycommissionRespVO>> getSalarycommissionPage(@Valid SalarycommissionPageReqVO pageVO) {
        PageResult<SalarycommissionDO> pageResult = salarycommissionService.getSalarycommissionPage(pageVO);
        return success(SalarycommissionConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出绩效工资 Excel")
    @PreAuthorize("@ss.hasPermission('hr:salarycommission:export')")
    @OperateLog(type = EXPORT)
    public void exportSalarycommissionExcel(@Valid SalarycommissionExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<SalarycommissionDO> list = salarycommissionService.getSalarycommissionList(exportReqVO);
        // 导出 Excel
        List<SalarycommissionExcelVO> datas = SalarycommissionConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "绩效工资.xls", "数据", SalarycommissionExcelVO.class, datas);
    }

}
