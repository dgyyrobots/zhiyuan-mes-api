package com.dofast.module.iot.dal.dataobject.alert;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备告警 DO
 *
 * @author 惠智造
 */
@TableName("iot_alert")
@KeySequence("iot_alert_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertDO extends BaseDO {

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
     * 产品ID
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 触发器
     */
    private String triggers;
    /**
     * 执行动作
     */
    private String actions;
    /**
     * 告警状态（1-启动，2-停止）
     */
    private Boolean status;
    /**
     * 备注
     */
    private String remark;

}
