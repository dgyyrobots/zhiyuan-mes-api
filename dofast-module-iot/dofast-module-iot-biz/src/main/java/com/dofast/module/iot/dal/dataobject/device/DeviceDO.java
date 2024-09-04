package com.dofast.module.iot.dal.dataobject.device;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备 DO
 *
 * @author 惠智造
 */
@TableName("iot_device")
@KeySequence("iot_device_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDO extends BaseDO {

    /**
     * 设备ID
     */
    @TableId
    private Long id;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 设备编号
     */
    private String serialNumber;
    /**
     * 固件版本
     */
    private Double firmwareVersion;
    /**
     * 设备状态（1-未激活，2-禁用，3-在线，4-离线）
     */
    private Boolean status;
    /**
     * 信号强度（	信号极好4格[-55— 0]，	信号好3格[-70— -55]，	信号一般2格[-85— -70]，	信号差1格[-100— -85]）
     */
    private Byte rssi;
    /**
     * 是否启用设备影子(0=禁用，1=启用)
     */
    private Boolean isShadow;
    /**
     * 定位方式(1=ip自动定位，2=设备定位，3=自定义)
     */
    private Boolean locationWay;
    /**
     * 物模型值
     */
    private String thingsModelValue;
    /**
     * 设备所在地址
     */
    private String networkAddress;
    /**
     * 设备入网IP
     */
    private String networkIp;
    /**
     * 设备经度
     */
    private Object longitude;
    /**
     * 设备纬度
     */
    private Object latitude;
    /**
     * 激活时间
     */
    private LocalDateTime activeTime;
    /**
     * 设备摘要，格式[{"name":"device"},{"chip":"esp8266"}]
     */
    private String summary;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 备注
     */
    private String remark;

}
