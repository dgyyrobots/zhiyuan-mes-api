package com.dofast.module.purchase.controller.admin.bpm.order.vo;

import com.dofast.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 采购订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderBpmPageReqVO extends PageParam {

    @Schema(description = "供应商", example = "29274")
    private Integer supplierId;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "供应商联系人")
    private String supplierContact;

    @Schema(description = "供应商联系人电话")
    private String supplierPhone;

    @Schema(description = "采购金额")
    private BigDecimal purchaseAmount;

    @Schema(description = "采购单号")
    private String poNo;

    @Schema(description = "付款类型", example = "2")
    private Integer paymentType;

    @Schema(description = "退货状态")
    private Integer returnGoods;

    @Schema(description = "审核类型", example = "1")
    private Integer processType;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "母批次号")
    private String parentBatchCode;

    @Schema(description = "流水号")
    private String serial;

    @Schema(description = "供应商编码")
    private String supplierCode;

    @Schema(description = "ERP入库单")
    private String warehousingCode;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
