package com.dofast.module.cmms.dal.dataobject.dvcheckplan;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备点检保养计划头 DO
 *
 * @author 芋道源码
 */
@TableName("cmms_dv_check_plan")
@KeySequence("cmms_dv_check_plan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DvCheckPlanDO extends BaseDO {

    /**
     * 计划ID
     */
    @TableId
    private Long id;
    /**
     * 计划编码
     */
    private String planCode;
    /**
     * 计划名称
     */
    private String planName;
    /**
     * 计划类型
     */
    private String planType;
    /**
     * 开始日期
     */
    private LocalDateTime startDate;
    /**
     * 结束日期
     */
    private LocalDateTime endDate;
    /**
     * 频率
     */
    private String cycleType;
    /**
     * 次数
     */
    private Integer cycleCount;
    /**
     * 状态
     */
    private String status;
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
