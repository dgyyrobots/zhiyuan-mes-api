package com.dofast.module.finance.controller.admin.cash;

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

import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashBalanceDO;
import com.dofast.module.finance.convert.cash.CashBalanceConvert;
import com.dofast.module.finance.service.cash.CashBalanceService;

@Tag(name = "管理后台 - 现金余额")
@RestController
@RequestMapping("/finance/cash-balance")
@Validated
public class CashBalanceController {

    @Resource
    private CashBalanceService cashBalanceService;

    @PostMapping("/create")
    @Operation(summary = "创建现金余额")
    @PreAuthorize("@ss.hasPermission('finance:cash-balance:create')")
    public CommonResult<Long> createCashBalance(@Valid @RequestBody CashBalanceCreateReqVO createReqVO) {
        return success(cashBalanceService.createCashBalance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新现金余额")
    @PreAuthorize("@ss.hasPermission('finance:cash-balance:update')")
    public CommonResult<Boolean> updateCashBalance(@Valid @RequestBody CashBalanceUpdateReqVO updateReqVO) {
        cashBalanceService.updateCashBalance(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除现金余额")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('finance:cash-balance:delete')")
    public CommonResult<Boolean> deleteCashBalance(@RequestParam("id") Long id) {
        cashBalanceService.deleteCashBalance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得现金余额")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('finance:cash-balance:query')")
    public CommonResult<CashBalanceRespVO> getCashBalance(@RequestParam("id") Long id) {
        CashBalanceDO cashBalance = cashBalanceService.getCashBalance(id);
        return success(CashBalanceConvert.INSTANCE.convert(cashBalance));
    }

    @GetMapping("/list")
    @Operation(summary = "获得现金余额列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('finance:cash-balance:query')")
    public CommonResult<List<CashBalanceRespVO>> getCashBalanceList(@RequestParam("ids") Collection<Long> ids) {
        List<CashBalanceDO> list = cashBalanceService.getCashBalanceList(ids);
        return success(CashBalanceConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得现金余额分页")
    @PreAuthorize("@ss.hasPermission('finance:cash-balance:query')")
    public CommonResult<PageResult<CashBalanceRespVO>> getCashBalancePage(@Valid CashBalancePageReqVO pageVO) {
        PageResult<CashBalanceDO> pageResult = cashBalanceService.getCashBalancePage(pageVO);
        return success(CashBalanceConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出现金余额 Excel")
    @PreAuthorize("@ss.hasPermission('finance:cash-balance:export')")
    @OperateLog(type = EXPORT)
    public void exportCashBalanceExcel(@Valid CashBalanceExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CashBalanceDO> list = cashBalanceService.getCashBalanceList(exportReqVO);
        // 导出 Excel
        List<CashBalanceExcelVO> datas = CashBalanceConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "现金余额.xls", "数据", CashBalanceExcelVO.class, datas);
    }

}
