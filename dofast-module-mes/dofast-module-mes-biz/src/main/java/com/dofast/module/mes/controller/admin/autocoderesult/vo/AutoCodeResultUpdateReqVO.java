package com.dofast.module.mes.controller.admin.autocoderesult.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 编码生成记录更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AutoCodeResultUpdateReqVO extends AutoCodeResultBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31205")
    @NotNull(message = "记录ID不能为空")
    private Long id;

}
