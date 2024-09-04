package com.dofast.module.system.controller.admin.form.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 字段更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormItemUpdateReqVO extends FormItemBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22390")
    @NotNull(message = "ID不能为空")
    private Integer id;

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
