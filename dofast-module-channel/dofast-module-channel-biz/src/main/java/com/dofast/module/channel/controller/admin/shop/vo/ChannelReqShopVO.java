package com.dofast.module.channel.controller.admin.shop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * 店铺信息的分装类，用于店铺概况页面显示店铺列表，供ChannelShopVO使用
 */
@Schema(description = "管理后台 - 店铺概况商铺信息封装类 ChannelReqShop VO")
@Data
@ToString(callSuper = true)
public class ChannelReqShopVO {
    @Schema(description = "店铺名称")
    private String displayName;

    @Schema(description = "店铺的id")
    private String shopCode;
}
