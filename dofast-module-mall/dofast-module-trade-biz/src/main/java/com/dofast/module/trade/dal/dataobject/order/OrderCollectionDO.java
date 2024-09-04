package com.dofast.module.trade.dal.dataobject.order;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 采集任务 DO
 *
 * @author 惠智造
 */
@TableName("trade_order_collection")
@KeySequence("trade_order_collection_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCollectionDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 任务类型
     */
    private Long typeId;
    /**
     * 订单
     */
    private Long orderId;
    /**
     * 记录
     */
    private Long recordId;
    /**
     * 记录者
     */
    private Long recorder;
    /**
     * 记录时间
     */
    private LocalDateTime recordTime;
    /**
     * 状态
     */
    private String status;
    /**
     * 发布者
     */
    private Long publisher;

}
