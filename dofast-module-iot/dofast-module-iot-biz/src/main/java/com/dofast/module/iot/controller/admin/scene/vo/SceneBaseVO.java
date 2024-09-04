package com.dofast.module.iot.controller.admin.scene.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 场景联动 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class SceneBaseVO {

    @Schema(description = "场景名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "场景名称不能为空")
    private String sceneName;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31230")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "用户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "用户名称不能为空")
    private String userName;

    @Schema(description = "触发器", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "触发器不能为空")
    private String triggers;

    @Schema(description = "执行动作", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "执行动作不能为空")
    private String actions;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}
