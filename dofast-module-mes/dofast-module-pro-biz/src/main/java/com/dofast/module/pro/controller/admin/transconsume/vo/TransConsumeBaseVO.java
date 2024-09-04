package com.dofast.module.pro.controller.admin.transconsume.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 物料消耗记录 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TransConsumeBaseVO {

    @Schema(description = "流转单ID", example = "25666")
    private Long transOrderId;

    @Schema(description = "流转单编号")
    private String transOrderCode;

    @Schema(description = "生产任务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "379")
    @NotNull(message = "生产任务ID不能为空")
    private Long taskId;

    @Schema(description = "工作站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14055")
    @NotNull(message = "工作站ID不能为空")
    private Long workstationId;

    @Schema(description = "工序ID", example = "22526")
    private Long processId;

    @Schema(description = "生产工单ID", example = "17635")
    private Long workorderId;

    @Schema(description = "批次号")
    private String batchCode;

    @Schema(description = "被消耗单据ID", example = "27943")
    private Long sourceDocId;

    @Schema(description = "被消耗单据编号")
    private String sourceDocCode;

    @Schema(description = "被消耗单据类型", example = "2")
    private String sourceDocType;

    @Schema(description = "被消耗单据行ID", example = "32361")
    private Long sourceLineId;

    @Schema(description = "被消耗物料批次号")
    private String sourceBatchCode;

    @Schema(description = "被消耗产品物料ID", example = "13327")
    private Long itemId;

    @Schema(description = "被消耗产品物料编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "被消耗产品物料编码不能为空")
    private String itemCode;

    @Schema(description = "被消耗产品物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "被消耗产品物料名称不能为空")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "单位不能为空")
    private String unitOfMeasure;

    @Schema(description = "消耗数量")
    private BigDecimal quantityConsumed;

    @Schema(description = "消耗时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime consumeDate;

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

}
