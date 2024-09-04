package com.dofast.module.event.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class Dian3LogisticsEvent<T> extends ApplicationEvent {
    private String topic;

    private T data;

    private String type;

    public Dian3LogisticsEvent(Object source, String topic, String type, T data) {
        super(source);
        this.topic = topic;
        this.data = data;
        this.type = type;
    }

    public Dian3LogisticsEvent(Object source, String topic, T data) {
        super(source);
        this.topic = topic;
        this.data = data;
        this.type = "";
    }

    public T getData() {
        return data;
    }

    public String getTopic() {
        return topic;
    }

    public String getType() {
        return type;
    }
}
