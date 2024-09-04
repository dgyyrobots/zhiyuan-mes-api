package com.dofast.module.pro.controller.admin.workorder.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产工单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkorderCreateReqVO extends WorkorderBaseVO {
    @Schema(description = "工单ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "20735")
    private Long id;

    @Schema(description = "工单ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "20735")
    private Long mixinOrderId;
}
