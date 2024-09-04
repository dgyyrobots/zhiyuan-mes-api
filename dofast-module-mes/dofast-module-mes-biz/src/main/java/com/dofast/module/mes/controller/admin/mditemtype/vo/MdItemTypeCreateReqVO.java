package com.dofast.module.mes.controller.admin.mditemtype.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 物料产品分类创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdItemTypeCreateReqVO extends MdItemTypeBaseVO {
    @Schema(description = "产品物料类型ID", example = "2895")
    private Long id;

}
