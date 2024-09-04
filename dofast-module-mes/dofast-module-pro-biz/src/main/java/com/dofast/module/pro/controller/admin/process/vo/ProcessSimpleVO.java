package com.dofast.module.pro.controller.admin.process.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Schema(description = "管理后台 - 生产工序简单信息封装类 Response VO")
@Data
@ToString(callSuper = true)
public class ProcessSimpleVO {

    @Schema(description = "工序ID")
    private Long id;

    @Schema(description = "工序名称")
    private String name;
}
