package com.dofast.module.system.controller.admin.user.vo.usersFace;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 用户人脸数据创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UsersFaceCreateReqVO extends UsersFaceBaseVO {

}
