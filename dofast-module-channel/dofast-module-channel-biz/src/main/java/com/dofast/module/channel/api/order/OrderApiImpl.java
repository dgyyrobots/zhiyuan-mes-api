package com.dofast.module.channel.api.order;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dofast.module.channel.api.order.dto.OrderBaseDTO;
import com.dofast.module.channel.controller.admin.order.vo.OrderExportReqVO;
import com.dofast.module.channel.dal.dataobject.order.OrderDO;
import com.dofast.module.channel.dal.mysql.order.ChannelOrderMapper;
import com.dofast.module.channel.service.order.ChannelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class OrderApiImpl implements OrderApi{
    @Autowired
    ChannelOrderService channelOrderService;

    @Autowired
    ChannelOrderMapper orderMapper;

    public int updateAddressIdByRefOid(String refOid, Integer customerId) {
        return orderMapper.update(null, new LambdaUpdateWrapper<OrderDO>()
                        .eq(OrderDO::getRefOid, refOid)
                        .set(OrderDO::getCustomerId, customerId));
    }

    @Override
    public OrderBaseDTO selectById(String id) {
        OrderDO orderDO = orderMapper.selectById(Integer.valueOf(id));
        OrderBaseDTO orderBaseDTO = BeanUtil.toBean(orderDO, OrderBaseDTO.class);
        return orderBaseDTO;
    }

    @Override
    public int updateByIdAndStatus(Integer id, String status, OrderBaseDTO update) {
        OrderDO orderDO = BeanUtil.toBean(update, OrderDO.class);
        return orderMapper.updateByIdAndStatus(id, status, orderDO);
    }


}
