package com.dofast.module.channel.controller.admin.salesorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 销售订单 Excel VO
 *
 * @author 惠智造
 */
@Data
public class SalesOrderExcelVO {

    @ExcelProperty("销售订单ID")
    private Long id;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @ExcelProperty("销售单编码")
    private String saleNo;

    @ExcelProperty("标题")
    private String title;

    @ExcelProperty("结算方式(1现结2月结3周结)")
    private Integer settlementMethod;

    @ExcelProperty("订单总金额")
    private BigDecimal price;

    @ExcelProperty("业务员id")
    private Integer saler;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
