package com.dofast.module.iot.controller.admin.devicejob.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备定时分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceJobPageReqVO extends PageParam {

    @Schema(description = "cron执行表达式")
    private String cronExpression;

    @Schema(description = "计划执行错误策略（1立即执行 2执行一次 3放弃执行）")
    private String misfirePolicy;

    @Schema(description = "是否并发执行（0允许 1禁止）")
    private String concurrent;

    @Schema(description = "状态（0正常 1暂停）", example = "2")
    private String status;

    @Schema(description = "备注信息", example = "随便")
    private String remark;

    @Schema(description = "设备ID", example = "12168")
    private Long deviceId;

    @Schema(description = "设备编号")
    private String serialNumber;

    @Schema(description = "设备名称", example = "李四")
    private String deviceName;

    @Schema(description = "是否详细corn表达式（1=是，0=否）")
    private Boolean isAdvance;

    @Schema(description = "执行的动作集合")
    private String actions;

    @Schema(description = "任务类型（1=设备定时，2=设备告警，3=场景联动）", example = "2")
    private Boolean jobType;

    @Schema(description = "产品ID", example = "350")
    private Long productId;

    @Schema(description = "产品名称", example = "赵六")
    private String productName;

    @Schema(description = "场景联动ID", example = "31751")
    private Long sceneId;

    @Schema(description = "告警ID", example = "14692")
    private Long alertId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
