package com.dofast.module.channel.dian3logisticspojo.offline;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "Dian3 线下订单获取电子面单 订单包裹信息列表 Response VO")
@Data
@ToString(callSuper = true)
public class Dian3OffLineResOrder {
    @Schema(description = "订单信息", example = "你猜", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private Dian3OffLineResOrderInfo orderInfo;

    @Schema(description = "包裹信息", example = "你猜", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private List<Dian3OffLineResPackages> packages;
}
