package com.dofast.module.purchase.service.bpm.invoice.listener;


import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEvent;
import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEventListener;
import com.dofast.module.purchase.service.bpm.invoice.InvoiceBpmService;
import com.dofast.module.purchase.service.bpm.invoice.InvoiceBpmServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * OA 请假单的结果的监听器实现类
 *
 * @author 芋道源码
 */
@Component
public class InvoiceResultListener extends BpmProcessInstanceResultEventListener {

    @Resource
    private InvoiceBpmService invoiceBpmService;

    @Override
    protected String getProcessDefinitionKey() {
        return InvoiceBpmServiceImpl.INVOICE_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceResultEvent event) {
        invoiceBpmService.updateInvoiceResult(Long.parseLong(event.getBusinessKey()), event.getResult());
    }

}
