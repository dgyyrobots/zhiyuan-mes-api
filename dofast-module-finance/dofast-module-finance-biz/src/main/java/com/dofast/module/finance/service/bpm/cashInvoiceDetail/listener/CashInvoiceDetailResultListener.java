package com.dofast.module.finance.service.bpm.cashInvoiceDetail.listener;


import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEvent;
import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEventListener;
import com.dofast.module.finance.service.bpm.cashInvoiceDetail.CashInvoiceDetailBpmService;
import com.dofast.module.finance.service.bpm.cashInvoiceDetail.CashInvoiceDetailBpmServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * OA 请假单的结果的监听器实现类
 *
 * @author 芋道源码
 */
@Component
public class CashInvoiceDetailResultListener extends BpmProcessInstanceResultEventListener {

    @Resource
    private CashInvoiceDetailBpmService cashInvoiceDetailBpmService;

    @Override
    protected String getProcessDefinitionKey() {
        return CashInvoiceDetailBpmServiceImpl.CASHINVOICDETAIL_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceResultEvent event) {
        cashInvoiceDetailBpmService.updateCashInvoiceDetailResult(Long.parseLong(event.getBusinessKey()), event.getResult());
    }

}
