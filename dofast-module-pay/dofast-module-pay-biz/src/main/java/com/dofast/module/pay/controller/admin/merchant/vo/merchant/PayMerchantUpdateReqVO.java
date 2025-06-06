package com.dofast.module.pay.controller.admin.merchant.vo.merchant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 支付商户信息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayMerchantUpdateReqVO extends PayMerchantBaseVO {

    @Schema(description = "商户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商户编号不能为空")
    private Long id;

}
