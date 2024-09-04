package com.dofast.module.channel.controller.admin.remark.vo;

import lombok.*;

import java.util.*;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;

@Schema(description = "管理后台 - 订单备注创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RemarkCreateReqVO extends RemarkBaseVO {

    @Schema(description = "是否置顶")
    @NotNull(message = "是否置顶不能为空")
    private Boolean isTop = false;
}
