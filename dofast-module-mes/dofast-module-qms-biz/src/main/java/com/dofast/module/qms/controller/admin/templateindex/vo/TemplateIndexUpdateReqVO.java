package com.dofast.module.qms.controller.admin.templateindex.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 检测模板-检测项更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplateIndexUpdateReqVO extends TemplateIndexBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9719")
    @NotNull(message = "记录ID不能为空")
    private Long id;

}
