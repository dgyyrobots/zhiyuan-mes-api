package com.dofast.module.hr.controller.admin.commissionrule.vo;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 提成规则 Excel 导出 Request VO，参数和 CommissionrulePageReqVO 是一致的")
@Data
public class CommissionruleExportReqVO {

    @Schema(description = "月份")
    private String month;

    @Schema(description = "账户", example = "4454")
    private String account;

    @Schema(description = "薪资")
    private String sale;

    @Schema(description = "线")
    private String line;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
