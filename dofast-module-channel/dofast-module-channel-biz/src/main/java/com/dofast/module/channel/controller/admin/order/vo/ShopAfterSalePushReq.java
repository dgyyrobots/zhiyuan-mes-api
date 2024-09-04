package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Schema(description = "管理后台 - 店铺售后推送 Request VO")
@Data
@ToString(callSuper = true)
public class ShopAfterSalePushReq {
    @Schema(description = "参数内容", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<ShopAfterSaleRes> content;
}
