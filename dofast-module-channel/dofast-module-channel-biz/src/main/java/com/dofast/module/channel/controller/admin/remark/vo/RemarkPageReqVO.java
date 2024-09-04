package com.dofast.module.channel.controller.admin.remark.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 订单备注分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RemarkPageReqVO extends PageParam {

    @Schema(description = "关联的商城订单的ID", example = "2086")
    private Long tradeOrderId;

    @Schema(description = "排序权重")
    private Integer sortCode;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "种类", example = "1")
    private String type;

    @Schema(description = "销售订单ID", example = "20604")
    private Long salId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
