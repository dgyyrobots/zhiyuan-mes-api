package com.dofast.module.iot.controller.admin.devicelog.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备日志分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceLogPageReqVO extends PageParam {

    @Schema(description = "标识符")
    private String identity;

    @Schema(description = "类型（1=属性上报，2=调用功能，3=事件上报，4=设备升级，5=设备上线，6=设备离线）", example = "2")
    private Boolean logType;

    @Schema(description = "日志值")
    private String logValue;

    @Schema(description = "设备ID", example = "6568")
    private Long deviceId;

    @Schema(description = "设备名称", example = "李四")
    private String deviceName;

    @Schema(description = "设备编号")
    private String serialNumber;

    @Schema(description = "是否监测数据（1=是，0=否）")
    private Byte isMonitor;

    @Schema(description = "模式(1=影子模式，2=在线模式，3=其他)")
    private Byte mode;

    @Schema(description = "用户ID", example = "30421")
    private Long userId;

    @Schema(description = "用户昵称", example = "王五")
    private String userName;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
