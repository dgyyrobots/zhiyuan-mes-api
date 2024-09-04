package com.dofast.module.finance.dal.dataobject.subjectrelated;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 收支科目 DO
 *
 * @author a1
 */
@TableName("cash_subject_related")
@KeySequence("cash_subject_related_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRelatedDO extends BaseDO {

    /**
     * 收支科目id
     */
    @TableId
    private Integer id;
    /**
     * 科目名称
     */
    private String subjectName;
    /**
     * 科目类型(in收 out支)（0/1）
     */
    private Integer subjectType;

}
