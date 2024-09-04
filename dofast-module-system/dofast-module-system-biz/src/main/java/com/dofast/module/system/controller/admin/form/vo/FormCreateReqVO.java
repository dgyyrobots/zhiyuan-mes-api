package com.dofast.module.system.controller.admin.form.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 系统表单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormCreateReqVO extends FormBaseVO {

    @Schema(description = "背景图", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "背景图不能为空")
    private String background;

    @Schema(description = "标签宽度")
    private Integer labelWidth;

}
