package com.dofast.module.mes.controller.admin.mdproductsop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品SOP更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdProductSopUpdateReqVO extends MdProductSopBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9444")
    @NotNull(message = "记录ID不能为空")
    private Long id;

}
