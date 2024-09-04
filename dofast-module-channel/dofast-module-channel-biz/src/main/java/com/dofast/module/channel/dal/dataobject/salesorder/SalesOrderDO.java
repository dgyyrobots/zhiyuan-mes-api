package com.dofast.module.channel.dal.dataobject.salesorder;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 销售订单 DO
 *
 * @author 惠智造
 */
@TableName("sales_order")
@KeySequence("sales_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderDO extends BaseDO {

    /**
     * 销售订单ID
     */
    @TableId
    private Long id;

    /**
     * 流程实例的编号
     */
    private String processInstanceId;

    /**
     * 销售单编码
     */
    private String saleNo;
    /**
     * 标题
     */
    private String title;
    /**
     * 结算方式(1现结2月结3周结)
     */
    private Integer settlementMethod;
    /**
     * 订单总金额
     */
    private BigDecimal price;
    /**
     * 业务员id
     */
    private Integer saler;

}
