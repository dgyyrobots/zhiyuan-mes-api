package com.dofast.module.cal.dal.dataobject.shift;

import java.time.LocalDate;

import cn.hutool.core.date.DateTime;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 计划班次 DO
 *
 * @author 惠智造
 */
@TableName("cal_shift")
@KeySequence("cal_shift_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftDO extends BaseDO {

    /**
     * 班次ID
     */
    @TableId
    private Long id;
    /**
     * 计划ID
     */
    private Long planId;
    /**
     * 序号
     */
    private Integer orderNum;
    /**
     * 班次名称
     */
    private String shiftName;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
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
