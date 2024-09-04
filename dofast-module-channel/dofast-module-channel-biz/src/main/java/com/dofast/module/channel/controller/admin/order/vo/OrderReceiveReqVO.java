package com.dofast.module.channel.controller.admin.order.vo;

import java.util.ArrayList;
import lombok.Data;

@Data
public class OrderReceiveReqVO {

    private String appKey;
    private String method;
    private String timestamp;
    private String tag;
    private String reqId;
    private String sign;

    ArrayList<OrderReceiveVO> content;
}
