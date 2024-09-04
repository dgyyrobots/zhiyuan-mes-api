package com.dofast.module.trade.controller.admin.mixinorderitem.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 销售的物料明细 Excel VO
 *
 * @author 惠智造
 */
@Data
public class MixinOrderItemExcelVO {

    @ExcelProperty("销售的物料ID")
    private Long id;

    @ExcelProperty("产品物料ID")
    private Long itemId;

    @ExcelProperty("产品物料编码")
    private String itemCode;

    @ExcelProperty("产品物料名称")
    private String itemName;

    @ExcelProperty("销售订单ID")
    private Long saleId;

    @ExcelProperty("销售单编码")
    private String saleNo;

    @ExcelProperty("标题")
    private String title;

    @ExcelProperty("单价")
    private BigDecimal singlePrice;

    @ExcelProperty("数量")
    private Integer num;

    @ExcelProperty("总价")
    private BigDecimal totalPrice;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
