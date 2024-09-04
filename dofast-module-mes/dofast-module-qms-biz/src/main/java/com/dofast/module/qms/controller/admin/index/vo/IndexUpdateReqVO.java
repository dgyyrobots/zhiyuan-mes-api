package com.dofast.module.qms.controller.admin.index.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 检测项更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IndexUpdateReqVO extends IndexBaseVO {

    @Schema(description = "检测项ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20679")
    @NotNull(message = "检测项ID不能为空")
    private Long id;

}
