package com.dofast.module.system.controller.admin.expresscompany.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 租户物流公司 Excel 导出 Request VO，参数和 ExpressCompanyPageReqVO 是一致的")
@Data
public class ExpressCompanyExportReqVO {

    @Schema(description = "物流公司id", example = "16106")
    private Integer companyId;

    @Schema(description = "物流公司名称", example = "王五")
    private String companyName;

    @Schema(description = "logo")
    private String logo;

    @Schema(description = "打印内容")
    private String contentJson;

    @Schema(description = "背景图")
    private String backgroundImage;

    @Schema(description = "打印字体")
    private String fontSize;

    @Schema(description = "宽度")
    private String width;

    @Schema(description = "高度")
    private String height;

    @Schema(description = "真实尺寸（mm）与显示尺寸（px）的比例")
    private BigDecimal scale;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "是否支持电子面单（0不支持  1支持）")
    private Byte isElectronicsheet;

    @Schema(description = "编码（默认）")
    private String expressNo;

    @Schema(description = "编码（点三）")
    private String expressNoDiansan;

    @Schema(description = "编码（快递100）")
    private String expressNoKd100;

    @Schema(description = "编码（菜鸟）")
    private String expressNoCainiao;

    @Schema(description = "电子面单打印风格")
    private String printStyle;

}
