package com.dofast.module.mes.controller.admin.mdworkstationtool.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 工装夹具资源更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdWorkstationToolUpdateReqVO extends MdWorkstationToolBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14883")
    @NotNull(message = "记录ID不能为空")
    private Long id;

}
