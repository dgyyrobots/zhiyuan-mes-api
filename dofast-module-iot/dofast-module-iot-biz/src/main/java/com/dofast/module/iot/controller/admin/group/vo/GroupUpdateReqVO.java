package com.dofast.module.iot.controller.admin.group.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备分组更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupUpdateReqVO extends GroupBaseVO {

    @Schema(description = "分组ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2505")
    @NotNull(message = "分组ID不能为空")
    private Long id;

}
