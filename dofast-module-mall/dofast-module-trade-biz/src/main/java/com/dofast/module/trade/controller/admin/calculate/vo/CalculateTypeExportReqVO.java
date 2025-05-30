package com.dofast.module.trade.controller.admin.calculate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 计价类型 Excel 导出 Request VO，参数和 CalculateTypePageReqVO 是一致的")
@Data
public class CalculateTypeExportReqVO {

    @Schema(description = "名称", example = "赵六")
    private String name;

    @Schema(description = "表单", example = "13786")
    private String formId;

    @Schema(description = "表达式类型", example = "2")
    private String type;

    @Schema(description = "状态", example = "2")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
