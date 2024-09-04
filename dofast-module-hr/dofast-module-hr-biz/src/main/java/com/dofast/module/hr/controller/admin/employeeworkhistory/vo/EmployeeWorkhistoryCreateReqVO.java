package com.dofast.module.hr.controller.admin.employeeworkhistory.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 员工工作经历创建 Request VO")
@Data
@ToString(callSuper = true)
public class EmployeeWorkhistoryCreateReqVO  {


    @Schema(description = "从业公司", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotNull(message = "从业公司不能为空")
    private String companyName;

    @Schema(description = "从业薪资", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "从业薪资不能为空")
    private Integer treatment;

    @Schema(description = "担任职位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "担任职位不能为空")
    private String treatmentPost;

    @Schema(description = "离职原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "离职原因不能为空")
    private String reasonForLeave;

    @Schema(description = "员工id", example = "2602")
    private Long employeeId;
}
