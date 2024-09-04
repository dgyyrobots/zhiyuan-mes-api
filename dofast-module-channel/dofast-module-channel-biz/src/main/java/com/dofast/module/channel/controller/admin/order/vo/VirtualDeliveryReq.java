package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 商城原始订单虚拟发货 Request VO")
@Data
@ToString(callSuper = true)
public class VirtualDeliveryReq {
    @Schema(description = "平台订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "XSDD2021062501")
    @NotNull(message = "平台订单编号不能为空")
    private String refOid;

    @Schema(description = "店铺编号;没有填写取订单上的店铺", requiredMode = Schema.RequiredMode.REQUIRED, example = "YWCE00001")
    private String posCode;
}
