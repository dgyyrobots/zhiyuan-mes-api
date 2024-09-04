package com.dofast.module.channel.controller.admin.shop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Schema(description = "管理后台 - 店铺概况商铺信息封装类 ChannelReqShop VO")
@Data
@ToString(callSuper = true)
public class ShopQueryVOUser {
    @Schema(description = "昵称")
    private String nick;

    @Schema(description = "真实姓名")
    private String name;

    @Schema(description = "手机")
    private String mobile;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "联系人备注")
    private String remark;

    @Schema(description = "联系人地址")
    private ShopQueryVOUserAddress address;
}
