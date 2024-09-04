package com.dofast.module.iot.dal.dataobject.devicegroup;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备分组 DO
 *
 * @author 惠智造
 */
@TableName("iot_device_group")
@KeySequence("iot_device_group_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceGroupDO extends BaseDO {

    /**
     * 设备ID
     */
    @TableId
    private Long id;
    /**
     * 分组ID
     */
    private Long groupId;

}
