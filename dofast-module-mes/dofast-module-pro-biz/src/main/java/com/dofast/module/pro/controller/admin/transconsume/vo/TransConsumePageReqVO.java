package com.dofast.module.pro.controller.admin.transconsume.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 物料消耗记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransConsumePageReqVO extends PageParam {

    @Schema(description = "流转单ID", example = "25666")
    private Long transOrderId;

    @Schema(description = "流转单编号")
    private String transOrderCode;

    @Schema(description = "生产任务ID", example = "379")
    private Long taskId;

    @Schema(description = "工作站ID", example = "14055")
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

    @Schema(description = "被消耗产品物料编码")
    private String itemCode;

    @Schema(description = "被消耗产品物料名称", example = "芋艿")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createTime;

}
