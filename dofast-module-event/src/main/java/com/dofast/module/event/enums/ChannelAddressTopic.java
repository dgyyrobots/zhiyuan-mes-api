package com.dofast.module.event.enums;

public enum ChannelAddressTopic {

    CHANNEL_ADDRESS_CREATE(1, "CHANNEL_ADDRESS_CREATE", "channel/address/create"); //创建订单

    private final Integer key;
    private final String topic;
    private final String uri;

    private ChannelAddressTopic(Integer key, String topic) {
        this.key = key;
        this.topic = topic;
        this.uri = "";
    }

    private ChannelAddressTopic(Integer key, String topic, String uri) {
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

    public String getTopic() {
        return topic;
    }

    @Override
    public String toString() {
        return this.topic;
    }
}
