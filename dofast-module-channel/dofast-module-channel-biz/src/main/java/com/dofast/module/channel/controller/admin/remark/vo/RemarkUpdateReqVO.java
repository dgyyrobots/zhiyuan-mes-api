package com.dofast.module.channel.controller.admin.remark.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 订单备注更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RemarkUpdateReqVO extends RemarkBaseVO {

    @Schema(description = "自增ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23860")
    @NotNull(message = "自增ID不能为空")
    private Long id;

}
