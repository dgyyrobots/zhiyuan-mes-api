package com.dofast.module.iot.dal.dataobject.thingsmodeltemplate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 物模型模板 DO
 *
 * @author 惠智造
 */
@TableName("iot_things_model_template")
@KeySequence("iot_things_model_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThingsModelTemplateDO extends BaseDO {

    /**
     * 物模型ID
     */
    @TableId
    private Long id;
    /**
     * 物模型名称
     */
    private String templateName;
    /**
     * 标识符，产品下唯一
     */
    private String identifier;
    /**
     * 模型类别（1-属性，2-功能，3-事件）
     */
    private Boolean type;
    /**
     * 数据类型（integer、decimal、string、bool、array、enum）
     */
    private String datatype;
    /**
     * 数据定义
     */
    private String specs;
    /**
     * 是否系统通用（0-否，1-是）
     */
    private Boolean isSys;
    /**
     * 是否首页显示（0-否，1-是）
     */
    private Boolean isTop;
    /**
     * 是否实时监测（0-否，1-是）
     */
    private Boolean isMonitor;
    /**
     * 备注
     */
    private String remark;

}
