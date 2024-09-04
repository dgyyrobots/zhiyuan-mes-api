package com.dofast.module.system.controller.admin.expresscompanytemplate.vo;

import com.dofast.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 系统物流公司分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressCompanyTemplatePageReqVO extends PageParam {

    @Schema(description = "站点id", example = "24000")
    private Integer siteId;

    @Schema(description = "物流公司名称", example = "赵六")
    private String companyName;

    @Schema(description = "物流公司logo")
    private String logo;

    @Schema(description = "物流公司网址", example = "https://www.iocoder.cn")
    private String url;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "编码")
    private String expressNo;

    @Schema(description = "编码（快递100）")
    private String expressNoKd100;

    @Schema(description = "编码（菜鸟）")
    private String expressNoCainiao;

    @Schema(description = "打印内容")
    private String contentJson;

    @Schema(description = "背景图")
    private String backgroundImage;

    @Schema(description = "打印字体")
    private Integer fontSize;

    @Schema(description = "宽度")
    private Integer width;

    @Schema(description = "高度")
    private Integer height;

    @Schema(description = "真实尺寸（mm）与显示尺寸（px）的比例")
    private BigDecimal scale;

    @Schema(description = "是否支持电子面单（0不支持  1支持）")
    private Byte isElectronicsheet;

    @Schema(description = "电子面单打印风格")
    private String printStyle;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
