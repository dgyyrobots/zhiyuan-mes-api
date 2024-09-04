package com.dofast.module.event.listener;


import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.channel.api.address.AddressApi;
import com.dofast.module.channel.api.address.dto.AddressGetDTO;
import com.dofast.module.channel.api.address.dto.AddressReceiveDTO;
import com.dofast.module.channel.api.order.OrderApi;
import com.dofast.module.channel.api.order.dto.OrderGetDTO;
import com.dofast.module.channel.api.order.dto.OrderReceiveDTO;
import com.dofast.module.channel.api.ordergoods.OrderGoodsApi;
import com.dofast.module.event.event.ChannelOrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;



@Component
public class ChannelOrderListener {

    
    @Autowired
    private AddressApi addressApi;

    @Autowired
    private OrderApi orderApi;

    @Autowired
    private OrderGoodsApi orderGoodsApi;

    /**
     * 监听topic
     * @param channelOrderEvent get
     */
    @EventListener(condition = "#channelOrderEvent.topic.contains(T(com.dofast.module.event.enums.ChannelOrderTopic).CHANNEL_ORDER_CREATE_GET.getTopic())")
    public void onChannelOrderCreateGet(ChannelOrderEvent channelOrderEvent) {
        System.out.println("create---------get");
        OrderGetDTO orderGetDTO = (OrderGetDTO) channelOrderEvent.getData();
        AddressGetDTO customerGetDTO = BeanUtil.toBean(orderGetDTO, AddressGetDTO.class);
        Integer customerId = addressApi.createAddress(customerGetDTO); //参数类型重写方法
        String refOid =  orderGetDTO.getRefOid();
        orderApi.updateAddressIdByRefOid(refOid, customerId);
        orderGoodsApi.updateAddressIdByRefOid(refOid, customerId);
    }



    @EventListener(condition = "#channelOrderEvent.topic.equals(T(com.dofast.module.event.enums.ChannelOrderTopic).CHANNEL_ORDER_CREATE_RECEIVE.getTopic())")
    public void onChannelOrderCreateReceive(ChannelOrderEvent channelOrderEvent) {
        OrderReceiveDTO orderReceiveDTO = (OrderReceiveDTO) channelOrderEvent.getData();
        AddressReceiveDTO customerReceiveDTO = BeanUtil.toBean(orderReceiveDTO, AddressReceiveDTO.class);
        Integer customerId = addressApi.createAddress(customerReceiveDTO); //参数类型重写方法
        String refOid = orderReceiveDTO.getRefOid();
        orderApi.updateAddressIdByRefOid(refOid, customerId);
        orderGoodsApi.updateAddressIdByRefOid(refOid, customerId);
    }

    @EventListener(condition = "#channelOrderEvent.topic.startsWith(T(com.dofast.module.event.enums.ChannelOrderTopic).CHANNEL_ORDER_CREATE.getTopic())")
    public void onChannelOrderCreate(ChannelOrderEvent channelOrderEvent) {
        System.out.println("create---------");
    }

    @EventListener(condition = "#channelOrderEvent.topic.startsWith('CHANNEL_ORDER_CREATE')")
    public void onChannelOrderCreatex (ChannelOrderEvent channelOrderEvent) {
        System.out.println("create---------1111");
    }
}
