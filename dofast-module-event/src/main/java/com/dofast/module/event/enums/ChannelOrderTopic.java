package com.dofast.module.event.enums;

public enum ChannelOrderTopic {

    CHANNEL_ORDER_CREATE(526010, "CHANNEL_ORDER_CREATE" ,"channel.order.create"), //创建订单
    CHANNEL_ORDER_CREATE_GET(526011, "CHANNEL_ORDER_CREATE_GET" ,"channel.order.create.get"), //创建订单
    CHANNEL_ORDER_CREATE_RECEIVE(526012, "CHANNEL_ORDER_CREATE_RECEIVE" ,"channel.order.create.receive"); //创建订单

    private final Integer key;
    private final String topic;
    private final String uri;

    private ChannelOrderTopic(Integer key, String topic) {
        this.key = key;
        this.topic = topic;
        this.uri = "";
    }

    private ChannelOrderTopic(Integer key, String topic, String uri) {
        this.key = key;
        this.topic = topic;
        this.uri = uri;
    }

    public Integer getKey() {
        return key;
    }

    public String getUri() {
        return uri;
    }

    public String getTopic() {return  topic;}

    @Override
    public String toString() {
        return this.topic;
    }
}
