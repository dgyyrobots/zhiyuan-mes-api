package com.dofast.module.qms.controller.admin.oqc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 出货检验单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OqcRespVO extends OqcBaseVO {

    @Schema(description = "出货检验单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "43")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "出货检验单编号")
    private String oqcCode;

    @Schema(description = "订单编号")
    private String sourceCode;


}
