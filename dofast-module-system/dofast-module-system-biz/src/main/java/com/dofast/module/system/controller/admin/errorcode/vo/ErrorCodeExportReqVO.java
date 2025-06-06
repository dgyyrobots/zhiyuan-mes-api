package com.dofast.module.system.controller.admin.errorcode.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 错误码 Excel 导出 Request VO,参数和 InfErrorCodePageReqVO 是一致的")
@Data
public class ErrorCodeExportReqVO {

    @Schema(description = "错误码类型", example = "1")
    private Integer type;

    @Schema(description = "应用名", example = "dashboard")
    private String applicationName;

    @Schema(description = "错误码编码", example = "1234")
    private Integer code;

    @Schema(description = "错误码错误提示", example = "帅气")
    private String message;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @Schema(description = "创建时间")
    private LocalDateTime[] createTime;

}
