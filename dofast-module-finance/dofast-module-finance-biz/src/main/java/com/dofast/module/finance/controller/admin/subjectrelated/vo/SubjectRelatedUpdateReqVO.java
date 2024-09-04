package com.dofast.module.finance.controller.admin.subjectrelated.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 收支科目更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SubjectRelatedUpdateReqVO extends SubjectRelatedBaseVO {

    @Schema(description = "收支科目id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15040")
    @NotNull(message = "收支科目id不能为空")
    private Integer id;

}
