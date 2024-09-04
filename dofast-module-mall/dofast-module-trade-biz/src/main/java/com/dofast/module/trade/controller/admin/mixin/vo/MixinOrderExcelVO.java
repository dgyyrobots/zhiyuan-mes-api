package com.dofast.module.trade.controller.admin.mixin.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 销售订单 Excel VO
 *
 * @author 惠智造
 */
@Data
public class MixinOrderExcelVO {

    @ExcelProperty("销售订单ID")
    private Long id;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;


    @ExcelProperty("销售单编码")
    private String saleNo;

    @ExcelProperty("标题")
    private String title;

    @ExcelProperty("业务员")
    private Integer saler;

    @ExcelProperty("业务员姓名")
    private String salerName;

    @ExcelProperty("结算方式")
    private Integer settlementMethod;

    @ExcelProperty("预计交付时间")
    private LocalDateTime estimatedDeliveryTime;

    @ExcelProperty("订单总金额")
    private BigDecimal price;

    @ExcelProperty("是否打印")
    private String isPrint;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
