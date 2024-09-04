package com.dofast.module.cmms.dal.dataobject.dvchecksubject;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 点检项目 DO
 *
 * @author 芋道源码
 */
@TableName("cmms_dv_check_subject")
@KeySequence("cmms_dv_check_subject_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DvCheckSubjectDO extends BaseDO {

    /**
     * 流水号
     */
    @TableId
    private Long id;
    /**
     * 计划ID
     */
    private Long planId;
    /**
     * 项目ID
     */
    private Long subjectId;
    /**
     * 项目编码
     */
    private String subjectCode;
    /**
     * 项目名称
     */
    private String subjectName;
    /**
     * 项目类型
     */
    private String subjectType;
    /**
     * 项目内容
     */
    private String subjectContent;
    /**
     * 标准
     */
    private String subjectStandard;
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
