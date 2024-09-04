package com.dofast.module.system.controller.admin.expresstemplateitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 运费模板细节 Excel 导出 Request VO，参数和 ExpressTemplateItemPageReqVO 是一致的")
@Data
public class ExpressTemplateItemExportReqVO {

    @Schema(description = "模板id", example = "10134")
    private Long templateId;

    @Schema(description = "可配送地址id序列")
    private String areaIds;

    @Schema(description = "起步计算标准（首重，首件）")
    private Integer startUnit;

    @Schema(description = "起步计算价格，单位（分）", example = "25668")
    private Integer startUnitPrice;

    @Schema(description = "续步计算标准（每件，每kg）")
    private Integer plusPerUnit;

    @Schema(description = "续步计算价格，单位（分）", example = "14371")
    private Integer plusPerUnitPrice;

    @Schema(description = "运费计算方式", example = "2")
    private Byte priceType;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
