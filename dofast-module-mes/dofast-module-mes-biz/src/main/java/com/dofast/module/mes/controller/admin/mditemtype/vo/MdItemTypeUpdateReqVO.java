package com.dofast.module.mes.controller.admin.mditemtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 物料产品分类更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdItemTypeUpdateReqVO extends MdItemTypeBaseVO {

    @Schema(description = "产品物料类型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2895")
    @NotNull(message = "产品物料类型ID不能为空")
    private Long id;

}
