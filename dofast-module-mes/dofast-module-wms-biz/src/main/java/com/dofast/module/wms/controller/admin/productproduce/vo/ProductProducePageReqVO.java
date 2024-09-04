package com.dofast.module.wms.controller.admin.productproduce.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 产品产出记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductProducePageReqVO extends PageParam {

    @Schema(description = "生产工单ID", example = "22688")
    private Long workorderId;

    @Schema(description = "生产工单编码")
    private String workorderCode;

    @Schema(description = "生产工单名称", example = "李四")
    private String workorderName;

    @Schema(description = "生产任务ID", example = "27736")
    private Long taskId;

    @Schema(description = "生产任务编号")
    private String taskCode;

    @Schema(description = "生产任务名称", example = "芋艿")
    private String taskName;

    @Schema(description = "工作站ID", example = "7905")
    private Long workstationId;

    @Schema(description = "工作站编号")
    private String workstationCode;

    @Schema(description = "工作站名称", example = "李四")
    private String workstationName;

    @Schema(description = "工序ID", example = "2279")
    private Long processId;

    @Schema(description = "工序编号")
    private String processCode;

    @Schema(description = "工序名称", example = "张三")
    private String processName;

    @Schema(description = "生产日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] produceDate;

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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "来源类型")
    private String orderSource;

}
