package com.dofast.module.channel.controller.admin.ordergoods.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 子订单 Excel 导出 Request VO，参数和 OrderGoodsPageReqVO 是一致的")
@Data
public class OrderGoodsExportReqVO {

    @Schema(description = "关联的id", example = "17654")
    private String refOid;

    @Schema(description = "子订单id", example = "11867")
    private String refOlId;

    @Schema(description = "平台SPU_ID", example = "28325")
    private String refSpuId;

    @Schema(description = "平台SKU_ID", example = "5479")
    private String refSkuId;

    @Schema(description = "商家编码", example = "29640")
    private String outerId;

    @Schema(description = "货品标题")
    private String title;

    @Schema(description = "货品规格")
    private String standards;

    @Schema(description = "售价合计", example = "19793")
    private BigDecimal totalSellPrice;

    @Schema(description = "原价合计", example = "14820")
    private BigDecimal totalPrice;

    @Schema(description = "原价", example = "16865")
    private BigDecimal price;

    @Schema(description = "售价", example = "5426")
    private BigDecimal sellPrice;

    @Schema(description = "应收金额")
    private BigDecimal totalFee;

    @Schema(description = "单个商品应收金额")
    private BigDecimal singleFee;

    @Schema(description = "数量")
    private Integer num;

    @Schema(description = "子订单售后状态", example = "1")
    private String refundStatus;

    @Schema(description = "子订单状态", example = "2")
    private String status;

    @Schema(description = "商品图片URL", example = "https://www.iocoder.cn")
    private String picUrl;

    @Schema(description = "是否赠品")
    private Integer isFreeGift;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "关联用户ID", example = "27744")
    private Integer userId;

    @Schema(description = "关联交易客户ID", example = "16979")
    private Integer customerId;
}
