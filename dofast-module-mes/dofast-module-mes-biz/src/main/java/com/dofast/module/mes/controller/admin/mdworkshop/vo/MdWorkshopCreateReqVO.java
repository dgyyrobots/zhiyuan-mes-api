package com.dofast.module.mes.controller.admin.mdworkshop.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 车间创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdWorkshopCreateReqVO extends MdWorkshopBaseVO {
    @Schema(description = "车间ID", example = "22987")
    private Long id;
}
