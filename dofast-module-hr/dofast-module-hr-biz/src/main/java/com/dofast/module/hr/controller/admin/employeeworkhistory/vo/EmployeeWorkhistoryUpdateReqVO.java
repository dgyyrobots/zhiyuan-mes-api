package com.dofast.module.hr.controller.admin.employeeworkhistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 员工工作经历更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeWorkhistoryUpdateReqVO extends EmployeeWorkhistoryBaseVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "26977")
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13788")
    @NotNull(message = "用户id不能为空")
    private Long userId;
}
