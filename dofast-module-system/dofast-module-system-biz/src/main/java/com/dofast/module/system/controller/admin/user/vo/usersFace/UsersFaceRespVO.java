package com.dofast.module.system.controller.admin.user.vo.usersFace;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户人脸数据 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UsersFaceRespVO extends UsersFaceBaseVO {

    @Schema(description = "自增编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "25092")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
