package com.dofast.module.trade.controller.admin.mixin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 销售订单 Excel 导出 Request VO，参数和 MixinOrderPageReqVO 是一致的")
@Data
public class MixinOrderExportReqVO {

    @Schema(description = "销售单编码")
    private String saleNo;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;


    @Schema(description = "标题")
    private String title;

    @Schema(description = "业务员")
    private Integer saler;

    @Schema(description = "业务员名称")
    private String salerName;

    @Schema(description = "结算方式")
    private Integer settlementMethod;

    @Schema(description = "预计交付时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] estimatedDeliveryTime;

    @Schema(description = "订单总金额", example = "25753")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private BigDecimal[] price;


    @Schema(description = "是否打印")
    private String isPrint;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
