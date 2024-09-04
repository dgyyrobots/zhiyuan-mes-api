package com.dofast.module.trade.dal.dataobject.calculate;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * 计价类型 DO
 *
 * @author 惠智造
 */
@TableName("trade_calculate_type")
@KeySequence("trade_calculate_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculateTypeDO extends BaseDO {

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
     * 备注
     */
    private String description;
    /**
     * 表单
     */
    private String formId;
    /**
     * 表达式类型
     *
     * 枚举 {@link TODO trade_calculate_type 对应的类}
     */
    private String type;
    /**
     * 表达式
     */
    private String expression;
    /**
     * 状态
     *
     * 枚举 {@link TODO trade_order_collection_status 对应的类}
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;

}
