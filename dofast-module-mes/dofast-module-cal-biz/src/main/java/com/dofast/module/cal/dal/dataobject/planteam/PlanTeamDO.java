package com.dofast.module.cal.dal.dataobject.planteam;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 计划班组 DO
 *
 * @author 惠智造
 */
@TableName("cal_plan_team")
@KeySequence("cal_plan_team_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanTeamDO extends BaseDO {

    /**
     * 流水号id
     */
    @TableId
    private Long id;
    /**
     * 计划ID
     */
    private Long planId;
    /**
     * 班组ID
     */
    private Long teamId;
    /**
     * 班组编号
     */
    private String teamCode;
    /**
     * 班组名称
     */
    private String teamName;
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
