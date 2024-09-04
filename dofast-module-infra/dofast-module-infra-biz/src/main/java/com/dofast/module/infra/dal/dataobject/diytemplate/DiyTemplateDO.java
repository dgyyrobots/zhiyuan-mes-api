package com.dofast.module.infra.dal.dataobject.diytemplate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 自定义模板页面类型 DO
 *
 * @author 惠智造
 */
@TableName("diy_template")
@KeySequence("diy_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiyTemplateDO extends BaseDO {

    /**
     * 商城系统模板id
     */
    @TableId
    private Integer id;
    /**
     * 模板名称
     */
    private String title;
    /**
     * 模板标识
     */
    private String name;
    /**
     * 页面路径
     */
    private String page;
    /**
     * 插件标识
     */
    private String addonName;
    /**
     * 默认值
     */
    private String value;
    /**
     * 规则
     */
    private String rule;
    /**
     * 排序
     */
    private Integer sort;

}
