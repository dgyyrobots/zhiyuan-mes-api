package com.dofast.module.trade.controller.admin.electronicsheetpackage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 电子面单 Excel 导出 Request VO，参数和 ElectronicsheetPackagePageReqVO 是一致的")
@Data
public class ElectronicsheetPackageExportReqVO {

    @Schema(description = "订单编码outerCode")
    private String orderNo;

    @Schema(description = "面单号")
    private String waybillCode;

    @Schema(description = "父面单号")
    private String parentWaybillCode;

    @Schema(description = "状态（0正常 -1不使用）", example = "2")
    private Byte status;

    @Schema(description = "电子面单模板")
    private String printTemplate;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
