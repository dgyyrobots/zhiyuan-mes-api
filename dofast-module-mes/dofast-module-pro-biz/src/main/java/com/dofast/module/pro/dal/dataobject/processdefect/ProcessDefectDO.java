package com.dofast.module.pro.dal.dataobject.processdefect;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工序异常缺陷名称 DO
 *
 * @author 惠智造
 */
@TableName("pro_process_defect")
@KeySequence("pro_process_defect_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDefectDO extends BaseDO {

    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 工序编码
     */
    private String processCode;
    /**
     * 缺陷名称
     */
    private String defectName;

}
