package com.dofast.module.iot.dal.dataobject.devicelog;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备日志 DO
 *
 * @author 惠智造
 */
@TableName("iot_device_log")
@KeySequence("iot_device_log_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceLogDO extends BaseDO {

    /**
     * 设备日志ID
     */
    @TableId
    private Long id;
    /**
     * 标识符
     */
    private String identity;
    /**
     * 类型（1=属性上报，2=调用功能，3=事件上报，4=设备升级，5=设备上线，6=设备离线）
     */
    private Boolean logType;
    /**
     * 日志值
     */
    private String logValue;
    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备编号
     */
    private String serialNumber;
    /**
     * 是否监测数据（1=是，0=否）
     */
    private Byte isMonitor;
    /**
     * 模式(1=影子模式，2=在线模式，3=其他)
     */
    private Byte mode;
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

}
