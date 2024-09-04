package com.dofast.module.trade.dal.dataobject.order;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * 采集任务类型 DO
 *
 * @author 惠智造
 */
@TableName("trade_order_collection_type")
@KeySequence("trade_order_collection_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCollectionTypeDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 表单
     */
    private Long formId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 描述
     */
    private String description;
    /**
     * 搜索标记
     */
    private String searchTags;
    /**
     * 类型
     */
    private Integer sortCode;
}
