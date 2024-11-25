package com.dofast.module.mes.controller.admin.interfacelog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 接口操作日志更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InterfaceLogUpdateReqVO extends InterfaceLogBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14566")
    @NotNull(message = "ID不能为空")
    private Long id;

}
