package com.dofast.module.system.controller.admin.expresstemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 运费模板 Excel 导出 Request VO，参数和 ExpressTemplatePageReqVO 是一致的")
@Data
public class ExpressTemplateExportReqVO {

    @Schema(description = "模板名称", example = "芋艿")
    private String name;

    @Schema(description = "运费计算方式1.按件2重量", example = "1")
    private Byte priceType;

    @Schema(description = "是否为默认模板")
    private Boolean defaulted;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
