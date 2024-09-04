package com.dofast.module.trade.controller.admin.order.vo;


import com.dofast.framework.common.enums.TerminalEnum;

import com.dofast.framework.common.pojo.PageParam;
import com.dofast.framework.common.validation.InEnum;
import com.dofast.framework.common.validation.Mobile;
import com.dofast.module.trade.enums.order.TradeOrderStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 交易订单的分页 Request VO")
@Data
public class TradeOrderPageReqVO extends PageParam {


    @Schema(description = "外部渠道ID", example = "88888888")
    private String channelOrderId;

    @Schema(description = "是否正式下单",defaultValue = "0")
    private Integer mixinOrderId;

    @Schema(description = "订单号", example = "88888888")
    private String no;

    @Schema(description = "外部渠道单号", example = "88888888")
    private String channelOrderNo;

    @Schema(description = "外部渠道", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String channel;

    @Schema(description = "渠道店铺", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long channelShopId;

    @Schema(description = "渠道店铺名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String channelShopName;



    @Schema(description = "用户编号", example = "1024")
    private Long userId;

    @Schema(description = "用户昵称", example = "小王")
    private String userNickname;

    @Schema(description = "用户手机号", example = "小王")
    @Mobile
    private String userMobile;


    @Schema(description = "收件人名称", example = "小红")
    private String receiverName;

    @Schema(description = "收件人手机", example = "1560")
    @Mobile
    private String receiverMobile;

    @Schema(description = "配送方式", example = "1")
    private Integer deliveryType;

    @Schema(description = "发货物流公司编号", example = "1")
    private String logisticsId;

    @Schema(description = "自提门店编号", example = "[1,2]")
    private List<Long> pickUpStoreIds;

    @Schema(description = "自提核销码", example = "12345678")
    private String pickUpVerifyCode;


    @Schema(description = "订单类型", example = "1")
    private Integer type;

    @Schema(description = "订单状态", example = "1")
    @InEnum(value = TradeOrderStatusEnum.class, message = "订单状态必须是 {value}")
    private Integer status;

    @Schema(description = "支付渠道", example = "wx_lite")
    private String payChannelCode;

    @Schema(description = "创建时间")
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] createTime;

    @Schema(description = "发布时间")
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] deliveryTime;

    @Schema(description = "租户Id", example = "1")
    private Long tenantId;

    @Schema(description = "业务员的ID")
    private Integer systemUserId;

    @Schema(description = "业务员的名称")
    private String systemUserName;

    @Schema(description = "关联的业务员列表")
    private Long userIdIn;

    @Schema(description = "关联的收件人手机号列表")
    private String receiverMobileIn;

    @Schema(description = "不关联的业务员列表")
    private List<Long> userIdNotIn;

    @Schema(description = "不关联的收件人手机号列表")
    private List<String> receiverMobileNotIn;

    @Schema(description = "排除的ID列表")
    private String idNotIn;

    @Schema(description = "任务状态")
    private List<String> tasks;

    @Schema(description = "客户详细地址")
    private String receiverDetailAddress;

    @Schema(description = "订单来源", example = "10")
    @InEnum(value = TerminalEnum.class, message = "订单来源 {value}")
    private Integer terminal;

    @Schema(description = "店铺ID", example = "10")
    private Long shopId;



}
