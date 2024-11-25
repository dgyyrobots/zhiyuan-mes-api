package com.dofast.module.mes.controller.admin.mdproductbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品BOM关系更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdProductBomUpdateReqVO extends MdProductBomBaseVO {

    @Schema(description = "流水号", requiredMode = Schema.RequiredMode.REQUIRED, example = "29402")
    @NotNull(message = "流水号不能为空")
    private Long id;

}
