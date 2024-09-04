package com.dofast.module.purchase.controller.admin.goods.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 采购商品明细 Excel VO
 *
 * @author 惠智造
 */
@Data
public class GoodsExcelVO {

    @ExcelProperty("id")
    private Integer id;

    @ExcelProperty("采购订单Id")
    private Integer purchaseId;

    @ExcelProperty("商品编号")
    private String goodsNumber;

    @ExcelProperty("商品名称")
    private String goodsName;

    @ExcelProperty("商品规格")
    private String goodsSpecs;

    @ExcelProperty("商品单位")
    private Integer company;

    @ExcelProperty("采购单价")
    private Object monovalent;

    @ExcelProperty("采购数量")
    private Integer quantity;

    @ExcelProperty("税金")
    private Object taxes;

    @ExcelProperty("采购总价")
    private Object total;

    @ExcelProperty("商品分类名称")
    private String categoryName;

    @ExcelProperty("品牌名称")
    private String brandName;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
