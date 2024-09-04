package com.dofast.module.hr.dal.dataobject.tradecommission;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工资提成 DO
 *
 * @author 惠智造
 */
@TableName("hr_tradecommission")
@KeySequence("hr_tradecommission_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradecommissionDO extends BaseDO {

    /**
     * 提成明细id
     */
    @TableId
    private Integer id;
    /**
     * 提成类型
     */
    private String type;
    /**
     * 科目类型
     */
    private String signType;
    /**
     * 产品类型
     */
    private String saleType;
    /**
     * 业务
     */
    private Integer trade;
    /**
     * 合同
     */
    private Integer contract;
    /**
     * 账号
     */
    private String account;
    /**
     * 业绩额度
     */
    private BigDecimal contribution;
    /**
     * 提成比例
     */
    private BigDecimal rate;
    /**
     * 提成金额
     */
    private BigDecimal amount;
    /**
     * 描述
     */
    private String desc;

}
