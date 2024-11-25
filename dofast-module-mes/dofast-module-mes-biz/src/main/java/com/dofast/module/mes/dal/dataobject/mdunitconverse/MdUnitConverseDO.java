package com.dofast.module.mes.dal.dataobject.mdunitconverse;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 单位换算 DO
 *
 * @author 惠智造
 */
@TableName("mes_md_unit_converse")
@KeySequence("mes_md_unit_converse_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MdUnitConverseDO extends BaseDO {

    /**
     * 单位ID
     */
    @TableId
    private Long id;
    /**
     * 原编码
     */
    private String measureCode;
    /**
     * 原单位数量
     */
    private BigDecimal originCount;
    /**
     * 转换单位
     */
    private String converseCode;
    /**
     * 转换数量
     */
    private BigDecimal converseCount;

}
