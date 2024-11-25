package com.dofast.module.wms.controller.admin.issueheader.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 生产领料单头 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IssueHeaderRespVO extends IssueHeaderBaseVO {

    @Schema(description = "领料单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4055")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    //private List<Map<String, Object>> bomList;

}
