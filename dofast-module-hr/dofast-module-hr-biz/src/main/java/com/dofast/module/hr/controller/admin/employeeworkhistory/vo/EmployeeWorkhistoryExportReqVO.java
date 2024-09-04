package com.dofast.module.hr.controller.admin.employeeworkhistory.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 员工工作经历 Excel 导出 Request VO，参数和 EmployeeWorkhistoryPageReqVO 是一致的")
@Data
public class EmployeeWorkhistoryExportReqVO {

    @Schema(description = "从业公司", example = "赵六")
    private String companyName;

    @Schema(description = "从业薪资")
    private Integer treatment;

    @Schema(description = "担任职位")
    private String treatmentPost;

    @Schema(description = "离职原因")
    private String reasonForLeave;

    @Schema(description = "员工id", example = "2602")
    private Long employeeId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
