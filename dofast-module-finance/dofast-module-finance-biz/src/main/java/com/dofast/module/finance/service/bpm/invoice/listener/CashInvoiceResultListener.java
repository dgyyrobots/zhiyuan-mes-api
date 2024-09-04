package com.dofast.module.finance.service.bpm.invoice.listener;


import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEvent;
import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEventListener;
import com.dofast.module.finance.service.bpm.invoice.CashInvoiceBpmService;
import com.dofast.module.finance.service.bpm.invoice.CashInvoiceBpmServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * OA 请假单的结果的监听器实现类
 *
 * @author 芋道源码
 */
@Component
public class CashInvoiceResultListener extends BpmProcessInstanceResultEventListener {

    @Resource
    private CashInvoiceBpmService cashInvoiceBpmService;

    @Override
    protected String getProcessDefinitionKey() {
        return CashInvoiceBpmServiceImpl.CASHFUND_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceResultEvent event) {
        cashInvoiceBpmService.updateCashInvoiceResult(Long.parseLong(event.getBusinessKey()), event.getResult());
    }

}
