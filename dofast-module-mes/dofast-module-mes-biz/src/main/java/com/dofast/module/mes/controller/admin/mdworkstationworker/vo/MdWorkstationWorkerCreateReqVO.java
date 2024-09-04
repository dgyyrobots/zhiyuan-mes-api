package com.dofast.module.mes.controller.admin.mdworkstationworker.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 人力资源创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdWorkstationWorkerCreateReqVO extends MdWorkstationWorkerBaseVO {
    @Schema(description = "记录ID", example = "15050")
    private Long id;
}
