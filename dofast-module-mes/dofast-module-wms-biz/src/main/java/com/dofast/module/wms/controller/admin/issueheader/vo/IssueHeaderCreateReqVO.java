package com.dofast.module.wms.controller.admin.issueheader.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产领料单头创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IssueHeaderCreateReqVO extends IssueHeaderBaseVO {
    @Schema(description = "领料单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4055")
    private Long id;

}
