package com.dofast.module.trade.dal.dataobject.calculatemodel;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * 计价模型 DO
 *
 * @author 惠智造
 */
@TableName("trade_calculate_model")
@KeySequence("trade_calculate_model_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculateModelDO extends BaseDO {

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
     * 计价类型id
     */
    private Integer categoryId;
    /**
     * 表单
     */
    private Long formId;
    /**
     * 表达式类型
     */
    private String type;
    /**
     * 表达式
     */
    private String expression;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;

}
