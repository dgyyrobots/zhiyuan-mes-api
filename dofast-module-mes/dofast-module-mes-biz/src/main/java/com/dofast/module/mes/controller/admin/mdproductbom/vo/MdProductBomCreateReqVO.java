package com.dofast.module.mes.controller.admin.mdproductbom.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品BOM关系创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdProductBomCreateReqVO extends MdProductBomBaseVO {
    @Schema(description = "流水号", requiredMode = Schema.RequiredMode.REQUIRED, example = "16552")
    private Long id;

}
