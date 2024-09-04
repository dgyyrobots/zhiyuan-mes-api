package com.dofast.module.purchase.service.bpm.order.listener;

import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEvent;
import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEventListener;
import com.dofast.module.purchase.service.bpm.order.OrderBpmServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderResultListener extends BpmProcessInstanceResultEventListener {

    @Resource
    private OrderBpmServiceImpl orderBpmService;


    @Override
    protected String getProcessDefinitionKey() {
        return OrderBpmServiceImpl.ORDER_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceResultEvent event) {
        orderBpmService.updateOrderResult(Long.parseLong(event.getBusinessKey()), event.getResult());
    }
}
