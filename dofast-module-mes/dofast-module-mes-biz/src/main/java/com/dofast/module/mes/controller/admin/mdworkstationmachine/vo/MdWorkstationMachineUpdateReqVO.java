package com.dofast.module.mes.controller.admin.mdworkstationmachine.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备资源更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdWorkstationMachineUpdateReqVO extends MdWorkstationMachineBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25735")
    @NotNull(message = "记录ID不能为空")
    private Long id;

}
