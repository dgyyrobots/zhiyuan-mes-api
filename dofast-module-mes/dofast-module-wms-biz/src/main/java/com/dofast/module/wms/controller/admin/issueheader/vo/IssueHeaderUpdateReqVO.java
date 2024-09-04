package com.dofast.module.wms.controller.admin.issueheader.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产领料单头更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IssueHeaderUpdateReqVO extends IssueHeaderBaseVO {

    @Schema(description = "领料单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4055")
    @NotNull(message = "领料单ID不能为空")
    private Long id;

}
