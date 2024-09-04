package com.dofast.module.channel.controller.admin.address.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Schema(description = "管理后台 - 交易客户更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AddressUpdateReqVO extends AddressBaseVO {

    @Schema(description = "订单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24370")
    @NotNull(message = "订单ID不能为空")
    private Integer id;

}
