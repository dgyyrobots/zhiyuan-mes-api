package com.dofast.module.hr.controller.admin.employeeworkhistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 员工工作经历 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeWorkhistoryRespVO extends EmployeeWorkhistoryBaseVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "26977")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
