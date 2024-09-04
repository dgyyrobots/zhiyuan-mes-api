package com.dofast.module.report.controller.admin.PrintLog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 打印日志 Excel 导出 Request VO，参数和 PrintLogPageReqVO 是一致的")
@Data
public class PrintLogExportReqVO {

    @Schema(description = "打印编码",example = "AA")
    private String printCode;

    @Schema(description = "打印人", example = "张三")
    private String printName;

    @Schema(description = "打印类型", example = "1")
    private String printType;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
