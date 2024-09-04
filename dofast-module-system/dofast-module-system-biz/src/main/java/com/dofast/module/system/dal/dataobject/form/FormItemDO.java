package com.dofast.module.system.dal.dataobject.form;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 字段 DO
 *
 * @author 惠智造
 */
@TableName("system_form_item")
@KeySequence("system_form_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormItemDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Integer id;
    /**
     * 父级
     */
    private Long parentId;
    /**
     * 父级
     */
    private Long formId;
    /**
     * 名称
     */
    private String name;
    /**
     * 字段名
     */
    private String field;
    /**
     * 验证器
     *
     * 枚举 {@link TODO system_form_validator 对应的类}
     */
    private String validator;
    /**
     * 类型
     *
     * 枚举 {@link TODO system_form_item_type 对应的类}
     */
    private String type;
    /**
     * 其他参数
     */
    private String props;
    /**
     * 组件参数
     */
    private String componentProps;
    /**
     * 字典
     */
    private String dictType;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 排序
     */
    private Integer sort;

}
