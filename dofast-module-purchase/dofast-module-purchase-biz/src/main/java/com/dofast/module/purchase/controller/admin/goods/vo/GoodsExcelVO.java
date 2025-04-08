package com.dofast.module.purchase.controller.admin.goods.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import org.apache.poi.hpsf.Decimal;
import java.math.BigDecimal;
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

    @ExcelProperty("采购单价")
    private Object monovalent;

    @ExcelProperty("采购数量")
    private BigDecimal quantity;

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

    @ExcelProperty("商品单位")
    private String company;

    @ExcelProperty("采购订单号")
    private String poNo;

    @ExcelProperty("入库数量")
    private BigDecimal receiveNum;

    @ExcelProperty("入库单位")
    private String unitOfMeasure;

    @ExcelProperty("状态（0-未打印，1-已打印，2-已入库）")
    private Integer status;

    @ExcelProperty("收货时间")
    private LocalDateTime receiveTime;

    @ExcelProperty("批次")
    private String batchCode;

    @ExcelProperty("母批次")
    private String parentBatchCode;

    @ExcelProperty("供应商编号")
    private String vendorCode;

    @ExcelProperty("供应商名称")
    private String vendorName;

    @ExcelProperty("项次")
    private String consequence;

    @ExcelProperty("ERP数量")
    private BigDecimal erpNum;

    @ExcelProperty("ERP采购批次")
    private Integer purchaseBatch;

    @ExcelProperty("ERP采购批序")
    private Integer purchaseConsequence;

    @ExcelProperty("ERP采购分批序")
    private Integer purchaseBatchConsequence;

    @ExcelProperty("ERP采购单号")
    private String erpReceiveCode;

}
