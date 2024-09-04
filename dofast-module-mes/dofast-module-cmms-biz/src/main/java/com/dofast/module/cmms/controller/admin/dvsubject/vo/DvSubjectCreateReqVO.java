package com.dofast.module.cmms.controller.admin.dvsubject.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备点检保养项目创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvSubjectCreateReqVO extends DvSubjectBaseVO {
    @Schema(description = "项目ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16116")
    private Long id;
}
