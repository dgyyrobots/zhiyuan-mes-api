package com.dofast.module.finance.service.bpm.cashTrade.listener;

import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEvent;
import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEventListener;
import com.dofast.module.finance.service.bpm.cashTrade.CashTradeBpmService;
import com.dofast.module.finance.service.bpm.cashTrade.CashTradeBpmServiceImpl;
import com.dofast.module.finance.service.cash.CashTradeService;
import com.dofast.module.finance.service.cash.CashTradeServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * OA 请假单的结果的监听器实现类
 *
 * @author 芋道源码
 */
@Component
public class CashTradeResultListener extends BpmProcessInstanceResultEventListener {

    @Resource
    private CashTradeBpmService cashTradeBpmService;

    @Override
    protected String getProcessDefinitionKey() {
        return CashTradeBpmServiceImpl.CASHTRADE_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceResultEvent event) {
        cashTradeBpmService.updateCashTradeResult(Long.parseLong(event.getBusinessKey()), event.getResult());
    }

}
