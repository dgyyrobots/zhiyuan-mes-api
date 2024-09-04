package com.dofast.module.hr.controller.admin.salarycommission.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 绩效工资 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class SalarycommissionBaseVO {

    @Schema(description = "工资", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "工资不能为空")
    private Integer salary;

    @Schema(description = "绩效类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "绩效类型不能为空")
    private String type;

    @Schema(description = "绩效标准", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "绩效标准不能为空")
    private String line;

    @Schema(description = "绩效金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "绩效金额不能为空")
    private BigDecimal amount;

    @Schema(description = "比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "比例不能为空")
    private BigDecimal rate;

    @Schema(description = "绩效", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "绩效不能为空")
    private BigDecimal commission;

    @Schema(description = "描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "描述不能为空")
    private String desc;

}
