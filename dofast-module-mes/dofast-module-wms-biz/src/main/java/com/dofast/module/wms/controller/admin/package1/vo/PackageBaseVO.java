package com.dofast.module.wms.controller.admin.package1.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 装箱单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class PackageBaseVO {

    @Schema(description = "父箱ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "8035")
    private Long parentId;

    @Schema(description = "所有父节点ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ancestors;

    @Schema(description = "装箱单编号")
    private String packageCode;

    @Schema(description = "条码ID", example = "17790")
    private Long barcodeId;

    @Schema(description = "条码内容")
    private String barcodeContent;

    @Schema(description = "条码地址", example = "https://www.iocoder.cn")
    private String barcodeUrl;

    @Schema(description = "装箱日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "装箱日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime packageDate;

    @Schema(description = "销售订单编号")
    private String soCode;

    @Schema(description = "发票编号")
    private String invoiceCode;

    @Schema(description = "客户ID", example = "16725")
    private Long clientId;

    @Schema(description = "客户编码")
    private String clientCode;

    @Schema(description = "客户名称", example = "赵六")
    private String clientName;

    @Schema(description = "客户简称")
    private String clientNick;

    @Schema(description = "箱长度")
    private BigDecimal packageLength;

    @Schema(description = "箱宽度")
    private BigDecimal packageWidth;

    @Schema(description = "箱高度")
    private BigDecimal packageHeight;

    @Schema(description = "尺寸单位")
    private String sizeUnit;

    @Schema(description = "净重")
    private BigDecimal netWeight;

    @Schema(description = "毛重")
    private BigDecimal crossWeight;

    @Schema(description = "重量单位")
    private String weightUnit;

    @Schema(description = "检查员用户名")
    private String inspector;

    @Schema(description = "检查员名称", example = "张三")
    private String inspectorName;

    @Schema(description = "状态", example = "1")
    private String status;

    @Schema(description = "是否生效")
    private String enableFlag;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

    public Long getId(){
        return null;
    }

}
