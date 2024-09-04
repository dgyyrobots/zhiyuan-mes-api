package com.dofast.module.channel.kndpojo.eorder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@Schema
@ToString(callSuper = true)
public class KDNEOrderRes {
    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String EBusinessID;

    @Schema(description = "成功与否", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private Boolean Success;

    @Schema(description = "返回编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String ResultCode;

    @Schema(description = "失败原因", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String Reason;

    @Schema(description = "订单信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private KDNEOrderResOrder Order;

    @Schema(description = "面单打印模板内容(html格式)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String PrintTemplate;

    @Schema(description = "唯一标识", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String UniquerRequestNumber;

    @Schema(description = "子单数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer SubCount;

    @Schema(description = "子单单号（数组形式）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String SubOrders;

    @Schema(description = "子单模板内容(html格式)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String SubPrintTemplates;

    @Schema(description = "签回单模板内容(html格式)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String SignBillPrintTemplate;
}
