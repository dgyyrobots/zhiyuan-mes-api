package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * 用于接收前端传的数据
 */
@Schema(description = "管理后台 - 主订单接收数据，用于渠道订单转商城订单 OrderToInnerShop VO")
@Data
@ToString(callSuper = true)
public class OrderToInnerShopVO {

    @Schema(description = "渠道订单ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @NotNull(message = "渠道订单ID不允许为null")
    private String channelOrderId;

    @Schema(description = "商城用户ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long userId;

    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String userNickname;

    @Schema(description = "用户手机号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String userMobile;

    @Schema(description = "收件人姓名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String receiverName;

    @Schema(description = "收件人手机号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String receiverMobile;

    @Schema(description = "收件人地区ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer receiverAreaId;

    @Schema(description = "收件人店铺编号")
    private String receiverPostCode;

    @Schema(description = "收件人详细信息", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String receiverDetailAddress;

    @Schema(description = "业务员ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer systemUserId;

    @Schema(description = "业务员名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String systemUserName;
}
