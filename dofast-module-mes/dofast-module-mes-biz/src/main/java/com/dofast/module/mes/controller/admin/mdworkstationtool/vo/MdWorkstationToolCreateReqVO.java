package com.dofast.module.mes.controller.admin.mdworkstationtool.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 工装夹具资源创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdWorkstationToolCreateReqVO extends MdWorkstationToolBaseVO {
    @Schema(description = "记录ID", example = "14883")
    private Long id;
}
