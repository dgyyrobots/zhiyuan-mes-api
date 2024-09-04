package com.dofast.module.channel.controller.admin.ordergoods.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 子订单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class OrderGoodsBaseVO {

    @Schema(description = "关联的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "17654")
    @NotNull(message = "关联的id不能为空")
    private String refOid;

    @Schema(description = "子订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11867")
    @NotNull(message = "子订单id不能为空")
    private String refOlId;

    @Schema(description = "平台SPU_ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28325")
    @NotNull(message = "平台SPU_ID不能为空")
    private String refSpuId;

    @Schema(description = "平台SKU_ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5479")
    @NotNull(message = "平台SKU_ID不能为空")
    private String refSkuId;

    @Schema(description = "商家编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "29640")
    @NotNull(message = "商家编码不能为空")
    private String outerId;

    @Schema(description = "货品标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "货品标题不能为空")
    private String title;

    @Schema(description = "货品规格", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "货品规格不能为空")
    private String standards;

    @Schema(description = "售价合计", requiredMode = Schema.RequiredMode.REQUIRED, example = "19793")
    @NotNull(message = "售价合计不能为空")
    private BigDecimal totalSellPrice;

    @Schema(description = "原价合计", requiredMode = Schema.RequiredMode.REQUIRED, example = "14820")
    @NotNull(message = "原价合计不能为空")
    private BigDecimal totalPrice;

    @Schema(description = "原价", requiredMode = Schema.RequiredMode.REQUIRED, example = "16865")
    @NotNull(message = "原价不能为空")
    private BigDecimal price;

    @Schema(description = "售价", requiredMode = Schema.RequiredMode.REQUIRED, example = "5426")
    @NotNull(message = "售价不能为空")
    private BigDecimal sellPrice;

    @Schema(description = "应收金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "应收金额不能为空")
    private BigDecimal totalFee;

    @Schema(description = "单个商品应收金额", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "单个商品应收金额不能为空")
    private BigDecimal singleFee;

    @Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "数量不能为空")
    private Integer num;

    @Schema(description = "子订单售后状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "子订单售后状态不能为空")
    private String refundStatus;

    @Schema(description = "子订单状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "子订单状态不能为空")
    private String status;

    @Schema(description = "商品图片URL", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @NotNull(message = "商品图片URL不能为空")
    private String picUrl;

    @Schema(description = "是否赠品", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "是否赠品不能为空")
    private Integer isFreeGift;

    @Schema(description = "关联用户ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "16979")
    private Integer userId;

    @Schema(description = "关联交易客户ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "16979")
    private Integer customerId;

    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "16979")
    private Integer tenantId;
}
