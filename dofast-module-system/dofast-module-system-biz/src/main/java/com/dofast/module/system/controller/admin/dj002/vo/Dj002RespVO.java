package com.dofast.module.system.controller.admin.dj002.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Schema(description = "管理后台 - 系统地址信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Dj002RespVO extends Dj002BaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3117")
    private Integer id;

    @Schema(description = "创建时间")
    private LocalDate createTime;

}
