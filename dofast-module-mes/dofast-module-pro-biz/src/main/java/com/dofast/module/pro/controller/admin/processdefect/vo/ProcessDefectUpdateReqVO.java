package com.dofast.module.pro.controller.admin.processdefect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 工序异常缺陷名称更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProcessDefectUpdateReqVO extends ProcessDefectBaseVO {

    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4847")
    @NotNull(message = "主键id不能为空")
    private Long id;

}
