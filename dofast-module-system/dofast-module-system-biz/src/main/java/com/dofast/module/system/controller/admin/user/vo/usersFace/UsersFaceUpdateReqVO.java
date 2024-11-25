package com.dofast.module.system.controller.admin.user.vo.usersFace;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 用户人脸数据更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UsersFaceUpdateReqVO extends UsersFaceBaseVO {

    @Schema(description = "自增编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "25092")
    @NotNull(message = "自增编号不能为空")
    private Long id;

}
