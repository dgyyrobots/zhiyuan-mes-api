package com.dofast.module.system.dal.dataobject.form;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 表单历史 DO
 *
 * @author 惠智造
 */
@TableName("system_form_record")
@KeySequence("system_form_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormRecordDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 计价规则
     */
    private Long formId;
    /**
     * 表单内容
     */
    private String value;

}
