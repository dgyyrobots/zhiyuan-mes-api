package com.dofast.module.mes.controller.admin.mdunitconverse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 单位换算更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdUnitConverseUpdateReqVO extends MdUnitConverseBaseVO {

    @Schema(description = "单位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17696")
    @NotNull(message = "单位ID不能为空")
    private Long id;

}
