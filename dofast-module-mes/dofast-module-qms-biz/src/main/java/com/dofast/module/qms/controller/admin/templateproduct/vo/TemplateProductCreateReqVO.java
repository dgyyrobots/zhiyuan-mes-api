package com.dofast.module.qms.controller.admin.templateproduct.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 检测模板-产品创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplateProductCreateReqVO extends TemplateProductBaseVO {
    @Schema(description = "记录ID", example = "1257")
    private Long id;
}
