package com.dofast.module.system.controller.admin.expresscompany.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 租户物流公司 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ExpressCompanyBaseVO {

    @Schema(description = "物流公司id", requiredMode = Schema.RequiredMode.REQUIRED, example = "16106")
    @NotNull(message = "物流公司id不能为空")
    private Integer companyId;

    @Schema(description = "物流公司名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "物流公司名称不能为空")
    private String companyName;

    @Schema(description = "logo", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "logo不能为空")
    private String logo;

    @Schema(description = "打印内容")
    private String contentJson;

    @Schema(description = "背景图", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "背景图不能为空")
    private String backgroundImage;

    @Schema(description = "打印字体", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "打印字体不能为空")
    private String fontSize;

    @Schema(description = "宽度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "宽度不能为空")
    private String width;

    @Schema(description = "高度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "高度不能为空")
    private String height;

    @Schema(description = "真实尺寸（mm）与显示尺寸（px）的比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "真实尺寸（mm）与显示尺寸（px）的比例不能为空")
    private BigDecimal scale;

    @Schema(description = "是否支持电子面单（0不支持  1支持）")
    private Byte isElectronicsheet;

    @Schema(description = "编码（默认）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编码（默认）不能为空")
    private String expressNo;

    @Schema(description = "编码（点三）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编码（点三）不能为空")
    private String expressNoDiansan;

    @Schema(description = "编码（快递100）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编码（快递100）不能为空")
    private String expressNoKd100;

    @Schema(description = "编码（菜鸟）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编码（菜鸟）不能为空")
    private String expressNoCainiao;

    @Schema(description = "电子面单打印风格")
    private String printStyle;

}
