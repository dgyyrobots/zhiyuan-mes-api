package com.dofast.module.purchase.controller.admin.bpm.invoice.vo;

import com.dofast.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 采购入库单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvoiceBpmPageReqVO extends PageParam {

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "采购单号")
    private String purchaseOrderNum;

    @Schema(description = "入库单号")
    private String shipmentNum;

    @Schema(description = "订单", example = "12272")
    private Integer purchaseId;

    @Schema(description = "供应商", example = "31852")
    private Integer supplierId;

    @Schema(description = "付款类型(0应付账款1现金付款2预付款)", example = "2")
    private Integer paymentType;

    @Schema(description = "有无退货")
    private Integer isReturn;

    @Schema(description = "供应商联系人")
    private String supplieruser;

    @Schema(description = "供应商手机号")
    private String supplierphone;

    @Schema(description = "采购总价", example = "11462")
    private Object totalPrice;

    @Schema(description = "是否入库")
    private Integer isWarehousing;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;


}
