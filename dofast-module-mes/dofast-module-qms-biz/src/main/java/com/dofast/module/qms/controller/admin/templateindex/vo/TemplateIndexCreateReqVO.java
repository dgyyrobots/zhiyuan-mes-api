package com.dofast.module.qms.controller.admin.templateindex.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 检测模板-检测项创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplateIndexCreateReqVO extends TemplateIndexBaseVO {
    @Schema(description = "记录ID", example = "9719")
    private Long id;
}
