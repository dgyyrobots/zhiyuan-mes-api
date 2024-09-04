package com.dofast.module.qms.controller.admin.template.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 检测模板创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplateCreateReqVO extends TemplateBaseVO {
    @Schema(description = "检测模板ID",  example = "4294")
    private Long id;
}
