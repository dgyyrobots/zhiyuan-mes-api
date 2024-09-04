package com.dofast.module.mes.controller.admin.Autocoderule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 编码生成规则更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AutoCodeRuleUpdateReqVO extends AutoCodeRuleBaseVO {

    @Schema(description = "规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19616")
    @NotNull(message = "规则ID不能为空")
    private Long id;

}
