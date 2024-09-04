package com.dofast.module.system.dal.dataobject.expresstemplate;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 运费模板 DO
 *
 * @author 惠智造
 */
@TableName("system_express_template")
@KeySequence("system_express_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpressTemplateDO extends BaseDO {

    /**
     * 运费模板编号
     */
    @TableId
    private Long id;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 运费计算方式1.按件2重量
     *
     * 枚举 {@link TODO express_cal_type 对应的类}
     */
    private Byte priceType;
    /**
     * 是否为默认模板
     */
    private Boolean defaulted;

}
