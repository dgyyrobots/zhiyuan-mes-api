package com.dofast.module.iot.dal.dataobject.category;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品分类 DO
 *
 * @author 惠智造
 */
@TableName("iot_category")
@KeySequence("iot_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDO extends BaseDO {

    /**
     * 产品分类ID
     */
    @TableId
    private Long categoryId;
    /**
     * 产品分类名称
     */
    private String categoryName;
    /**
     * 是否系统通用（0-否，1-是）
     */
    private Boolean isSys;
    /**
     * 父级ID
     */
    private Long parentId;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    /**
     * 备注
     */
    private String remark;

}
