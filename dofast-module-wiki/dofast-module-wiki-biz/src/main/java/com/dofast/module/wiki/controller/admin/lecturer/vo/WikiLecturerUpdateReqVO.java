package com.dofast.module.wiki.controller.admin.lecturer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 讲师的信息	更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WikiLecturerUpdateReqVO extends WikiLecturerBaseVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "5395")
    @NotNull(message = "id不能为空")
    private Long id;

}
