package com.dofast.module.system.controller.admin.form.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 字段创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormItemCreateReqVO extends FormItemBaseVO {

    @Schema(description = "父级", requiredMode = Schema.RequiredMode.REQUIRED, example = "11870")
    @NotNull(message = "父级不能为空")
    private Long parentId;

    @Schema(description = "其他参数", requiredMode = Schema.RequiredMode.REQUIRED)
    private String props;

    @Schema(description = "组件参数", requiredMode = Schema.RequiredMode.REQUIRED)
    private String componentProps;

    @Schema(description = "字典", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String dictType;

}
