package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 现金余额 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CashBalanceExcelVO {

    @ExcelProperty("余额明细id")
    private Long id;

    @ExcelProperty("资金账户")
    private Long depositor;

    @ExcelProperty("时间")
    private LocalDateTime date;

    @ExcelProperty("金额")
    private BigDecimal money;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
