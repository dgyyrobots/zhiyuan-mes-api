package com.dofast.module.mes.dal.dataobject.mdunitmeasure;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 单位 DO
 *
 * @author 芋道源码
 */
@TableName("mes_md_unit_measure")
@KeySequence("mes_md_unit_measure_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MdUnitMeasureDO extends BaseDO {

    /**
     * 单位ID
     */
    @TableId
    private Long id;
    /**
     * 单位编码
     */
    private String measureCode;
    /**
     * 单位名称
     */
    private String measureName;
    /**
     * 是否是主单位
     */
    private String primaryFlag;
    /**
     * 主单位ID
     */
    private Long primaryId;
    /**
     * 与主单位换算比例
     */
    private BigDecimal changeRate;
    /**
     * 是否启用
     */
    private String enableFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 预留字段1
     */
    private String attr1;
    /**
     * 预留字段2
     */
    private String attr2;
    /**
     * 预留字段3
     */
    private Integer attr3;
    /**
     * 预留字段4
     */
    private Integer attr4;

}
