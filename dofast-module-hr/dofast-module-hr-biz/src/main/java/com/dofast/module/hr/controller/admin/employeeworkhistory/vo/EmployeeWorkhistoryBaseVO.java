package com.dofast.module.hr.controller.admin.employeeworkhistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 员工工作经历 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class EmployeeWorkhistoryBaseVO {

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

    @Schema(description = "员工id", requiredMode = Schema.RequiredMode.REQUIRED, example = "2602")
    @NotNull(message = "员工id不能为空")
    private Long employeeId;

}
