package com.dofast.module.finance.controller.admin.bpm.cashTrade;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.finance.controller.admin.bpm.cashTrade.vo.CashTradeBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashTrade.vo.CashTradeBpmPageReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashTrade.vo.CashTradeBpmRespVO;
import com.dofast.module.finance.convert.cash.CashTradeConvert;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeDO;
import com.dofast.module.finance.service.bpm.cashTrade.CashTradeBpmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - 财务流水工作流")
@RestController
@RequestMapping("/finance/bpm/cash-trade")
@Validated
public class CashTradeBpmController {

    @Resource
    private CashTradeBpmService cashTradeBpmService;

    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:create')")
    @Operation(summary = "创建财务流水")
    public CommonResult<Long> createLeave(@Valid @RequestBody CashTradeBpmCreateReqVO createReqVO) {
        return success(cashTradeBpmService.createCashTrade(getLoginUserId(), createReqVO));
    }

    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得财务流水")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<CashTradeBpmRespVO> getLeave(@RequestParam("id") Long id) {
        CashTradeDO cashTrade = cashTradeBpmService.getCashTrade(id);
        return success(CashTradeConvert.INSTANCE.convertBpm(cashTrade));
    }

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得财务流水分页")
    public CommonResult<PageResult<CashTradeBpmRespVO>> getLeavePage(@Valid CashTradeBpmPageReqVO pageVO) {
        PageResult<CashTradeDO> pageResult = cashTradeBpmService.getCashTradePage(getLoginUserId(), pageVO);
        return success(CashTradeConvert.INSTANCE.convertPageBpm(pageResult));
    }
}
