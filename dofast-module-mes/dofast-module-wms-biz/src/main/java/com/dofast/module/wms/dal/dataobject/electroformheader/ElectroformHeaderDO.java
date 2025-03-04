package com.dofast.module.wms.dal.dataobject.electroformheader;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 制版房领料单头 DO
 *
 * @author 惠智造
 */
@TableName("wms_electroform_header")
@KeySequence("wms_electroform_header_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectroformHeaderDO extends BaseDO {

    /**
     * 制版领料单ID
     */
    @TableId
    private Long id;
    /**
     * 制版领料单编号
     */
    private String issueCode;
    /**
     * 制版领料单名称
     */
    private String issueName;
    /**
     * 领料日期
     */
    private LocalDateTime issueDate;
    /**
     * 单据状态
     */
    private String status;
    /**
     * 生产设备名称
     */
    private String machineryName;
    /**
     * 生产设备编码
     */
    private String machineryCode;
    /**
     * 生产设备ID
     */
    private Long machineryId;

}
