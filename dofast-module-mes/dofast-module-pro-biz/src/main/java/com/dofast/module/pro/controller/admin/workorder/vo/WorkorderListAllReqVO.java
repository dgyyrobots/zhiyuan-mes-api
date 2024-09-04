package com.dofast.module.pro.controller.admin.workorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class WorkorderListAllReqVO {
    @Schema(description = "销售订单ID")
    private Long mixinOrderId;
}
