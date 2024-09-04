package com.dofast.module.trade.controller.admin.calculate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 计价记录 Excel 导出 Request VO，参数和 CalculateRecordPageReqVO 是一致的")
@Data
public class CalculateRecordExportReqVO {

    @Schema(description = "类型", example = "22899")
    private Long typeId;

    @Schema(description = "结果")
    private String result;

    @Schema(description = "操作系统")
    private String os;

    @Schema(description = "客户端")
    private String client;

    @Schema(description = "设备信息")
    private String device;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
