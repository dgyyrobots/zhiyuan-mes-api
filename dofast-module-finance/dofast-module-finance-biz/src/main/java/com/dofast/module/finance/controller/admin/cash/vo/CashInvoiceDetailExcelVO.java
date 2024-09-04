package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 财务发票明细 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CashInvoiceDetailExcelVO {

    @ExcelProperty("发票明细id")
    private Long id;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @ExcelProperty("开票信息id")
    private Long invoice;

    @ExcelProperty("项目")
    private String item;

    @ExcelProperty("项目类型")
    private String itemType;

    @ExcelProperty("项目id")
    private Long itemId;

    @ExcelProperty("模型")
    private String model;

    @ExcelProperty("单位")
    private String unit;

    @ExcelProperty("数量")
    private BigDecimal amount;

    @ExcelProperty("价格")
    private BigDecimal price;

    @ExcelProperty("金额")
    private BigDecimal money;

    @ExcelProperty("税率")
    private Byte taxRate;

    @ExcelProperty("税金")
    private BigDecimal taxMoney;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "状态")
    private String status;

}
