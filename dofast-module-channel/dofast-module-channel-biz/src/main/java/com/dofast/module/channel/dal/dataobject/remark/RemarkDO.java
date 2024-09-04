package com.dofast.module.channel.dal.dataobject.remark;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单备注 DO
 *
 * @author 惠智造
 */
@TableName("order_remark")
@KeySequence("order_remark_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemarkDO extends BaseDO {

    /**
     * 自增ID
     */
    @TableId
    private Long id;
    /**
     * 关联的商城订单的ID
     */
    private Long tradeOrderId;
    /**
     * 排序权重
     */
    @Builder.Default
    private Integer sortCode = 0;
    /**
     * 备注
     */
    private String remark;
    /**
     * 种类
     */
    private String type;
    /**
     * 销售订单ID
     */
    private Long salId;

}
