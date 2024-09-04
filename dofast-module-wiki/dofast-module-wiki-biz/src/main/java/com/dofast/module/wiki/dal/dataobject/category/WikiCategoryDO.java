package com.dofast.module.wiki.dal.dataobject.category;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 首页的分类 DO
 *
 * @author 惠智造
 */
@TableName("wiki_category")
@KeySequence("wiki_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WikiCategoryDO extends BaseDO {

    /**
     * 种类id
     */
    @TableId
    private Long id;
    /**
     * 父id
     */
    private Long pid;
    /**
     * 类别等级
     */
    private Boolean categoryLevel;
    /**
     * 种类名称
     */
    private String name;

}
