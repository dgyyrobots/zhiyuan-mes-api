package com.dofast.module.iot.controller.admin.devicelog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 设备日志 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DeviceLogBaseVO {

    @Schema(description = "标识符", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "标识符不能为空")
    private String identity;

    @Schema(description = "类型（1=属性上报，2=调用功能，3=事件上报，4=设备升级，5=设备上线，6=设备离线）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "类型（1=属性上报，2=调用功能，3=事件上报，4=设备升级，5=设备上线，6=设备离线）不能为空")
    private Boolean logType;

    @Schema(description = "日志值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "日志值不能为空")
    private String logValue;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6568")
    @NotNull(message = "设备ID不能为空")
    private Long deviceId;

    @Schema(description = "设备名称", example = "李四")
    private String deviceName;

    @Schema(description = "设备编号")
    private String serialNumber;

    @Schema(description = "是否监测数据（1=是，0=否）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否监测数据（1=是，0=否）不能为空")
    private Byte isMonitor;

    @Schema(description = "模式(1=影子模式，2=在线模式，3=其他)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "模式(1=影子模式，2=在线模式，3=其他)不能为空")
    private Byte mode;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30421")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "用户昵称不能为空")
    private String userName;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}
