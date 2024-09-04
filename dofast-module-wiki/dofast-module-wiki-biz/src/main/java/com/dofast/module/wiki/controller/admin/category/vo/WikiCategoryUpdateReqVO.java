package com.dofast.module.wiki.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 首页的分类更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WikiCategoryUpdateReqVO extends WikiCategoryBaseVO {

    @Schema(description = "种类id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18920")
    @NotNull(message = "种类id不能为空")
    private Long id;

}
