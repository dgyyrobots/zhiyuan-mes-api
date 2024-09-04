package com.dofast.module.trade.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 采集任务类型 Excel 导出 Request VO，参数和 OrderCollectionTypePageReqVO 是一致的")
@Data
public class OrderCollectionTypeExportReqVO {

    @Schema(description = "名称", example = "李四")
    private String name;

    @Schema(description = "表单", example = "32737")
    private Long formId;

    @Schema(description = "描述", example = "随便")
    private String description;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
