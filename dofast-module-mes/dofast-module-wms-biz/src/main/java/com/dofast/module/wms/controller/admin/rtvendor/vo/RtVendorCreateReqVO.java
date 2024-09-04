package com.dofast.module.wms.controller.admin.rtvendor.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 供应商退货创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtVendorCreateReqVO extends RtVendorBaseVO {
    @Schema(description = "退货单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23729")
    private Long id;
}
