package com.dofast.module.mes.controller.admin.mdclient.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 客户更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdClientUpdateReqVO extends MdClientBaseVO {

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6498")
    @NotNull(message = "客户ID不能为空")
    private Long id;

}
