package com.dofast.module.hr.controller.admin.salary.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 工资总 Excel VO
 *
 * @author 惠智造
 */
@Data
public class SalaryExcelVO {

    @ExcelProperty("工资明细id")
    private Integer id;

    @ExcelProperty("订单流程编码")
    private String orderFlowNo;

    @ExcelProperty("明细编码")
    private String detailNo;

    @ExcelProperty("sn")
    private String sn;

    @ExcelProperty(value = "月份", converter = DictConvert.class)
    @DictFormat("common_months") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String month;

    @ExcelProperty("账户")
    private String account;

    @ExcelProperty("所属公司")
    private Integer company;

    @ExcelProperty("部门")
    private Integer dept;

    @ExcelProperty("基本工资")
    private BigDecimal basic;

    @ExcelProperty("绩效")
    private BigDecimal performance;

    @ExcelProperty("红利")
    private BigDecimal bonus;

    @ExcelProperty("津贴")
    private BigDecimal allowance;

    @ExcelProperty("免税金额")
    private BigDecimal exemption;

    @ExcelProperty("扣减")
    private BigDecimal deduction;

    @ExcelProperty("奖励")
    private BigDecimal deserved;

    @ExcelProperty("实际收入")
    private BigDecimal actual;

    @ExcelProperty("公司SSF")
    private BigDecimal companySsf;

    @ExcelProperty("公司HPF")
    private BigDecimal companyHpf;

    @ExcelProperty("可纳税所得额")
    private BigDecimal curTaxableIncome;

    @ExcelProperty("应纳税收入")
    private BigDecimal taxableIncome;

    @ExcelProperty("应付税款")
    private BigDecimal taxPayable;

    @ExcelProperty("已纳税")
    private BigDecimal taxPaid;

    @ExcelProperty("税额")
    private BigDecimal tax;

    @ExcelProperty("审核人")
    private String confirmer;

    @ExcelProperty("审核时间")
    private LocalDateTime confirmTime;

    @ExcelProperty("授权人")
    private String granter;

    @ExcelProperty("授权时间")
    private LocalDateTime grantTime;

    @ExcelProperty("描述")
    private String desc;

    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
