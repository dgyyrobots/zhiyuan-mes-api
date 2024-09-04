package com.dofast.module.channel.controller.admin.shop.vo;

import com.dofast.module.channel.dal.dataobject.shop.ShopDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 店铺和渠道信息的分装类，用于店铺概况页面渠道和店铺信息的显示
 */
@Schema(description = "管理后台 - 店铺概况商铺列表 ChannelShop VO")
@Data
@ToString(callSuper = true)
public class ChannelShopVO {
    @Schema(description = "渠道名称")
    private String channelName;

    @Schema(description = "店铺列表（只包含信息）")
    private List<ChannelReqShopVO> channelReqShopVOList;

}
