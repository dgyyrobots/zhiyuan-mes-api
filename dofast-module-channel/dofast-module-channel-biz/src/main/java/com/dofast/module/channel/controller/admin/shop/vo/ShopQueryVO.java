package com.dofast.module.channel.controller.admin.shop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Schema(description = "管理后台 - 店铺列表查询封装类 ShopQuery VO")
@Data
@ToString(callSuper = true)
public class ShopQueryVO {
    @Schema(description = "店铺id")
    private Integer id;

    @Schema(description = "店铺编码")
    private String code;

    @Schema(description = "店铺名")
    private String name;

    @Schema(description = "店铺标题")
    private String title;

    @Schema(description = "店铺平台类型")
    private String platform;

    @Schema(description = "店铺平台子类型")
    private String platformType;

    @Schema(description = "店铺类")
    private String type;

    @Schema(description = "创建时间")
    private Integer createTime;

    @Schema(description = "店铺状态")
    private String status;

    @Schema(description = "关联平台用户Id")
    private String refUserId;

    @Schema(description = "关联平台用户名称")
    private String refUserNick;

    @Schema(description = "平台授权状态")
    private String refAuthStatus;

    @Schema(description = "平台授权过期时间")
    private String refAuthExpiresTime;

    @Schema(description = "联系人信息")
    private ShopQueryVOUser contact;
}
