package com.dofast.module.purchase.service.bpm.order;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.bpm.api.task.BpmProcessInstanceApi;
import com.dofast.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import com.dofast.module.purchase.controller.admin.bpm.order.vo.OrderBpmCreateReqVO;
import com.dofast.module.purchase.controller.admin.bpm.order.vo.OrderBpmPageReqVO;
import com.dofast.module.purchase.controller.admin.invoice.vo.InvoiceCreateReqVO;
import com.dofast.module.purchase.controller.admin.invoice.vo.InvoicePageReqVO;
import com.dofast.module.purchase.controller.admin.order.vo.OrderCreateReqVO;
import com.dofast.module.purchase.controller.admin.order.vo.OrderPageReqVO;
import com.dofast.module.purchase.convert.invoice.InvoiceConvert;
import com.dofast.module.purchase.convert.order.OrderConvert;
import com.dofast.module.purchase.dal.dataobject.invoice.InvoiceDO;
import com.dofast.module.purchase.dal.dataobject.order.OrderDO;
import com.dofast.module.purchase.dal.mysql.order.PurchaseOrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pad.util.PadSecurityUtils.getUserId;
import static com.dofast.module.bpm.enums.ErrorCodeConstants.OA_LEAVE_NOT_EXISTS;
import static com.dofast.module.purchase.enums.ErrorCodeConstants.ORDER_NOT_EXISTS;

@Service
public class OrderBpmServiceImpl implements OrderBpmService{

    /**
     * 采购订单对应的流程定义 KEY
     */
    public static final String ORDER_KEY = "purchase_order";

    @Resource
    private PurchaseOrderMapper purchaseOrderMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;

    @Override
    public Long createOrder(Long userId, OrderBpmCreateReqVO createReqVO) {
        // 插入 售后信息
        OrderDO orderDO = OrderConvert.INSTANCE.convert(createReqVO);
        orderDO.setCreator(String.valueOf(getUserId()));
        purchaseOrderMapper.insert(orderDO);

        // 发起 BPM 流程
        Map<String, Object> processInstanceVariables = new HashMap<>();
        processInstanceVariables.put("after-order",createReqVO);
        String processInstanceId = processInstanceApi.createProcessInstance(userId,
                new BpmProcessInstanceCreateReqDTO().setProcessDefinitionKey(ORDER_KEY)
                        .setVariables(processInstanceVariables).setBusinessKey(String.valueOf(orderDO.getId())));

        // 将工作流的编号，更新到 采购订单中
        purchaseOrderMapper.updateById(OrderConvert.INSTANCE.convert(createReqVO).setId(orderDO.getId()).setProcessInstanceId(processInstanceId));
        return Long.valueOf(orderDO.getId());
    }

    @Override
    public void updateOrderResult(Long id, Integer result) {
        validateLeaveExists(id);
        purchaseOrderMapper.updateById(new OrderDO().setProcessType(result));
    }

    private void validateLeaveExists(Long id) {
        if (purchaseOrderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public OrderDO getOrder(Long id) {
        return purchaseOrderMapper.selectById(id);
    }

    @Override
    public PageResult<OrderDO> getOrderPage(Long userId, OrderBpmPageReqVO pageReqVO) {
        return purchaseOrderMapper.selectPage(String.valueOf(userId),pageReqVO);
    }
}
