package com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 计价分类 Excel 导出 Request VO，参数和 CalculateCategoryPageReqVO 是一致的")
@Data
public class CalculateCategoryExportReqVO {

    @Schema(description = "分类名称", example = "芋艿")
    private String name;

    @Schema(description = "备注", example = "随便")
    private String description;

    @Schema(description = "状态", example = "2")
    private Integer status;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
