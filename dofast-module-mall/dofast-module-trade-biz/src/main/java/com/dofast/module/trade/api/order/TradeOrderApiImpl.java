package com.dofast.module.trade.api.order;

import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.module.trade.api.order.dto.TradeOrderRespDTO;
import com.dofast.module.trade.api.order.dto.TradeOrderSummaryRespDTO;
import com.dofast.module.trade.controller.admin.order.vo.TradeOrderListReqVO;
import com.dofast.module.trade.convert.order.TradeOrderConvert;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import com.dofast.module.trade.service.order.TradeOrderQueryService;
import com.dofast.module.trade.service.order.TradeOrderService;
import com.dofast.module.trade.service.order.TradeOrderUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import javax.annotation.Resource;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 订单 API 接口实现类
 *
 * @author HUIHUI
 */
@Service
@Validated
public class TradeOrderApiImpl implements TradeOrderApi {


    @Resource
    private TradeOrderUpdateService tradeOrderUpdateService;
    @Resource
    private TradeOrderQueryService tradeOrderQueryService;


    @Override
    public List<TradeOrderRespDTO> getOrderList(Collection<Long> ids) {
        return TradeOrderConvert.INSTANCE.convertList04(tradeOrderQueryService.getOrderList(ids));
    }

    @Override

    public TradeOrderRespDTO getOrder(Long id) {
        return TradeOrderConvert.INSTANCE.convert(tradeOrderQueryService.getOrder(id));
    }

    @Override
    public void cancelPaidOrder(Long userId, Long orderId) {
        tradeOrderUpdateService.cancelPaidOrder(userId, orderId);
    }



    @Override
    public Integer getOrderStatus(Long id) {
        return null;
    }

    @Override
    public TradeOrderSummaryRespDTO getOrderSummary(LocalDateTime beginTime, LocalDateTime endTime) {
        return null;
    }

}
