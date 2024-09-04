package com.dofast.module.iot.controller.admin.productauthorize.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品授权码更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductAuthorizeUpdateReqVO extends ProductAuthorizeBaseVO {

    @Schema(description = "授权码ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29927")
    @NotNull(message = "授权码ID不能为空")
    private Long id;

}
