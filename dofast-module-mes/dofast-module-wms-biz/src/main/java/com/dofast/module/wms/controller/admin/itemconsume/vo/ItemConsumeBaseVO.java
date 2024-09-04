package com.dofast.module.wms.controller.admin.itemconsume.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
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
public class ItemConsumeBaseVO {

    @Schema(description = "生产工单ID", example = "26019")
    private Long workorderId;

    @Schema(description = "生产工单编码")
    private String workorderCode;

    @Schema(description = "生产工单名称", example = "芋艿")
    private String workorderName;

    @Schema(description = "生产任务ID", example = "13172")
    private Long taskId;

    @Schema(description = "生产任务编号")
    private String taskCode;

    @Schema(description = "生产任务名称", example = "芋艿")
    private String taskName;

    @Schema(description = "工作站ID", example = "21099")
    private Long workstationId;

    @Schema(description = "工作站编号")
    private String workstationCode;

    @Schema(description = "工作站名称", example = "王五")
    private String workstationName;

    @Schema(description = "工序ID", example = "2771")
    private Long processId;

    @Schema(description = "工序编号")
    private String processCode;

    @Schema(description = "工序名称", example = "张三")
    private String processName;

    @Schema(description = "消耗日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime consumeDate;

    @Schema(description = "单据状态", example = "1")
    private String status;

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

}
