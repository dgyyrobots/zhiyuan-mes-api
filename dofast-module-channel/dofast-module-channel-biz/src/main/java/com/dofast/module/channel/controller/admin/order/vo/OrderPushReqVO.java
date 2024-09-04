package com.dofast.module.channel.controller.admin.order.vo;

import java.util.List;
import lombok.Data;

@Data
public class OrderPushReqVO {

    private String appKey;
    private String method;
    private String timestamp;
    private String tag;
    private String reqId;
    private String sign;

    private OrderReceiveVO content;
}
