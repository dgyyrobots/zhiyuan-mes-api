package com.dofast.module.pay.api.order;

import com.dofast.module.pay.api.order.dto.PayOrderCreateReqDTO;
import com.dofast.module.pay.api.order.dto.PayOrderRespDTO;
import com.dofast.module.pay.convert.order.PayOrderConvert;
import com.dofast.module.pay.dal.dataobject.order.PayOrderDO;
import com.dofast.module.pay.service.order.PayOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 支付单 API 实现类
 *
 * @author 芋道源码
 */
@Service
public class PayOrderApiImpl implements PayOrderApi {

    @Resource
    private PayOrderService payOrderService;

    @Override
    public Long createOrder(PayOrderCreateReqDTO reqDTO) {
        return payOrderService.createOrder(reqDTO);
    }

    @Override
    public PayOrderRespDTO getOrder(Long id) {
        PayOrderDO order = payOrderService.getOrder(id);
        return PayOrderConvert.INSTANCE.convert2(order);
    }

    @Override
    public void updatePayOrderPrice(Long id, Integer payPrice,Integer amount) {
        payOrderService.updatePayOrderPrice(id, payPrice,amount);
    }

    @Override
    public PayOrderRespDTO selectByMID(Long merchantOrderId) {
        return PayOrderConvert.INSTANCE.convert2(payOrderService.selectByMI(merchantOrderId));
    }

}
