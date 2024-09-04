package com.dofast.module.pro.controller.admin.routeproductbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品制程物料BOM更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RouteProductBomUpdateReqVO extends RouteProductBomBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7136")
    @NotNull(message = "记录ID不能为空")
    private Long id;

}
