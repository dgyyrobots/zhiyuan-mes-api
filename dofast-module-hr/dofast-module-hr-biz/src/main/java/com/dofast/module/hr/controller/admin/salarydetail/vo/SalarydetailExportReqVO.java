package com.dofast.module.hr.controller.admin.salarydetail.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工资明细 Excel 导出 Request VO，参数和 SalarydetailPageReqVO 是一致的")
@Data
public class SalarydetailExportReqVO {

    @Schema(description = "工资")
    private Integer salary;

    @Schema(description = "科目")
    private String item;

    @Schema(description = "类型", example = "1")
    private String type;

    @Schema(description = "金额")
    private BigDecimal amount;

    @Schema(description = "描述")
    private String desc;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
