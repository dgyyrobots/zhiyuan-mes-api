package com.dofast.module.iot.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品分类更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryUpdateReqVO extends CategoryBaseVO {

    @Schema(description = "产品分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26309")
    @NotNull(message = "产品分类ID不能为空")
    private Long categoryId;

}
