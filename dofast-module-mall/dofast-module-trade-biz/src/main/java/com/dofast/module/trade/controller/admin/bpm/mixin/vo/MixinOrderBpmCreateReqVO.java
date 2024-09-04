package com.dofast.module.trade.controller.admin.bpm.mixin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - 销售订单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MixinOrderBpmCreateReqVO extends MixinOrderBpmBaseVO {

    @Schema(description = "相关商品")
    private String goodsList;

    @Schema(description = "相关图片")
    private String pics;

    @Schema(description = "相关订单")
    @NotNull(message = "请传入关联订单")
    private List<Long> orderList;

}
