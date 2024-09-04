package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - 店铺订单发货 Request VO")
@Data
@ToString(callSuper = true)
public class OrderShipmentReq {
    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1879178508166855158")
    @NotNull(message = "订单编号不能为空")
    private String refOid;

    @Schema(description = "店铺编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "shaomsaj")
    @NotNull(message = "店铺编码不能为空")
    private String posCode;

    @Schema(description = "发货个性内容,目前只支持拼多多", requiredMode = Schema.RequiredMode.REQUIRED, example = "imei=识别码1,识别码2;")
    private String feature;

    @Schema(description = "物流包裹信息", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<OrderShipmentPakReq> packages;
}
