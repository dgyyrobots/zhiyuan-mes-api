package com.dofast.module.channel.controller.admin.order.vo;

import com.dofast.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.Optional;

@Schema(description = "管理后台 - 主订单分页 OrderListQuery VO")
@Data
@ToString(callSuper = true)
public class OrderListQueryVO extends PageParam {

    @Schema(description = "渠道", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String channel;

    @Schema(description = "客户信息", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String customKeyword;

    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String endOrderTime;

    @Schema(description = "商品信息", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String goodsKeyword;

    @Schema(description = "订单号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String orderNo;

    @Schema(description = "订单状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String orderStatus;

    @Schema(description = "店铺编号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String shopCode;

    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String startOrderTime;
}
