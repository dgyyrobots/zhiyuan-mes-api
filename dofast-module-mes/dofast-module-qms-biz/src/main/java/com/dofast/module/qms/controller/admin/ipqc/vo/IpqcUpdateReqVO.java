package com.dofast.module.qms.controller.admin.ipqc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 过程检验单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IpqcUpdateReqVO extends IpqcBaseVO {

    @Schema(description = "检验单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1549")
    @NotNull(message = "检验单ID不能为空")
    private Long id;

}
