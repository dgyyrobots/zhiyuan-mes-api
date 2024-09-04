package com.dofast.module.hr.controller.admin.tradecommission.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 工资提成 Excel VO
 *
 * @author 惠智造
 */
@Data
public class TradecommissionExcelVO {

    @ExcelProperty("提成明细id")
    private Integer id;

    @ExcelProperty("提成类型")
    private String type;

    @ExcelProperty("科目类型")
    private String signType;

    @ExcelProperty("产品类型")
    private String saleType;

    @ExcelProperty("业务")
    private Integer trade;

    @ExcelProperty("合同")
    private Integer contract;

    @ExcelProperty("账号")
    private String account;

    @ExcelProperty("业绩额度")
    private BigDecimal contribution;

    @ExcelProperty("提成比例")
    private BigDecimal rate;

    @ExcelProperty("提成金额")
    private BigDecimal amount;

    @ExcelProperty("描述")
    private String desc;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
