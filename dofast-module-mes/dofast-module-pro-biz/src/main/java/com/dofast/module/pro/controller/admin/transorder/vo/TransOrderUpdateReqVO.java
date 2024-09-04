package com.dofast.module.pro.controller.admin.transorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 流转单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransOrderUpdateReqVO extends TransOrderBaseVO {

    @Schema(description = "流转单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17276")
    @NotNull(message = "流转单ID不能为空")
    private Long id;

}
