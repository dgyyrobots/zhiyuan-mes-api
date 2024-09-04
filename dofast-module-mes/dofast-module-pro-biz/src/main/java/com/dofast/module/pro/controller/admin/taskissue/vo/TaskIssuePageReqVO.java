package com.dofast.module.pro.controller.admin.taskissue.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 生产任务投料分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskIssuePageReqVO extends PageParam {

    @Schema(description = "生产任务ID", example = "15068")
    private Long taskId;

    @Schema(description = "生产工单ID", example = "31032")
    private Long workorderId;

    @Schema(description = "工作站ID", example = "3570")
    private Long workstationId;

    @Schema(description = "单据ID", example = "18958")
    private Long sourceDocId;

    @Schema(description = "单据编号")
    private String sourceDocCode;
    @Schema(description = "单据编号")
    private String sourceDocType;
    @Schema(description = "投料批次")
    private String batchCode;

    @Schema(description = "行ID", example = "7541")
    private Long sourceLineId;

    @Schema(description = "产品物料ID", example = "24950")
    private Long itemId;

    @Schema(description = "产品物料编码")
    private String itemCode;

    @Schema(description = "产品物料名称", example = "张三")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createTime;

}
