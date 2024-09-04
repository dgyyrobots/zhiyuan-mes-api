package com.dofast.module.infra.dal.dataobject.diytemplatecategory;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 自定义模板分类 DO
 *
 * @author 惠智造
 */
@TableName("diy_template_category")
@KeySequence("diy_template_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiyTemplateCategoryDO extends BaseDO {

    /**
     * 商城系统模板分类id
     */
    @TableId
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 上级分类id
     */
    private Integer pid;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 状态（是否展示）
     */
    private Integer state;
    /**
     * 排序
     */
    private Integer sort;

}
