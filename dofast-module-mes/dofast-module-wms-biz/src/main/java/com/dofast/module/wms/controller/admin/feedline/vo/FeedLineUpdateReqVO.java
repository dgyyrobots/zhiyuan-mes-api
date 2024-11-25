package com.dofast.module.wms.controller.admin.feedline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 上料详情更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FeedLineUpdateReqVO extends FeedLineBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14669")
    @NotNull(message = "行ID不能为空")
    private Long id;

}
