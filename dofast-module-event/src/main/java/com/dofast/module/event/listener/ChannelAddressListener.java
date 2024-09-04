package com.dofast.module.event.listener;


import com.dofast.module.channel.api.address.AddressApi;
import com.dofast.module.event.event.ChannelAddressEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ChannelAddressListener {

    
    @Autowired
    private AddressApi addressApi;
    /**
     * 监听topic
     * @param channelAddressEvent
     */
    @EventListener(condition = "#{channelAddressEvent.topic.equals(T(com.dofast.module.event.enums.ChannelOrderTopic).CHANNEL_CUSTOMER_CREATE.getTopic())}")
    public void onChannelOrderCreate(ChannelAddressEvent channelAddressEvent) {
        //data泛型
    }
}
