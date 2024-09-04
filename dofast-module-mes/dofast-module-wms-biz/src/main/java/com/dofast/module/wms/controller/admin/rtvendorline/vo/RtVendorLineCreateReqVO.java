package com.dofast.module.wms.controller.admin.rtvendorline.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 供应商退货行创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtVendorLineCreateReqVO extends RtVendorLineBaseVO {
    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21759")
    private Long id;
}
