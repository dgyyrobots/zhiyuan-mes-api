package com.dofast.module.wms.controller.admin.rtissue.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 生产退料单头 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtIssueRespVO extends RtIssueBaseVO {

    @Schema(description = "退料单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26320")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    // 上料信息
    private List<Map<String, Object>> issuelineList;
    // 退料信息
    private List<Map<String, Object>> rtissuelineList;

}
