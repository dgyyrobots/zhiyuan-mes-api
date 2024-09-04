package com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail.vo;

import com.dofast.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 财务发票明细分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashInvoiceDetailBpmPageReqVO extends PageParam {

    @Schema(description = "开票信息id")
    private Long invoice;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "项目")
    private String item;

    @Schema(description = "项目类型", example = "1")
    private String itemType;

    @Schema(description = "项目id", example = "28685")
    private Long itemId;

    @Schema(description = "模型")
    private String model;

    @Schema(description = "单位")
    private String unit;

    @Schema(description = "数量")
    private BigDecimal amount;

    @Schema(description = "价格", example = "30035")
    private BigDecimal price;

    @Schema(description = "金额")
    private BigDecimal money;

    @Schema(description = "税率")
    private Byte taxRate;

    @Schema(description = "税金")
    private BigDecimal taxMoney;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
