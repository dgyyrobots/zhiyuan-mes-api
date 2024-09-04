package com.dofast.module.cmms.controller.admin.dvsubject.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备点检保养项目更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvSubjectUpdateReqVO extends DvSubjectBaseVO {

    @Schema(description = "项目ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16116")
    @NotNull(message = "项目ID不能为空")
    private Long id;

}
