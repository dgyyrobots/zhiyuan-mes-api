package com.dofast.module.iot.controller.admin.alert.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 设备告警 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AlertBaseVO {

    @Schema(description = "告警名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotNull(message = "告警名称不能为空")
    private String alertName;

    @Schema(description = "告警级别（1=提醒通知，2=轻微问题，3=严重警告）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "告警级别（1=提醒通知，2=轻微问题，3=严重警告）不能为空")
    private Byte alertLevel;

    @Schema(description = "产品ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20265")
    @NotNull(message = "产品ID不能为空")
    private Long productId;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotNull(message = "产品名称不能为空")
    private String productName;

    @Schema(description = "触发器", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "触发器不能为空")
    private String triggers;

    @Schema(description = "执行动作", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "执行动作不能为空")
    private String actions;

    @Schema(description = "告警状态（1-启动，2-停止）", example = "1")
    private Boolean status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}
