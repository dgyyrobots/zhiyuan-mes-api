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
import com.dofast.module.finance.dal.dataobject.cash.CashDepositorDO;
import com.dofast.module.finance.convert.cash.CashDepositorConvert;
import com.dofast.module.finance.service.cash.CashDepositorService;

@Tag(name = "管理后台 - 资金账号")
@RestController
@RequestMapping("/finance/cash-depositor")
@Validated
public class CashDepositorController {

    @Resource
    private CashDepositorService cashDepositorService;

    @PostMapping("/create")
    @Operation(summary = "创建资金账号")
    @PreAuthorize("@ss.hasPermission('finance:cash-depositor:create')")
    public CommonResult<Long> createCashDepositor(@Valid @RequestBody CashDepositorCreateReqVO createReqVO) {
        return success(cashDepositorService.createCashDepositor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资金账号")
    @PreAuthorize("@ss.hasPermission('finance:cash-depositor:update')")
    public CommonResult<Boolean> updateCashDepositor(@Valid @RequestBody CashDepositorUpdateReqVO updateReqVO) {
        cashDepositorService.updateCashDepositor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资金账号")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('finance:cash-depositor:delete')")
    public CommonResult<Boolean> deleteCashDepositor(@RequestParam("id") Long id) {
        cashDepositorService.deleteCashDepositor(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资金账号")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('finance:cash-depositor:query')")
    public CommonResult<CashDepositorRespVO> getCashDepositor(@RequestParam("id") Long id) {
        CashDepositorDO cashDepositor = cashDepositorService.getCashDepositor(id);
        return success(CashDepositorConvert.INSTANCE.convert(cashDepositor));
    }

    @GetMapping("/list")
    @Operation(summary = "获得资金账号列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('finance:cash-depositor:query')")
    public CommonResult<List<CashDepositorRespVO>> getCashDepositorList(@RequestParam("ids") Collection<Long> ids) {
        List<CashDepositorDO> list = cashDepositorService.getCashDepositorList(ids);
        return success(CashDepositorConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资金账号分页")
    @PreAuthorize("@ss.hasPermission('finance:cash-depositor:query')")
    public CommonResult<PageResult<CashDepositorRespVO>> getCashDepositorPage(@Valid CashDepositorPageReqVO pageVO) {
        PageResult<CashDepositorDO> pageResult = cashDepositorService.getCashDepositorPage(pageVO);
        return success(CashDepositorConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资金账号 Excel")
    @PreAuthorize("@ss.hasPermission('finance:cash-depositor:export')")
    @OperateLog(type = EXPORT)
    public void exportCashDepositorExcel(@Valid CashDepositorExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CashDepositorDO> list = cashDepositorService.getCashDepositorList(exportReqVO);
        // 导出 Excel
        List<CashDepositorExcelVO> datas = CashDepositorConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "资金账号.xls", "数据", CashDepositorExcelVO.class, datas);
    }

}
