package com.dofast.module.mes.dal.dataobject.electroplatelog;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 制版房记录 DO
 *
 * @author 惠智造
 */
@TableName("mes_electroplate_log")
@KeySequence("mes_electroplate_log_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectroplateLogDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 设备ID
     */
    private Long machineryId;
    /**
     * 设备编码
     */
    private String machineryCode;
    /**
     * 设备名称
     */
    private String machineryName;
    /**
     * 比例
     */
    private String proportion;
    /**
     * 温度
     */
    private String temperature;
    /**
     * PH值
     */
    private String phValue;
    /**
     * 备注
     */
    private String remark;

}
