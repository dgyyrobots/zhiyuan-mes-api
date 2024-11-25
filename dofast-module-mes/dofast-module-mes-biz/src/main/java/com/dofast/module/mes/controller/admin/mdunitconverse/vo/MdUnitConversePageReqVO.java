package com.dofast.module.mes.controller.admin.mdunitconverse.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 单位换算分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdUnitConversePageReqVO extends PageParam {

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
