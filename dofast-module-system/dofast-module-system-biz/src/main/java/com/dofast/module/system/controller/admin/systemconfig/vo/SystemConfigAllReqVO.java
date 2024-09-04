package com.dofast.module.system.controller.admin.systemconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Schema(description = "管理后台 - 批量修改创建 Request VO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SystemConfigAllReqVO implements Serializable {

    @Schema(description = "应用端口关键字", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "应用端口关键字不能为空")
    private String key;

    @Schema(description = "数据集合", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Value> value;



}
