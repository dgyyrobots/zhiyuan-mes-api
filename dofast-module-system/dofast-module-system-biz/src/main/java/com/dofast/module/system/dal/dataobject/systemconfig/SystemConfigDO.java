package com.dofast.module.system.dal.dataobject.systemconfig;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 参数配置 DO
 *
 * @author 惠智造
 */
@TableName("system_config")
@KeySequence("system_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemConfigDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 应用端口关键字
     */
    private String appModule;
    /**
     * 配置项关键字
     */
    private String configKey;
    /**
     * 配置值json
     */
    private String value;
    /**
     * 描述
     */
    private String configDesc;
    /**
     * 是否启用 1启用 0不启用
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private Byte isUse;
}
