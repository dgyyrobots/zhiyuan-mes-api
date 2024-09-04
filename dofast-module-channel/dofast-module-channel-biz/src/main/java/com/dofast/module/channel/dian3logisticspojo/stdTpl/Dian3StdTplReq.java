package com.dofast.module.channel.dian3logisticspojo.stdTpl;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
@Schema(description = "Dian3 打印模板查询 Request VO")
public class Dian3StdTplReq {
    @Schema(description = "模板类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String templateType;

    @Schema(description = "打印模板的平台来源", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String refPlatformType;

    @Schema(description = "当前分页", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer pageNo;

    @Schema(description = "分页数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer pageSize = 100;
}
