package com.dofast.module.trade.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 采集任务 Excel 导出 Request VO，参数和 OrderCollectionPageReqVO 是一致的")
@Data
public class OrderCollectionExportReqVO {

    @Schema(description = "名称", example = "张三")
    private String name;

    @Schema(description = "任务类型", example = "30210")
    private Long typeId;

    @Schema(description = "任务类型", example = "30210")
    private List<Long> typeIds;

    @Schema(description = "订单", example = "16516")
    private Long orderId;

    @Schema(description = "订单", example = "16516")
    private List<Long> orderIds;

    @Schema(description = "记录", example = "6096")
    private Long recordId;

    @Schema(description = "记录者")
    private Long recorder;

    @Schema(description = "记录时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] recordTime;

    @Schema(description = "状态", example = "2")
    private String status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "任务类型和状态集合")
    private List<String> tasks;
}
