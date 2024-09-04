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
import com.dofast.module.finance.dal.dataobject.cash.CashFundDO;
import com.dofast.module.finance.convert.cash.CashFundConvert;
import com.dofast.module.finance.service.cash.CashFundService;

@Tag(name = "管理后台 - 财务退款")
@RestController
@RequestMapping("/finance/cash-fund")
@Validated
public class CashFundController {

    @Resource
    private CashFundService cashFundService;

    @PostMapping("/create")
    @Operation(summary = "创建财务退款")
    @PreAuthorize("@ss.hasPermission('finance:cash-fund:create')")
    public CommonResult<Long> createCashFund(@Valid @RequestBody CashFundCreateReqVO createReqVO) {
        return success(cashFundService.createCashFund(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新财务退款")
    @PreAuthorize("@ss.hasPermission('finance:cash-fund:update')")
    public CommonResult<Boolean> updateCashFund(@Valid @RequestBody CashFundUpdateReqVO updateReqVO) {
        cashFundService.updateCashFund(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除财务退款")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('finance:cash-fund:delete')")
    public CommonResult<Boolean> deleteCashFund(@RequestParam("id") Long id) {
        cashFundService.deleteCashFund(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得财务退款")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('finance:cash-fund:query')")
    public CommonResult<CashFundRespVO> getCashFund(@RequestParam("id") Long id) {
        CashFundDO cashFund = cashFundService.getCashFund(id);
        return success(CashFundConvert.INSTANCE.convert(cashFund));
    }

    @GetMapping("/list")
    @Operation(summary = "获得财务退款列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('finance:cash-fund:query')")
    public CommonResult<List<CashFundRespVO>> getCashFundList(@RequestParam("ids") Collection<Long> ids) {
        List<CashFundDO> list = cashFundService.getCashFundList(ids);
        return success(CashFundConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得财务退款分页")
    @PreAuthorize("@ss.hasPermission('finance:cash-fund:query')")
    public CommonResult<PageResult<CashFundRespVO>> getCashFundPage(@Valid CashFundPageReqVO pageVO) {
        PageResult<CashFundDO> pageResult = cashFundService.getCashFundPage(pageVO);
        return success(CashFundConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出财务退款 Excel")
    @PreAuthorize("@ss.hasPermission('finance:cash-fund:export')")
    @OperateLog(type = EXPORT)
    public void exportCashFundExcel(@Valid CashFundExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CashFundDO> list = cashFundService.getCashFundList(exportReqVO);
        // 导出 Excel
        List<CashFundExcelVO> datas = CashFundConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "财务退款.xls", "数据", CashFundExcelVO.class, datas);
    }

}
