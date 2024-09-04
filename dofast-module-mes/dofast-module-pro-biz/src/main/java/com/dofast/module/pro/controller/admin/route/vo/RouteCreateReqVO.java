package com.dofast.module.pro.controller.admin.route.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 工艺路线创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RouteCreateReqVO extends RouteBaseVO {
    @Schema(description = "工艺路线ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9100")
    private Long id;
}
