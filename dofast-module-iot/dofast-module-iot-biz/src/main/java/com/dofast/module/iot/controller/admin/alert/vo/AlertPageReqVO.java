package com.dofast.module.iot.controller.admin.alert.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备告警分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AlertPageReqVO extends PageParam {

    @Schema(description = "告警名称", example = "赵六")
    private String alertName;

    @Schema(description = "告警级别（1=提醒通知，2=轻微问题，3=严重警告）")
    private Byte alertLevel;

    @Schema(description = "产品ID", example = "20265")
    private Long productId;

    @Schema(description = "产品名称", example = "赵六")
    private String productName;

    @Schema(description = "触发器")
    private String triggers;

    @Schema(description = "执行动作")
    private String actions;

    @Schema(description = "告警状态（1-启动，2-停止）", example = "1")
    private Boolean status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
