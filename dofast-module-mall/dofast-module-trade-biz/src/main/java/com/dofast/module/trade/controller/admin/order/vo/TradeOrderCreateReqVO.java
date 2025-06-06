package com.dofast.module.trade.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "主订单 - 交易订单创建 Request VO")
@Data
public class TradeOrderCreateReqVO {

    @Schema(description = "收件地址编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "收件地址不能为空")
    private Long addressId;

    @Schema(description = "用户编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "客户不能为空")
    private Long userId;

    @Schema(description = "优惠劵编号", example = "1024")
    private Long couponId;

    @Schema(description = "备注", example = "这个是我的订单哟")
    private String remark;

    @Schema(description = "是否来自购物车", requiredMode = Schema.RequiredMode.REQUIRED, example = "true") // true - 来自购物车；false - 立即购买
    @NotNull(message = "是否来自购物车不能为空")
    private Boolean fromCart;

    @Schema(description = "配送类型",requiredMode = Schema.RequiredMode.REQUIRED,example = "true")
    @NotNull(message = "配送类型不能为空")
    private Integer deliveryType;

    /**
     * 订单商品项列表
     */
    @NotEmpty(message = "必须选择购买的商品")
    @Valid
    private List<Item> items;

    @Schema(description = "订单商品项")
    @Data
    public static class Item {

        @Schema(description = "商品 SKU 编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "111")
        @NotNull(message = "商品 SKU 编号不能为空")
        private Long skuId;

        @Schema(description = "商品 SKU 购买数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
        @NotNull(message = "商品 SKU 购买数量不能为空")
        @Min(value = 1, message = "商品 SKU 购买数量必须大于 0")
        private Integer count;

    }


    @Schema(description = "业务员的ID")
    private Integer systemUserId;

    @Schema(description = "业务员的姓名")
    private String systemUserName;

    @Schema(description = "应付金额")
    private Integer payPrice;



}
