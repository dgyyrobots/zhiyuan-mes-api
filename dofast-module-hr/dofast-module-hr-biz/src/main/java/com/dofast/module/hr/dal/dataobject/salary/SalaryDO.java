package com.dofast.module.hr.dal.dataobject.salary;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工资总 DO
 *
 * @author 惠智造
 */
@TableName("hr_salary")
@KeySequence("hr_salary_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDO extends BaseDO {

    /**
     * 工资明细id
     */
    @TableId
    private Integer id;
    /**
     * 订单流程编码
     */
    private String orderFlowNo;
    /**
     * 明细编码
     */
    private String detailNo;
    /**
     * sn
     */
    private String sn;
    /**
     * 月份
     *
     * 枚举 {@link TODO common_months 对应的类}
     */
    private String month;
    /**
     * 账户
     */
    private String account;
    /**
     * 所属公司
     */
    private Integer company;
    /**
     * 部门
     */
    private Integer dept;
    /**
     * 基本工资
     */
    private BigDecimal basic;
    /**
     * 绩效
     */
    private BigDecimal performance;
    /**
     * 红利
     */
    private BigDecimal bonus;
    /**
     * 津贴
     */
    private BigDecimal allowance;
    /**
     * 免税金额
     */
    private BigDecimal exemption;
    /**
     * 扣减
     */
    private BigDecimal deduction;
    /**
     * 奖励
     */
    private BigDecimal deserved;
    /**
     * 实际收入
     */
    private BigDecimal actual;
    /**
     * 公司SSF
     */
    private BigDecimal companySsf;
    /**
     * 公司HPF
     */
    private BigDecimal companyHpf;
    /**
     * 可纳税所得额
     */
    private BigDecimal curTaxableIncome;
    /**
     * 应纳税收入
     */
    private BigDecimal taxableIncome;
    /**
     * 应付税款
     */
    private BigDecimal taxPayable;
    /**
     * 已纳税
     */
    private BigDecimal taxPaid;
    /**
     * 税额
     */
    private BigDecimal tax;
    /**
     * 审核人
     */
    private String confirmer;
    /**
     * 审核时间
     */
    private LocalDateTime confirmTime;
    /**
     * 授权人
     */
    private String granter;
    /**
     * 授权时间
     */
    private LocalDateTime grantTime;
    /**
     * 描述
     */
    private String desc;
    /**
     * 状态
     */
    private String status;

}
