package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 业务发票关联 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CashTradeInvoiceExcelVO {

    @ExcelProperty("渠道")
    private Long trade;

    @ExcelProperty("发票")
    private Long invoice;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
