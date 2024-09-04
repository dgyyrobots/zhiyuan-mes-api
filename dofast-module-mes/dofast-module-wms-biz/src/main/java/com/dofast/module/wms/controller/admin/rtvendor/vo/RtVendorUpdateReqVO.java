package com.dofast.module.wms.controller.admin.rtvendor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 供应商退货更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtVendorUpdateReqVO extends RtVendorBaseVO {

    @Schema(description = "退货单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23729")
    @NotNull(message = "退货单ID不能为空")
    private Long id;

}
