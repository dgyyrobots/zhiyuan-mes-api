package com.dofast.module.purchase.controller.admin.refund.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 采购退货 Excel 导出 Request VO，参数和 RefundPageReqVO 是一致的")
@Data
public class RefundExportReqVO {

    @Schema(description = "订单", example = "22411")
    private Integer purchaseId;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "订单单号")
    private String purchaseOrderNum;

    @Schema(description = "入库单号")
    private String shipmentNum;

    @Schema(description = "退货总额")
    private BigDecimal returnBonus;

    @Schema(description = "是否退货")
    private Integer isReturn;

    @Schema(description = "供应商", example = "14077")
    private Integer supplierId;

    @Schema(description = "付款类型", example = "1")
    private Integer paymentType;

    @Schema(description = "供应商联系人")
    private String supplieruser;

    @Schema(description = "供应商手机号")
    private String supplierphone;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
