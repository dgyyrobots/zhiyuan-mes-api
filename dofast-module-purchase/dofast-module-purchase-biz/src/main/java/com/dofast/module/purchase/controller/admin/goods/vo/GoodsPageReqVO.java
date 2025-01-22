package com.dofast.module.purchase.controller.admin.goods.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.apache.poi.hpsf.Decimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 采购商品明细分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GoodsPageReqVO extends PageParam {

    @Schema(description = "采购订单Id", example = "20055")
    private Integer purchaseId;

    @Schema(description = "商品编号")
    private String goodsNumber;

    @Schema(description = "商品名称", example = "王五")
    private String goodsName;

    @Schema(description = "商品规格")
    private String goodsSpecs;

    @Schema(description = "采购单价")
    private Object monovalent;

    @Schema(description = "采购数量")
    private BigDecimal quantity;

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

    @Schema(description = "商品单位")
    private String company;

    @Schema(description = "采购订单号")
    private String poNo;

    @Schema(description = "入库数量")
    private BigDecimal receiveNum;

    @Schema(description = "入库单位")
    private String unitOfMeasure;

    @Schema(description = "状态（0-未打印，1-已打印，2-已入库）", example = "2")
    private Integer status;

    @Schema(description = "收货时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] receiveTime;

    @Schema(description = "批次")
    private String batchCode;

    @Schema(description = "母批次")
    private String parentBatchCode;

    @Schema(description = "项次")
    private String consequence;

    @Schema(description = "ERP数量")
    private BigDecimal erpNum;

}
