package com.dofast.module.mes.controller.admin.mdunitconverse.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 单位换算 Excel 导出 Request VO，参数和 MdUnitConversePageReqVO 是一致的")
@Data
public class MdUnitConverseExportReqVO {

    @Schema(description = "原编码")
    private String measureCode;

    @Schema(description = "原单位数量", example = "15969")
    private BigDecimal originCount;

    @Schema(description = "转换单位")
    private String converseCode;

    @Schema(description = "转换数量", example = "15766")
    private BigDecimal converseCount;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
