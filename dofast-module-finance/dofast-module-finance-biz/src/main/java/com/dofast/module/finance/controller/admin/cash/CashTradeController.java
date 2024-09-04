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
import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeDO;
import com.dofast.module.finance.convert.cash.CashTradeConvert;
import com.dofast.module.finance.service.cash.CashTradeService;

@Tag(name = "管理后台 - 财务流水")
@RestController
@RequestMapping("/finance/cash-trade")
@Validated
public class CashTradeController {

    @Resource
    private CashTradeService cashTradeService;

    @PostMapping("/create")
    @Operation(summary = "创建财务流水")
    @PreAuthorize("@ss.hasPermission('finance:cash-trade:create')")
    public CommonResult<Long> createCashTrade(@Valid @RequestBody CashTradeCreateReqVO createReqVO) {
        return success(cashTradeService.createCashTrade(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新财务流水")
    @PreAuthorize("@ss.hasPermission('finance:cash-trade:update')")
    public CommonResult<Boolean> updateCashTrade(@Valid @RequestBody CashTradeUpdateReqVO updateReqVO) {
        cashTradeService.updateCashTrade(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除财务流水")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('finance:cash-trade:delete')")
    public CommonResult<Boolean> deleteCashTrade(@RequestParam("id") Long id) {
        cashTradeService.deleteCashTrade(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得财务流水")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('finance:cash-trade:query')")
    public CommonResult<CashTradeRespVO> getCashTrade(@RequestParam("id") Long id) {
        CashTradeDO cashTrade = cashTradeService.getCashTrade(id);
        return success(CashTradeConvert.INSTANCE.convert(cashTrade));
    }

    @GetMapping("/list")
    @Operation(summary = "获得财务流水列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('finance:cash-trade:query')")
    public CommonResult<List<CashTradeRespVO>> getCashTradeList(@RequestParam("ids") Collection<Long> ids) {
        List<CashTradeDO> list = cashTradeService.getCashTradeList(ids);
        return success(CashTradeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得财务流水分页")
    @PreAuthorize("@ss.hasPermission('finance:cash-trade:query')")
    public CommonResult<PageResult<CashTradeRespVO>> getCashTradePage(@Valid CashTradePageReqVO pageVO) {
        PageResult<CashTradeDO> pageResult = cashTradeService.getCashTradePage(pageVO);
        return success(CashTradeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出财务流水 Excel")
    @PreAuthorize("@ss.hasPermission('finance:cash-trade:export')")
    @OperateLog(type = EXPORT)
    public void exportCashTradeExcel(@Valid CashTradeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CashTradeDO> list = cashTradeService.getCashTradeList(exportReqVO);
        // 导出 Excel
        List<CashTradeExcelVO> datas = CashTradeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "财务流水.xls", "数据", CashTradeExcelVO.class, datas);
    }

    @GetMapping("/day-get-money")
    @Operation(summary = "获得日订单所有的金额数量")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<BigDecimal> CountGetMoney() {
        BigDecimal result = cashTradeService.getDayOrderMoney(1);
        return success(result);
    }

    @GetMapping("/day-pay-money")
    @Operation(summary = "获得日订单所有的金额数量")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<BigDecimal> CountPayMoney() {
        BigDecimal result = cashTradeService.getDayOrderMoney(2);
        return success(result);
    }

}
