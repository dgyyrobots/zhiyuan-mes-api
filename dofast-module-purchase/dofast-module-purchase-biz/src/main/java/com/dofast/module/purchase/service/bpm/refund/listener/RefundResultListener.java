package com.dofast.module.purchase.service.bpm.refund.listener;

import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEvent;
import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEventListener;
import com.dofast.module.purchase.service.bpm.refund.RefundBpmServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RefundResultListener extends BpmProcessInstanceResultEventListener {

    @Resource
    private RefundBpmServiceImpl refundBpmService;
    @Override
    protected String getProcessDefinitionKey() {
        return RefundBpmServiceImpl.REFUND_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceResultEvent event) {
        refundBpmService.updateRefundResult(Long.parseLong(event.getBusinessKey()), event.getResult());
    }
}
