package com.dofast.module.iot.controller.admin.group.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 设备分组 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class GroupBaseVO {

    @Schema(description = "分组名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotNull(message = "分组名称不能为空")
    private String groupName;

    @Schema(description = "分组排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分组排序不能为空")
    private Byte groupOrder;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31061")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotNull(message = "用户昵称不能为空")
    private String userName;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}
