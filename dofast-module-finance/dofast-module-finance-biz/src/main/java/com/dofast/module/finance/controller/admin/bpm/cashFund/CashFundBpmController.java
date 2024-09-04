package com.dofast.module.finance.controller.admin.bpm.cashFund;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.finance.controller.admin.bpm.cashFund.vo.CashFundBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashFund.vo.CashFundBpmPageReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashFund.vo.CashFundBpmRespVO;
import com.dofast.module.finance.convert.cash.CashFundConvert;
import com.dofast.module.finance.dal.dataobject.cash.CashFundDO;
import com.dofast.module.finance.service.bpm.cashFund.CashFundBpmService;
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

@Tag(name = "管理后台 - 财务退款工作流")
@RestController
@RequestMapping("/finance/bpm/cash-fund")
@Validated
public class CashFundBpmController
{

    @Resource
    private CashFundBpmService cashFundBpmService;

    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:create')")
    @Operation(summary = "创建财务退款信息")
    public CommonResult<Long> createLeave(@Valid @RequestBody CashFundBpmCreateReqVO createReqVO) {
        return success(cashFundBpmService.createCashFund(getLoginUserId(), createReqVO));
    }

    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得财务退款信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<CashFundBpmRespVO> getLeave(@RequestParam("id") Long id) {
        CashFundDO leave = cashFundBpmService.getCashFund(id);
        return success(CashFundConvert.INSTANCE.convertBpm(leave));
    }

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得财务退款信息分页")
    public CommonResult<PageResult<CashFundBpmRespVO>> getLeavePage(@Valid CashFundBpmPageReqVO pageVO) {
        PageResult<CashFundDO> pageResult = cashFundBpmService.getCashFundPage(getLoginUserId(), pageVO);
        return success(CashFundConvert.INSTANCE.convertPageBpm(pageResult));
    }
}
