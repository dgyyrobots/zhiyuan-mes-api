package com.dofast.module.purchase.controller.admin.order.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 采购订单 Excel 导出 Request VO，参数和 OrderPageReqVO 是一致的")
@Data
public class OrderExportReqVO {

    @Schema(description = "供应商联系人")
    private String supplierContact;

    @Schema(description = "供应商联系人电话")
    private String supplierPhone;

    @Schema(description = "采购金额")
    private BigDecimal purchaseAmount;

    @Schema(description = "采购单号")
    private String poNo;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "流程实例的编号", example = "25492")
    private String processInstanceId;

    @Schema(description = "供应商id", example = "9696")
    private Integer supplierId;

    @Schema(description = "付款类型(0应付账款1现金付款2预付款)", example = "1")
    private Integer paymentType;

    @Schema(description = "退货状态(0有1无)")
    private Integer returnGoods;

    @Schema(description = "审核类型（0已审核，1未审核，3采购入库，3确认入库）", example = "2")
    private Integer processType;

    @Schema(description = "母批次号")
    private String parentBatchCode;

    @Schema(description = "流水号")
    private String serial;

    @Schema(description = "供应商编码")
    private String supplierCode;

    @Schema(description = "ERP入库单")
    private String warehousingCode;

}
