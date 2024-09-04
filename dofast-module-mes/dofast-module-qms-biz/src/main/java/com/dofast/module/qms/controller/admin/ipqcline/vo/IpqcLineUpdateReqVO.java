package com.dofast.module.qms.controller.admin.ipqcline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 过程检验单行更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IpqcLineUpdateReqVO extends IpqcLineBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26211")
    @NotNull(message = "记录ID不能为空")
    private Long id;

}
