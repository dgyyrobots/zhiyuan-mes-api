package com.dofast.module.member.controller.admin.address.vo;

import com.dofast.module.member.controller.app.address.vo.AppAddressBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "用户 APP - 用户收件地址创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AddressCreateReqVO extends AddressBaseVO {

    @Schema(description = "用户ID",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "用户ID不能为空")
    private Long userId;

}
