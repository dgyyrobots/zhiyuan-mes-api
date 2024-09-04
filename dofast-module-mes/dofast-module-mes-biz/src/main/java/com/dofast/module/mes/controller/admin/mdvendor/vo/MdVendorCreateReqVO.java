package com.dofast.module.mes.controller.admin.mdvendor.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 供应商创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdVendorCreateReqVO extends MdVendorBaseVO {
    @Schema(description = "供应商ID", example = "26219")
    private Long id;

}
