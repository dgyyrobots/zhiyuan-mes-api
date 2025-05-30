package com.dofast.module.system.controller.admin.user.vo.usersFace;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 用户人脸数据 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class UsersFaceBaseVO {

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30911")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "人脸地址")
    private String faceImg;

}
