package com.dofast.module.trade.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TradeOrderReceiverAddress {
    @Schema(description = "收件人名称", example = "张三")
    private String receiverName;

    @Schema(description = "收件人手机", example = "13800138000")
    private String receiverMobile;

    @Schema(description = "收件人地区编号", example = "110000")
    private Integer receiverAreaId;

    @Schema(description = "收件人邮编", example = "100000")
    private Integer receiverPostCode;

    @Schema(description = "收件人详细地址", example = "中关村大街 1 号")
    private String receiverDetailAddress;

}
