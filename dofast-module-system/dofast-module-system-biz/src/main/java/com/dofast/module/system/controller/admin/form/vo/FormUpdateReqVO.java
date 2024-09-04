package com.dofast.module.system.controller.admin.form.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 系统表单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormUpdateReqVO extends FormBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31657")
    @NotNull(message = "ID不能为空")
    private Integer id;

    @Schema(description = "背景图", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "背景图不能为空")
    private String background;

    @Schema(description = "标签宽度")
    private Integer labelWidth;

    @Schema(description = "表单项")
    private String fields;

    @Schema(description = "表单扩展项")
    private String extend;

}
