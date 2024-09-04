package com.dofast.module.iot.dal.dataobject.deviceuser;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备用户 DO
 *
 * @author 惠智造
 */
@TableName("iot_device_user")
@KeySequence("iot_device_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceUserDO extends BaseDO {

    /**
     * 设备ID
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 手机号码
     */
    private String phonenumber;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 是否为设备所有者（0=否，1=是）
     */
    private Byte isOwner;
    /**
     * 备注
     */
    private String remark;

}
