package com.dofast.module.finance.service.bpm.cashFund;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.bpm.api.task.BpmProcessInstanceApi;
import com.dofast.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import com.dofast.module.finance.controller.admin.bpm.cashFund.vo.CashFundBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashFund.vo.CashFundBpmPageReqVO;
import com.dofast.module.finance.convert.cash.CashFundConvert;
import com.dofast.module.finance.dal.dataobject.cash.CashFundDO;
import com.dofast.module.finance.dal.mysql.cash.CashFundMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pad.util.PadSecurityUtils.getUserId;
import static com.dofast.module.finance.enums.ErrorCodeConstants.CASH_TRADE_INVOICE_NOT_EXISTS;

@Service
public class CashFundBpmServiceImpl implements CashFundBpmService{

    /**
     * 财务退款对应的流程定义 KEY
     */
    public static final String CASHFUND_KEY = "cash_fund";

    @Resource
    private CashFundMapper cashFundMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;

    @Override
    public Long createCashFund(Long userId, CashFundBpmCreateReqVO createReqVO) {
        // 插入 售后信息
        CashFundDO convert = CashFundConvert.INSTANCE.convert(createReqVO);
        convert.setCreator(String.valueOf(getUserId()));
        cashFundMapper.insert(convert);

        // 发起 BPM 流程
        Map<String, Object> processInstanceVariables = new HashMap<>();
        processInstanceVariables.put("after-order",createReqVO);
        String processInstanceId = processInstanceApi.createProcessInstance(userId,
                new BpmProcessInstanceCreateReqDTO().setProcessDefinitionKey(CASHFUND_KEY)
                        .setVariables(processInstanceVariables).setBusinessKey(String.valueOf(convert.getId())));

        // 将工作流的编号，更新到 采购入库单中
        cashFundMapper.updateById(CashFundConvert.INSTANCE.convert(createReqVO).setId(convert.getId()).setProcessInstanceId(processInstanceId));
        return Long.valueOf(convert.getId());
    }

    @Override
    public void updateCashFundResult(Long id, Integer result) {
        validateLeaveExists(id);
        cashFundMapper.updateById(new CashFundDO().setIsReturn(String.valueOf(result)));
    }

    private void validateLeaveExists(Long id) {
        if (cashFundMapper.selectById(id) == null) {
            throw exception(CASH_TRADE_INVOICE_NOT_EXISTS);
        }
    }

    @Override
    public CashFundDO getCashFund(Long id) {
        return cashFundMapper.selectById(id);
    }

    @Override
    public PageResult<CashFundDO> getCashFundPage(Long userId, CashFundBpmPageReqVO pageReqVO) {
        return cashFundMapper.selectPage(String.valueOf(userId),pageReqVO);
    }
}
