package com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 电子面单 Excel 导出 Request VO，参数和 ExpressElectronicsheetPageReqVO 是一致的")
@Data
public class ExpressElectronicsheetExportReqVO {

    @Schema(description = "配送快递名字", example = "芋艿")
    private String companyName;

    @Schema(description = "类型", example = "1")
    private String type;

    @Schema(description = "配送信息")
    private String info;

    @Schema(description = "配送编码(快递鸟)")
    private String kdnCode;

    @Schema(description = "信息模板")
    private String template;

    @Schema(description = "快递配置信息")
    private String config;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
