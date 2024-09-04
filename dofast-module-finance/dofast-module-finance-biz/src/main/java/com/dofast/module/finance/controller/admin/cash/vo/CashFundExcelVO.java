package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 财务退款 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CashFundExcelVO {

    @ExcelProperty("退款明细id")
    private Long id;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @ExcelProperty(value = "退款类型", converter = DictConvert.class)
    @DictFormat("cash_fund_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String type;

    @ExcelProperty("退款原因")
    private String origin;

    @ExcelProperty("退款父id")
    private Integer parent;

    @ExcelProperty("业务")
    private Long trader;

    @ExcelProperty("合同")
    private Long contract;

    @ExcelProperty("订单")
    private Long order;

    @ExcelProperty("批次")
    private Long batch;

    @ExcelProperty("应退金额")
    private BigDecimal deserved;

    @ExcelProperty("实退金额")
    private BigDecimal actual;

    @ExcelProperty("余额")
    private BigDecimal balance;

    @ExcelProperty(value = "客户", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String custom;

    @ExcelProperty("描述")
    private String desc;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
