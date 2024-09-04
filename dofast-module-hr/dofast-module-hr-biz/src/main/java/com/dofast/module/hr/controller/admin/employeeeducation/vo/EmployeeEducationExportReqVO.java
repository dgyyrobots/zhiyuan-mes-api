package com.dofast.module.hr.controller.admin.employeeeducation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 员工教育培训经历 Excel 导出 Request VO，参数和 EmployeeEducationPageReqVO 是一致的")
@Data
public class EmployeeEducationExportReqVO {

    @Schema(description = "学校")
    private String educationSchool;

    @Schema(description = "专业")
    private String educationSpecialty;

    @Schema(description = "所获荣誉")
    private String educationHonor;

    @Schema(description = "教育开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] educationStarttime;

    @Schema(description = "教育结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] educationEndtime;

    @Schema(description = "员工id", example = "23518")
    private Long employeeId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
