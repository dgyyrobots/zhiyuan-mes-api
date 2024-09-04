package com.dofast.module.cal.controller.admin.holiday.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 节假日设置 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HolidayRespVO extends HolidayBaseVO {

    @Schema(description = "流水号id", requiredMode = Schema.RequiredMode.REQUIRED, example = "7058")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
