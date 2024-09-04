package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - 店铺订单发货物流包裹信息 Request VO")
@Data
@ToString(callSuper = true)
public class OrderShipmentPakReq {
    @Schema(description = "商家物流编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "ZTO")
    @NotNull(message = "商家物流编码不能为空")
    private String companyCode;

    @Schema(description = "物流单号", requiredMode = Schema.RequiredMode.REQUIRED, example = "78165613888845")
    @NotNull(message = "物流单号不能为空")
    private String outSid;

    @Schema(description = "货品详情", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<OrderShipmentItemReq> lines;
}
