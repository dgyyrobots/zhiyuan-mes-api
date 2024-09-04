package com.dofast.module.mes.controller.admin.Autocodepart.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 编码生成规则组成创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AutoCodePartCreateReqVO extends AutoCodePartBaseVO {
    @Schema(description = "分段ID", example = "18078")
    private Long id;
}
