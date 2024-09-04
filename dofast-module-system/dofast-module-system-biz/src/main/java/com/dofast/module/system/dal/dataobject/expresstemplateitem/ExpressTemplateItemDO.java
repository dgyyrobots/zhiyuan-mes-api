package com.dofast.module.system.dal.dataobject.expresstemplateitem;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 运费模板细节 DO
 *
 * @author 惠智造
 */
@TableName("system_express_template_item")
@KeySequence("system_express_template_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpressTemplateItemDO extends BaseDO {

    /**
     * 运费模板细节编号
     */
    @TableId
    private Long id;
    /**
     * 模板id
     */
    private Long templateId;
    /**
     * 可配送地址id序列
     */
    private String areaIds;
    /**
     * 起步计算标准（首重，首件）
     */
    private Integer startUnit;
    /**
     * 起步计算价格，单位（分）
     */
    private Integer startUnitPrice;
    /**
     * 续步计算标准（每件，每kg）
     */
    private Integer plusPerUnit;
    /**
     * 续步计算价格，单位（分）
     */
    private Integer plusPerUnitPrice;
    /**
     * 运费计算方式
     *
     * 枚举 {@link TODO express_cal_type 对应的类}
     */
    private Byte priceType;

}
