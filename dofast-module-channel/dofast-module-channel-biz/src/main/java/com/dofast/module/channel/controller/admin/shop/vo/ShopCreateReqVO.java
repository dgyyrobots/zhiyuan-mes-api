package com.dofast.module.channel.controller.admin.shop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 店铺授权创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopCreateReqVO extends ShopBaseVO {

    @Schema(description = "排序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序号不能为空")
    private Integer sortCode;

}
