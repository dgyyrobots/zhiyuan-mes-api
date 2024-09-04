package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 资金账号 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CashDepositorExcelVO {

    @ExcelProperty("资金账户id")
    private Long id;

    @ExcelProperty(value = "账户类型", converter = DictConvert.class)
    @DictFormat("cash_depositor_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String type;

    @ExcelProperty("简称")
    private String abbr;

    @ExcelProperty("账户名称")
    private String title;

    @ExcelProperty("账户标签")
    private String tags;

    @ExcelProperty("供应者")
    private String provider;

    @ExcelProperty("开户银行")
    private String bank;

    @ExcelProperty("客户编码")
    private String customerNo;

    @ExcelProperty("银行账号")
    private String account;

    @ExcelProperty("联合银行编码")
    private String unionBankNo;

    @ExcelProperty("清算银行编码")
    private String clearingBankNo;

    @ExcelProperty(value = "是否公开", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String isPublic;

    @ExcelProperty("当前余额")
    private String currency;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("cash_depositor_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
