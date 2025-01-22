package com.dofast.module.wms.controller.admin.rtissue.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产退料单头创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtIssueCreateReqVO extends RtIssueBaseVO {
    @Schema(description = "退料单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26320")
    private Long id;
    // 虚拟字段
    // 上料信息
    private List<Map<String, Object>> issuelineList;
    // 退料信息
    private List<Map<String, Object>> rtissuelineList;
}
