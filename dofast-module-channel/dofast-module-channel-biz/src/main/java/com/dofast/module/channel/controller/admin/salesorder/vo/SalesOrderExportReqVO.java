package com.dofast.module.channel.controller.admin.salesorder.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 销售订单 Excel 导出 Request VO，参数和 SalesOrderPageReqVO 是一致的")
@Data
public class SalesOrderExportReqVO {

    @Schema(description = "销售单编码")
    private String saleNo;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "结算方式(1现结2月结3周结)")
    private Integer settlementMethod;

    @Schema(description = "订单总金额", example = "25849")
    private BigDecimal price;

    @Schema(description = "业务员id")
    private Integer saler;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
