package com.dofast.module.hr.controller.admin.salary;

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

import com.dofast.module.hr.controller.admin.salary.vo.*;
import com.dofast.module.hr.dal.dataobject.salary.SalaryDO;
import com.dofast.module.hr.convert.salary.SalaryConvert;
import com.dofast.module.hr.service.salary.SalaryService;

@Tag(name = "管理后台 - 工资总")
@RestController
@RequestMapping("/hr/salary")
@Validated
public class SalaryController {

    @Resource
    private SalaryService salaryService;

    @PostMapping("/create")
    @Operation(summary = "创建工资总")
    @PreAuthorize("@ss.hasPermission('hr:salary:create')")
    public CommonResult<Integer> createSalary(@Valid @RequestBody SalaryCreateReqVO createReqVO) {
        return success(salaryService.createSalary(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工资总")
    @PreAuthorize("@ss.hasPermission('hr:salary:update')")
    public CommonResult<Boolean> updateSalary(@Valid @RequestBody SalaryUpdateReqVO updateReqVO) {
        salaryService.updateSalary(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工资总")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hr:salary:delete')")
    public CommonResult<Boolean> deleteSalary(@RequestParam("id") Integer id) {
        salaryService.deleteSalary(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工资总")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hr:salary:query')")
    public CommonResult<SalaryRespVO> getSalary(@RequestParam("id") Integer id) {
        SalaryDO salary = salaryService.getSalary(id);
        return success(SalaryConvert.INSTANCE.convert(salary));
    }

    @GetMapping("/list")
    @Operation(summary = "获得工资总列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hr:salary:query')")
    public CommonResult<List<SalaryRespVO>> getSalaryList(@RequestParam("ids") Collection<Integer> ids) {
        List<SalaryDO> list = salaryService.getSalaryList(ids);
        return success(SalaryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工资总分页")
    @PreAuthorize("@ss.hasPermission('hr:salary:query')")
    public CommonResult<PageResult<SalaryRespVO>> getSalaryPage(@Valid SalaryPageReqVO pageVO) {
        PageResult<SalaryDO> pageResult = salaryService.getSalaryPage(pageVO);
        return success(SalaryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工资总 Excel")
    @PreAuthorize("@ss.hasPermission('hr:salary:export')")
    @OperateLog(type = EXPORT)
    public void exportSalaryExcel(@Valid SalaryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<SalaryDO> list = salaryService.getSalaryList(exportReqVO);
        // 导出 Excel
        List<SalaryExcelVO> datas = SalaryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "工资总.xls", "数据", SalaryExcelVO.class, datas);
    }

}
