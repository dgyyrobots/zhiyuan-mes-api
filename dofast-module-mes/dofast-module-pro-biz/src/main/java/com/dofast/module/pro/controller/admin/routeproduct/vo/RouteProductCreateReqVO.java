package com.dofast.module.pro.controller.admin.routeproduct.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品制程创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RouteProductCreateReqVO extends RouteProductBaseVO {
    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28991")
    private Long id;
}
