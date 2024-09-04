package com.dofast.module.mes.dal.dataobject.autocoderesult;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 编码生成记录 DO
 *
 * @author 芋道源码
 */
@TableName("mes_common_auto_code_result")
@KeySequence("mes_common_auto_code_result_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoCodeResultDO extends BaseDO {

    /**
     * 记录ID
     */
    @TableId
    private Long id;
    /**
     * 规则ID
     */
    private Long ruleId;
    /**
     * 生成日期时间
     */
    private String genDate;
    /**
     * 最后产生的序号
     */
    private Integer genIndex;
    /**
     * 最后产生的值
     */
    private String lastResult;
    /**
     * 最后产生的流水号
     */
    private Integer lastSerialNo;
    /**
     * 最后传入的参数
     */
    private String lastInputChar;
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
