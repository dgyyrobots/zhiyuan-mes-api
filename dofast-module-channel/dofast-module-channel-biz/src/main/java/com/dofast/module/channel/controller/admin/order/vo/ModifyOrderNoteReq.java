package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 店铺订单修改备注与旗帜 Request VO")
@Data
@ToString(callSuper = true)
public class ModifyOrderNoteReq {
    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "154846487545455454783")
    @NotNull(message = "订单编号不能为空")
    private String refOid;

    @Schema(description = "店铺编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "15451")
    @NotNull(message = "店铺编码不能为空")
    private String posCode;

    @Schema(description = "卖家备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "加急发货")
    @NotNull(message = "卖家备注不能为空")
    private String memo;

    @Schema(description = "订单旗帜", requiredMode = Schema.RequiredMode.REQUIRED, example = "RED")
    @NotNull(message = "订单旗帜不能为空")
    private String flag;

    @Schema(description = "操作人，目前仅小红书平台需要", requiredMode = Schema.RequiredMode.REQUIRED)
    private String operator;
}
