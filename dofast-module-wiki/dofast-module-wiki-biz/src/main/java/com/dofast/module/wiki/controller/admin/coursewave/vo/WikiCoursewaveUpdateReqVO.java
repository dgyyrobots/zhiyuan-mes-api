package com.dofast.module.wiki.controller.admin.coursewave.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 首页知识列表的信息	更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WikiCoursewaveUpdateReqVO extends WikiCoursewaveBaseVO {

    @Schema(description = "课件id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10293")
    @NotNull(message = "课件id不能为空")
    private Long id;

}
