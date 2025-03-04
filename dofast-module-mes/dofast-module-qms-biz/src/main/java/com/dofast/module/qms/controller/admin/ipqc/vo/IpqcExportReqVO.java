package com.dofast.module.qms.controller.admin.ipqc.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 过程检验单 Excel 导出 Request VO，参数和 IpqcPageReqVO 是一致的")
@Data
public class IpqcExportReqVO {

    @Schema(description = "检验单编号")
    private String ipqcCode;

    @Schema(description = "检验单名称", example = "张三")
    private String ipqcName;

    @Schema(description = "检验类型", example = "1")
    private String ipqcType;

    @Schema(description = "检验模板ID", example = "27300")
    private Long templateId;

    @Schema(description = "工单ID", example = "8284")
    private Long workorderId;

    @Schema(description = "工单编码")
    private String workorderCode;

    @Schema(description = "工单名称", example = "王五")
    private String workorderName;

    @Schema(description = "任务ID", example = "26269")
    private Long taskId;

    @Schema(description = "任务编号")
    private String taskCode;

    @Schema(description = "任务名称", example = "王五")
    private String taskName;

    @Schema(description = "工作站ID", example = "4377")
    private Long workstationId;

    @Schema(description = "工作站编号")
    private String workstationCode;

    @Schema(description = "工作站名称", example = "赵六")
    private String workstationName;

    @Schema(description = "工序ID", example = "26922")
    private Long processId;

    @Schema(description = "工序编码")
    private String processCode;

    @Schema(description = "工序名称", example = "芋艿")
    private String processName;

    @Schema(description = "产品物料ID", example = "27915")
    private Long itemId;

    @Schema(description = "产品物料编码")
    private String itemCode;

    @Schema(description = "产品物料名称", example = "芋艿")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位")
    private String unitOfMeasure;

    @Schema(description = "检测数量")
    private BigDecimal quantityCheck;

    @Schema(description = "不合格数")
    private BigDecimal quantityUnqualified;

    @Schema(description = "合格品数量")
    private BigDecimal quantityQualified;

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

    @Schema(description = "检测日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] inspectDate;

    @Schema(description = "检测人员")
    private String inspector;

    @Schema(description = "单据状态", example = "2")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "附件")
    private String adjuncts;

}
