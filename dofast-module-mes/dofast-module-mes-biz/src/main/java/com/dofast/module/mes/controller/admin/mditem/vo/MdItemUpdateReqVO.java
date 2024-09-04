package com.dofast.module.mes.controller.admin.mditem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 物料产品更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdItemUpdateReqVO extends MdItemBaseVO {

    @Schema(description = "产品物料ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27681")
    @NotNull(message = "产品物料ID不能为空")
    private Long id;

    @Schema(description = "仓库编码")
    private String warehouseCode;

    @Schema(description = "库区编码")
    private String locationCode;

    @Schema(description = "库位编码")
    private String areaCode;

}
