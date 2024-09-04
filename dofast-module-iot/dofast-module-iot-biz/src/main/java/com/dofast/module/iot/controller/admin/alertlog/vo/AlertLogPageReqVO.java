package com.dofast.module.iot.controller.admin.alertlog.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备告警日志分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AlertLogPageReqVO extends PageParam {

    @Schema(description = "告警名称", example = "芋艿")
    private String alertName;

    @Schema(description = "告警级别（1=提醒通知，2=轻微问题，3=严重警告）")
    private Byte alertLevel;

    @Schema(description = "处理状态(1=不需要处理,2=未处理,3=已处理)", example = "1")
    private Byte status;

    @Schema(description = "产品ID", example = "15923")
    private Long productId;

    @Schema(description = "产品名称", example = "芋艿")
    private String productName;

    @Schema(description = "设备ID", example = "8378")
    private Long deviceId;

    @Schema(description = "设备名称", example = "李四")
    private String deviceName;

    @Schema(description = "用户ID", example = "26237")
    private Long userId;

    @Schema(description = "用户昵称", example = "王五")
    private String userName;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "类型（1=告警，2=场景联动）", example = "2")
    private Byte type;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
