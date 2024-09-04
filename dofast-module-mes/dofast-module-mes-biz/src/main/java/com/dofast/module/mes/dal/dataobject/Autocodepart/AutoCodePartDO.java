package com.dofast.module.mes.dal.dataobject.Autocodepart;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 编码生成规则组成 DO
 *
 * @author 芋道源码
 */
@TableName("mes_common_auto_code_part")
@KeySequence("mes_common_auto_code_part_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoCodePartDO extends BaseDO {

    /**
     * 分段ID
     */
    @TableId
    private Long id;
    /**
     * 规则ID
     */
    private Long ruleId;
    /**
     * 分段序号
     */
    private Integer partIndex;
    /**
     * 分段类型，INPUTCHAR：输入字符，NOWDATE：当前日期时间，FIXCHAR：固定字符，SERIALNO：流水号
     */
    private String partType;
    /**
     * 分段编号
     */
    private String partCode;
    /**
     * 分段名称
     */
    private String partName;
    /**
     * 分段长度
     */
    private Integer partLength;
    /**
     * 格式化
     */
    private String dateFormat;
    /**
     * 输入字符
     */
    private String inputCharacter;
    /**
     * 固定字符
     */
    private String fixCharacter;
    /**
     * 流水号起始值
     */
    private Integer seriaStartNo;
    /**
     * 流水号步长
     */
    private Integer seriaStep;
    /**
     * 流水号当前值
     */
    private Integer seriaNowNo;
    /**
     * 流水号是否循环
     */
    private String cycleFlag;
    /**
     * 循环方式，YEAR：按年，MONTH：按月，DAY：按天，HOUR：按小时，MINITE：按分钟，OTHER：按传入字符变
     */
    private String cycleMethod;
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
