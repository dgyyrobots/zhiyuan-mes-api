package com.dofast.module.trade.controller.admin.mixinorderitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 销售的物料明细 Excel 导出 Request VO，参数和 MixinOrderItemPageReqVO 是一致的")
@Data
public class MixinOrderItemExportReqVO {

    @Schema(description = "产品物料ID", example = "26593")
    private Long itemId;

    @Schema(description = "产品物料编码")
    private String itemCode;

    @Schema(description = "产品物料名称", example = "李四")
    private String itemName;

    @Schema(description = "销售订单ID", example = "5322")
    private Long saleId;

    @Schema(description = "销售单编码")
    private String saleNo;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "单价", example = "17831")
    private BigDecimal singlePrice;

    @Schema(description = "数量")
    private Integer num;

    @Schema(description = "总价", example = "17830")
    private BigDecimal totalPrice;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
