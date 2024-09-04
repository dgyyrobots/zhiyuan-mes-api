package com.dofast.module.hr.controller.admin.employeeeducation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 员工教育培训经历 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class EmployeeEducationBaseVO {

    @Schema(description = "学校", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "学校不能为空")
    private String educationSchool;

    @Schema(description = "专业", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "专业不能为空")
    private String educationSpecialty;

    @Schema(description = "所获荣誉", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "所获荣誉不能为空")
    private String educationHonor;

    @Schema(description = "教育开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "教育开始时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime educationStarttime;

    @Schema(description = "教育结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "教育结束时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime educationEndtime;

    @Schema(description = "员工id", requiredMode = Schema.RequiredMode.REQUIRED, example = "23518")
    @NotNull(message = "员工id不能为空")
    private Long employeeId;

}
