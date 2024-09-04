package com.dofast.module.cal.dal.dataobject.holiday;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 节假日设置 DO
 *
 * @author 惠智造
 */
@TableName("cal_holiday")
@KeySequence("cal_holiday_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HolidayDO extends BaseDO {

    /**
     * 流水号id
     */
    @TableId
    private Long id;
    /**
     * 日期
     */
    private LocalDateTime theDay;
    /**
     * 日期类型
     *
     * 枚举 {@link TODO jiaqi_type 对应的类}
     */
    private String holidayType;
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
