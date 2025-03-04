package com.dofast.module.wms.controller.admin.electroformheader.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 制版房领料单头 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ElectroformHeaderBaseVO {

    @Schema(description = "制版领料单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "制版领料单编号不能为空")
    private String issueCode;

    @Schema(description = "制版领料单名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "制版领料单名称不能为空")
    private String issueName;

    @Schema(description = "领料日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime issueDate;

    @Schema(description = "单据状态", example = "1")
    private String status;

    @Schema(description = "生产设备名称", example = "李四")
    private String machineryName;

    @Schema(description = "生产设备编码")
    private String machineryCode;

    @Schema(description = "生产设备ID", example = "27745")
    private Long machineryId;

}
