package com.dofast.module.finance.service.bpm.cashFund.listener;


import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEvent;
import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEventListener;
import com.dofast.module.finance.service.bpm.cashFund.CashFundBpmService;
import com.dofast.module.finance.service.bpm.cashFund.CashFundBpmServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * OA 请假单的结果的监听器实现类
 *
 * @author 芋道源码
 */
@Component
public class CashFundResultListener extends BpmProcessInstanceResultEventListener {

    @Resource
    private CashFundBpmService cashFundBpmService;

    @Override
    protected String getProcessDefinitionKey() {
        return CashFundBpmServiceImpl.CASHFUND_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceResultEvent event) {
        cashFundBpmService.updateCashFundResult(Long.parseLong(event.getBusinessKey()), event.getResult());
    }

}
