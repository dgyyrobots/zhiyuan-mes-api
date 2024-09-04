package com.dofast.module.trade.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Schema(description = "管理后台 - 采集任务类型 Response VO")
@Data
@ToString(callSuper = true)
public class OrderCollectionTypeSimpleRespVO  {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32095")
    private Long id;

    @Schema(description = "任务类型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "表单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "32095")
    private Long formId;

    @Schema(description = "搜索标记", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "applied:已搜索")
    private String searchTags;

}
