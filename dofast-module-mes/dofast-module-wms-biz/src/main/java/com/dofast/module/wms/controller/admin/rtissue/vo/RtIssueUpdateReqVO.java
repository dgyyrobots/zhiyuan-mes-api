package com.dofast.module.wms.controller.admin.rtissue.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产退料单头更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtIssueUpdateReqVO extends RtIssueBaseVO {

    @Schema(description = "退料单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26320")
    @NotNull(message = "退料单ID不能为空")
    private Long id;

    // 虚拟字段
    // 退料信息
    private List<Map<String, Object>> rtissuelineList;

}
