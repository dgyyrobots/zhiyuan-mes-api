package com.dofast.module.pro.controller.admin.taskissue.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 生产任务投料 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TaskIssueBaseVO {

    @Schema(description = "生产任务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15068")
    @NotNull(message = "生产任务ID不能为空")
    private Long taskId;

    @Schema(description = "生产工单ID", example = "31032")
    private Long workorderId;

    @Schema(description = "工作站ID", example = "3570")
    private Long workstationId;

    @Schema(description = "单据ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18958")
    @NotNull(message = "单据ID不能为空")
    private Long sourceDocId;

    @Schema(description = "单据编号")
    private String sourceDocCode;

    @Schema(description = "单据类型")
    private String sourceDocType;

    @Schema(description = "投料批次")
    private String batchCode;

    @Schema(description = "行ID", example = "7541")
    private Long sourceLineId;

    @Schema(description = "产品物料ID", example = "24950")
    private Long itemId;

    @Schema(description = "产品物料编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "产品物料编码不能为空")
    private String itemCode;

    @Schema(description = "产品物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotNull(message = "产品物料名称不能为空")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "单位不能为空")
    private String unitOfMeasure;

    @Schema(description = "总的投料数量")
    private BigDecimal quantityIssued;

    @Schema(description = "当前可用数量")
    private BigDecimal quantityAvailable;

    @Schema(description = "当前使用数量")
    private BigDecimal quantityUsed;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

    public Long  getId(){return null;}
}
