package com.dofast.module.cmms.controller.admin.dvchecksubject.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 点检项目更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvCheckSubjectUpdateReqVO extends DvCheckSubjectBaseVO {

    @Schema(description = "流水号", requiredMode = Schema.RequiredMode.REQUIRED, example = "9611")
    @NotNull(message = "流水号不能为空")
    private Long id;

}
