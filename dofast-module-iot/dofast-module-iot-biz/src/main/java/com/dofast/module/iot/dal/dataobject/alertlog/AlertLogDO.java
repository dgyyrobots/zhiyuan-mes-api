package com.dofast.module.iot.dal.dataobject.alertlog;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备告警日志 DO
 *
 * @author 惠智造
 */
@TableName("iot_alert_log")
@KeySequence("iot_alert_log_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertLogDO extends BaseDO {

    /**
     * 告警ID
     */
    @TableId
    private Long id;
    /**
     * 告警名称
     */
    private String alertName;
    /**
     * 告警级别（1=提醒通知，2=轻微问题，3=严重警告）
     */
    private Byte alertLevel;
    /**
     * 处理状态(1=不需要处理,2=未处理,3=已处理)
     */
    private Byte status;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 类型（1=告警，2=场景联动）
     */
    private Byte type;

}
