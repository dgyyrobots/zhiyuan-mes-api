package com.dofast.module.mes.controller.admin.mdvendor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 供应商更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdVendorUpdateReqVO extends MdVendorBaseVO {

    @Schema(description = "供应商ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26219")
    @NotNull(message = "供应商ID不能为空")
    private Long id;

}
