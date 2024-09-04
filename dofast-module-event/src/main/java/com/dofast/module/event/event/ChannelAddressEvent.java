package com.dofast.module.event.event;


import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ChannelAddressEvent<T> extends ApplicationEvent{

    private String topic;

    private T data;

    private String type;

    public ChannelAddressEvent(Object source, String topic, String type, T data) {
        super(source);
        this.topic = topic;
        this.data = data;
        this.type = type;
    }

    public ChannelAddressEvent(Object source, String topic, T data) {
        super(source);
        this.topic = topic;
        this.data = data;
        this.type = "";
    }
}
