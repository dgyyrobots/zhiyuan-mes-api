package com.dofast.module.cal.controller.admin.holiday.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 节假日设置更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HolidayUpdateReqVO extends HolidayBaseVO {

    @Schema(description = "流水号id", requiredMode = Schema.RequiredMode.REQUIRED, example = "7058")
    @NotNull(message = "流水号id不能为空")
    private Long id;

}
