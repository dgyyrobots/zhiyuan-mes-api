package com.dofast.module.hr.controller.admin.salarydetail;

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

import com.dofast.module.hr.controller.admin.salarydetail.vo.*;
import com.dofast.module.hr.dal.dataobject.salarydetail.SalarydetailDO;
import com.dofast.module.hr.convert.salarydetail.SalarydetailConvert;
import com.dofast.module.hr.service.salarydetail.SalarydetailService;

@Tag(name = "管理后台 - 工资明细")
@RestController
@RequestMapping("/hr/salarydetail")
@Validated
public class SalarydetailController {

    @Resource
    private SalarydetailService salarydetailService;

    @PostMapping("/create")
    @Operation(summary = "创建工资明细")
    @PreAuthorize("@ss.hasPermission('hr:salarydetail:create')")
    public CommonResult<Integer> createSalarydetail(@Valid @RequestBody SalarydetailCreateReqVO createReqVO) {
        return success(salarydetailService.createSalarydetail(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工资明细")
    @PreAuthorize("@ss.hasPermission('hr:salarydetail:update')")
    public CommonResult<Boolean> updateSalarydetail(@Valid @RequestBody SalarydetailUpdateReqVO updateReqVO) {
        salarydetailService.updateSalarydetail(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工资明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hr:salarydetail:delete')")
    public CommonResult<Boolean> deleteSalarydetail(@RequestParam("id") Integer id) {
        salarydetailService.deleteSalarydetail(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工资明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hr:salarydetail:query')")
    public CommonResult<SalarydetailRespVO> getSalarydetail(@RequestParam("id") Integer id) {
        SalarydetailDO salarydetail = salarydetailService.getSalarydetail(id);
        return success(SalarydetailConvert.INSTANCE.convert(salarydetail));
    }

    @GetMapping("/list")
    @Operation(summary = "获得工资明细列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hr:salarydetail:query')")
    public CommonResult<List<SalarydetailRespVO>> getSalarydetailList(@RequestParam("ids") Collection<Integer> ids) {
        List<SalarydetailDO> list = salarydetailService.getSalarydetailList(ids);
        return success(SalarydetailConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工资明细分页")
    @PreAuthorize("@ss.hasPermission('hr:salarydetail:query')")
    public CommonResult<PageResult<SalarydetailRespVO>> getSalarydetailPage(@Valid SalarydetailPageReqVO pageVO) {
        PageResult<SalarydetailDO> pageResult = salarydetailService.getSalarydetailPage(pageVO);
        return success(SalarydetailConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工资明细 Excel")
    @PreAuthorize("@ss.hasPermission('hr:salarydetail:export')")
    @OperateLog(type = EXPORT)
    public void exportSalarydetailExcel(@Valid SalarydetailExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<SalarydetailDO> list = salarydetailService.getSalarydetailList(exportReqVO);
        // 导出 Excel
        List<SalarydetailExcelVO> datas = SalarydetailConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "工资明细.xls", "数据", SalarydetailExcelVO.class, datas);
    }

}
