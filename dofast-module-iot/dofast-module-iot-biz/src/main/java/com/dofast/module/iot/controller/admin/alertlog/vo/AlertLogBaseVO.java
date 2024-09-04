package com.dofast.module.iot.controller.admin.alertlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 设备告警日志 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AlertLogBaseVO {

    @Schema(description = "告警名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "告警名称不能为空")
    private String alertName;

    @Schema(description = "告警级别（1=提醒通知，2=轻微问题，3=严重警告）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "告警级别（1=提醒通知，2=轻微问题，3=严重警告）不能为空")
    private Byte alertLevel;

    @Schema(description = "处理状态(1=不需要处理,2=未处理,3=已处理)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "处理状态(1=不需要处理,2=未处理,3=已处理)不能为空")
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

}
