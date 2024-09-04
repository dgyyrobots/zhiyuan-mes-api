package com.dofast.module.iot.dal.dataobject.devicejob;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备定时 DO
 *
 * @author 惠智造
 */
@TableName("iot_device_job")
@KeySequence("iot_device_job_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceJobDO extends BaseDO {

    /**
     * 任务ID
     */
    @TableId
    private Long id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务组名
     */
    private String jobGroup;
    /**
     * cron执行表达式
     */
    private String cronExpression;
    /**
     * 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
     */
    private String misfirePolicy;
    /**
     * 是否并发执行（0允许 1禁止）
     */
    private String concurrent;
    /**
     * 状态（0正常 1暂停）
     */
    private String status;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 设备编号
     */
    private String serialNumber;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 是否详细corn表达式（1=是，0=否）
     */
    private Boolean isAdvance;
    /**
     * 执行的动作集合
     */
    private String actions;
    /**
     * 任务类型（1=设备定时，2=设备告警，3=场景联动）
     */
    private Boolean jobType;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 场景联动ID
     */
    private Long sceneId;
    /**
     * 告警ID
     */
    private Long alertId;

}
