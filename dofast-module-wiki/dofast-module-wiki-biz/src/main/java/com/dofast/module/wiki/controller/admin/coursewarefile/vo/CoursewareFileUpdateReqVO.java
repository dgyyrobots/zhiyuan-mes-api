package com.dofast.module.wiki.controller.admin.coursewarefile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 课件文件的保存地址更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CoursewareFileUpdateReqVO extends CoursewareFileBaseVO {

    @Schema(description = "文件id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19134")
    @NotNull(message = "文件id不能为空")
    private Long id;

}
