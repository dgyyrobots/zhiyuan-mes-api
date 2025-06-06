package com.dofast.module.trade.controller.app.aftersale.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Schema(description = "用户 App - 交易售后退回货物 Request VO")
@Data
public class AppTradeAfterSaleDeliveryReqVO {

    @Schema(description = "售后编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "售后编号不能为空")
    private Long id;

    @Schema(description = "退货物流公司编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "退货物流公司编号不能为空")
    private String logisticsId;

    @Schema(description = "退货物流单号", requiredMode = Schema.RequiredMode.REQUIRED, example = "SF123456789")
    @NotNull(message = "退货物流单号不能为空")
    private String logisticsNo;

    @Schema(description = "退货时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "退货时间不能为空")
    private LocalDateTime deliveryTime;

}
