package com.dofast.module.pro.dal.dataobject.feedbackdefect;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 报工缺陷 DO
 *
 * @author 惠智造
 */
@TableName("pro_feedback_defect")
@KeySequence("pro_feedback_defect_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDefectDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 报工单ID
     */
    private String feedbackId;
    /**
     * 生产任务编号
     */
    private String taskCode;
    /**
     * 缺陷项名称
     */
    private String defectName;
    /**
     * 缺陷项id
     */
    private Long defectId;
    /**
     * 起始米数
     */
    private String startMeter;
    /**
     * 结束米数
     */
    private String endMeter;
    /**
     * 缺陷米数
     */
    private String defectMeter;

}
