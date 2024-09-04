package com.dofast.module.trade.controller.admin.bpm.mixin.vo;

import com.dofast.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 销售订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MixinOrderBpmPageReqVO extends PageParam {

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "销售单编码")
    private String saleNo;

    @Schema(description = "商城订单编码")
    private String tradeOrderNo;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "业务员")
    private Integer saler;

    @Schema(description = "业务员姓名")
    private String salerName;

    @Schema(description = "结算方式")
    private Integer settlementMethod;

    @Schema(description = "预计交付时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] estimatedDeliveryTime;

    @Schema(description = "订单总金额", example = "25753")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private BigDecimal[] price;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    private List<Long> ids;

    @Schema(description = "是否打印（0：未打印，1已打印）")
    private String isPrint;
}
