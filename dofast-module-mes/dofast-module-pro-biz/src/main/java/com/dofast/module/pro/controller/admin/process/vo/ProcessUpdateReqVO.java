package com.dofast.module.pro.controller.admin.process.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产工序更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProcessUpdateReqVO extends ProcessBaseVO {

    @Schema(description = "工序ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16302")
    @NotNull(message = "工序ID不能为空")
    private Long id;

}
