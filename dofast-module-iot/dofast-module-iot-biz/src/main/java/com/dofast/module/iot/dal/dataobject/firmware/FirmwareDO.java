package com.dofast.module.iot.dal.dataobject.firmware;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品固件 DO
 *
 * @author 惠智造
 */
@TableName("iot_firmware")
@KeySequence("iot_firmware_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirmwareDO extends BaseDO {

    /**
     * 固件ID
     */
    @TableId
    private Long id;
    /**
     * 固件名称
     */
    private String firmwareName;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 是否系统通用（0-否，1-是）
     */
    private Boolean isSys;
    /**
     * 是否最新版本（0-否，1-是）
     */
    private Boolean isLatest;
    /**
     * 固件版本
     */
    private Double version;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 备注
     */
    private String remark;

}
