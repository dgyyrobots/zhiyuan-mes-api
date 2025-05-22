package com.dofast.module.system.controller.admin.dj002.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 系统地址信息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Dj002UpdateReqVO extends Dj002BaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3117")
    @NotNull(message = "ID不能为空")
    private Integer id;

}
