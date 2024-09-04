package com.dofast.module.sales.service.bpm.listener;

import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEvent;
import com.dofast.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEventListener;
import com.dofast.module.bpm.service.oa.BpmOALeaveService;
import com.dofast.module.bpm.service.oa.BpmOALeaveServiceImpl;
import com.dofast.module.sales.service.bpm.BpmSalesService;
import com.dofast.module.sales.service.bpm.BpmSalesServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * OA 请假单的结果的监听器实现类
 *
 * @author 芋道源码
 */
@Component
public class SalesResultListener extends BpmProcessInstanceResultEventListener {

    @Resource
    private BpmSalesService bpmSalesService;

    @Override
    protected String getProcessDefinitionKey() {
        return BpmSalesServiceImpl.SALES_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceResultEvent event) {
        bpmSalesService.updateSaleResult(Long.parseLong(event.getBusinessKey()), event.getResult());
    }

}
