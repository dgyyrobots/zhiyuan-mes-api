package com.dofast.module.qms.controller.admin.oqc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 出货检验单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class OqcBaseVO {

    @Schema(description = "出货检验单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "出货检验单编号不能为空")
    private String oqcCode;

    @Schema(description = "出货检验单名称", example = "赵六")
    private String oqcName;

    @Schema(description = "检验模板ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22519")
    private Long templateId;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15420")
    @NotNull(message = "客户ID不能为空")
    private Long clientId;

    @Schema(description = "客户编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "客户编码不能为空")
    private String clientCode;

    @Schema(description = "客户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotNull(message = "客户名称不能为空")
    private String clientName;

    @Schema(description = "批次号")
    private String batchCode;

    @Schema(description = "产品物料ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14491")
    @NotNull(message = "产品物料ID不能为空")
    private Long itemId;

    @Schema(description = "产品物料编码")
    private String itemCode;

    @Schema(description = "产品物料名称", example = "李四")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位")
    private String unitOfMeasure;

    @Schema(description = "最低检测数")
    private BigDecimal quantityMinCheck;

    @Schema(description = "最大不合格数")
    private BigDecimal quantityMaxUnqualified;

    @Schema(description = "发货数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发货数量不能为空")
    private BigDecimal quantityOut;

    @Schema(description = "本次检测数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "本次检测数量不能为空")
    private BigDecimal quantityCheck;

    @Schema(description = "不合格数")
    private BigDecimal quantityUnqualified;

    @Schema(description = "合格数量")
    private BigDecimal quantityQuanlified;

    @Schema(description = "致命缺陷率")
    private BigDecimal crRate;

    @Schema(description = "严重缺陷率")
    private BigDecimal majRate;

    @Schema(description = "轻微缺陷率")
    private BigDecimal minRate;

    @Schema(description = "致命缺陷数量")
    private BigDecimal crQuantity;

    @Schema(description = "严重缺陷数量")
    private BigDecimal majQuantity;

    @Schema(description = "轻微缺陷数量")
    private BigDecimal minQuantity;

    @Schema(description = "检测结果")
    private String checkResult;

    @Schema(description = "出货日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime outDate;

    @Schema(description = "检测日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime inspectDate;

    @Schema(description = "检测人员")
    private String inspector;

    @Schema(description = "单据状态", example = "1")
    private String status;

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

    @Schema(description = "附件")
    private String adjuncts;

    public Long getId(){
        return null;
    }
}
