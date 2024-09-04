package com.dofast.module.channel.controller.admin.shop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Schema(description = "管理后台 - 店铺概况商铺信息封装类 ChannelReqShop VO")
@Data
@ToString(callSuper = true)
public class ShopQueryVOUserAddress {
    @Schema(description = "地区id")
    private Integer areaId;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "省份")
    private String province;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "区县")
    private String district;

    @Schema(description = "详细地址")
    private String detailAddress;
}
