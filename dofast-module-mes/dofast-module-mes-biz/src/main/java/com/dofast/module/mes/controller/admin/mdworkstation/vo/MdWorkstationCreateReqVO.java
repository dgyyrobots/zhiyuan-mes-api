package com.dofast.module.mes.controller.admin.mdworkstation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 工作站创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdWorkstationCreateReqVO extends MdWorkstationBaseVO {
    @Schema(description = "工作站ID",  example = "43")
    private Long id;
}
