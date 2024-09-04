package com.dofast.module.trade.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 采集任务 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class OrderCollectionBaseVO {

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotNull(message = "名称不能为空")
    private String name;

    @Schema(description = "任务类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "30210")
    @NotNull(message = "任务类型不能为空")
    private Long typeId;

    @Schema(description = "订单", requiredMode = Schema.RequiredMode.REQUIRED, example = "16516")
    @NotNull(message = "订单不能为空")
    private Long orderId;

    @Schema(description = "记录", example = "6096")
    private Long recordId;

    @Schema(description = "记录者")
    private Long recorder;

    @Schema(description = "记录时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime recordTime;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private String status;

}
