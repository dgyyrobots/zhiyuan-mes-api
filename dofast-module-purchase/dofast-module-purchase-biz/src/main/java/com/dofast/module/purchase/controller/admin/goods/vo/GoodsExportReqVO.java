package com.dofast.module.purchase.controller.admin.goods.vo;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 采购商品明细 Excel 导出 Request VO，参数和 GoodsPageReqVO 是一致的")
@Data
public class GoodsExportReqVO {

    @Schema(description = "采购订单Id", example = "20055")
    private Integer purchaseId;

    @Schema(description = "商品编号")
    private String goodsNumber;

    @Schema(description = "商品名称", example = "王五")
    private String goodsName;

    @Schema(description = "商品规格")
    private String goodsSpecs;

    @Schema(description = "商品单位")
    private Integer company;

    @Schema(description = "采购单价")
    private Object monovalent;

    @Schema(description = "采购数量")
    private Integer quantity;

    @Schema(description = "税金")
    private Object taxes;

    @Schema(description = "采购总价")
    private Object total;

    @Schema(description = "商品分类名称", example = "芋艿")
    private String categoryName;

    @Schema(description = "品牌名称", example = "李四")
    private String brandName;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
