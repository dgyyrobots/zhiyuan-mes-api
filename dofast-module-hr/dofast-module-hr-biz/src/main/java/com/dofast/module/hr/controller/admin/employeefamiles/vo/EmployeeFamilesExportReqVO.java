package com.dofast.module.hr.controller.admin.employeefamiles.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 员工家庭成员 Excel 导出 Request VO，参数和 EmployeeFamilesPageReqVO 是一致的")
@Data
public class EmployeeFamilesExportReqVO {

    @Schema(description = "家庭成员姓名", example = "芋艿")
    private String familesName;

    @Schema(description = "家庭成员关系")
    private String familesRealtion;

    @Schema(description = "家庭成员工作单位")
    private String familesWorkunit;

    @Schema(description = "家庭成员工作地区")
    private String familesWorkplace;

    @Schema(description = "家庭成员电话")
    private Integer familesPhone;

    @Schema(description = "员工id", example = "16304")
    private Long employeeId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
