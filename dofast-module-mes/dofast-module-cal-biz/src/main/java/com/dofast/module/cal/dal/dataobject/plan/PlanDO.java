package com.dofast.module.cal.dal.dataobject.plan;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 排班计划 DO
 *
 * @author 惠智造
 */
@TableName("cal_plan")
@KeySequence("cal_plan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDO extends BaseDO {

    /**
     * 计划ID
     */
    @TableId
    private Long id;
    /**
     * 计划编号
     */
    private String planCode;
    /**
     * 计划名称
     */
    private String planName;
    /**
     * 班组类型
     */
    private String calendarType;
    /**
     * 开始日期
     */
    private LocalDateTime startDate;
    /**
     * 结束日期
     */
    private LocalDateTime endDate;
    /**
     * 轮班方式
     *
     * 枚举 {@link TODO mes_shift_method 对应的类}
     */
    private String shiftType;
    /**
     * 倒班方式
     *
     * 枚举 {@link TODO mes_shift_type 对应的类}
     */
    private String shiftMethod;
    /**
     * 数
     */
    private Integer shiftCount;
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
