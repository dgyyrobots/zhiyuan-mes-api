package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 财务流水 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CashTradeExcelVO {

    @ExcelProperty("资金明细id")
    private Long id;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @ExcelProperty("供应者")
    private Long depositor;

    @ExcelProperty("父账号")
    private Long parent;

    @ExcelProperty("产品")
    private Long product;

    @ExcelProperty("交易人")
    private Long trader;

    @ExcelProperty("订单")
    private Long order;

    @ExcelProperty("合同")
    private Long contract;

    @ExcelProperty("关联类型")
    private String relatedType;

    @ExcelProperty("关联id")
    private Long relatedId;

    @ExcelProperty("项目")
    private Long project;

    @ExcelProperty("投资")
    private Long investId;

    @ExcelProperty("贷款")
    private Long loanId;

    @ExcelProperty("转账")
    private Long transferId;

    @ExcelProperty("部门")
    private Long dept;

    @ExcelProperty(value = "类型", converter = DictConvert.class)
    @DictFormat("cash_trade_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String type;

    @ExcelProperty("金额")
    private BigDecimal money;

    @ExcelProperty("汇率")
    private BigDecimal exchangeRate;

    @ExcelProperty("当前额度")
    private String currency;

    @ExcelProperty("日期")
    private LocalDateTime date;

    @ExcelProperty("时间")
    private LocalTime time;

    @ExcelProperty("最后期限")
    private LocalDateTime deadline;

    @ExcelProperty("操作人")
    private String handlers;

    @ExcelProperty("分类")
    private String category;

    @Schema(description = "结果")
    private Integer isReturn;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
