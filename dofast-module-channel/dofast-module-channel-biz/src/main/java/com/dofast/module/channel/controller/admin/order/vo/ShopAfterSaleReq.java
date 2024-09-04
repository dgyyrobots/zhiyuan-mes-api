package com.dofast.module.channel.controller.admin.order.vo;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;

@Schema(description = "管理后台 - 店铺售后查询 Request VO")
@Data
@ToString
public class ShopAfterSaleReq {
    @Schema(description = "返回页码； 默认1， 当前采用分页返回，数量和页数会一起传，如果不传，则采用 默认值",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1")
    private Integer pageNo = 1;

    @Schema(description = "返回数量；默认 100，最大 100", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "100")
    @Max(100)
    private Integer pageSize = 100;

    @Schema(description = "创建开始时间的时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "2021-02-24 00:00:00")
    private String startTime;

    @Schema(description = "创建结束时间的时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "2021-02-24 00:00:00")
    private String endTime;

    @Schema(description = "时间类型；默认1， 1:更新，2:创建", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1")
    private String timeType = "1";

    @Schema(description = "订单编号；多个以逗号分隔", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1877788889157228562,1877788889157228563")
    private String refOid;

    @Schema(description = "售后单编码; 多个以逗号分隔", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1877788889157228562,1877788889157228563")
    private String refAid;
}
