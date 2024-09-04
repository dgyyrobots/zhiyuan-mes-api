package com.dofast.module.mes.dal.dataobject.Autocoderule;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 编码生成规则 DO
 *
 * @author 芋道源码
 */
@TableName("mes_common_auto_code_rule")
@KeySequence("mes_common_auto_code_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoCodeRuleDO extends BaseDO {

    /**
     * 规则ID
     */
    @TableId
    private Long id;
    /**
     * 规则编码
     */
    private String ruleCode;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 描述
     */
    private String ruleDesc;
    /**
     * 最大长度
     */
    private Integer maxLength;
    /**
     * 是否补齐
     */
    private String isPadded;
    /**
     * 补齐字符
     */
    private String paddedChar;
    /**
     * 补齐方式
     */
    private String paddedMethod;
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
