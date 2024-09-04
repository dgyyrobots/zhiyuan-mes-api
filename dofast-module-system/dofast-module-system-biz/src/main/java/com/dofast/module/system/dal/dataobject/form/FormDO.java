package com.dofast.module.system.dal.dataobject.form;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 系统表单 DO
 *
 * @author 惠智造
 */
@TableName("system_form")
@KeySequence("system_form_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormDO extends BaseDO {

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
     * 描述
     */
    private String description;
    /**
     * 背景图
     */
    private String background;
    /**
     * 标签宽度
     */
    private Integer labelWidth;
    /**
     * 类型
     *
     * 枚举 {@link TODO system_form_type 对应的类}
     */
    private String type;
    /**
     * 状态
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private Integer status;

    /**
     * 表单项
     */
    private String fields;

    /**
     * 表单扩展数据
     */
    private String extend;

}
