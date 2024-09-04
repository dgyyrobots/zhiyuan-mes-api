package com.dofast.module.trade.dal.dataobject.calculatecategory;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * 计价分类 DO
 *
 * @author 惠智造
 */
@TableName("trade_calculate_category")
@KeySequence("trade_calculate_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculateCategoryDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Integer id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 备注
     */
    private String description;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;

}
