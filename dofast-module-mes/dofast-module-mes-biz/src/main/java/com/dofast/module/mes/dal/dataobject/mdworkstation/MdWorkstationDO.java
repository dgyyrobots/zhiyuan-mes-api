package com.dofast.module.mes.dal.dataobject.mdworkstation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工作站 DO
 *
 * @author 芋道源码
 */
@TableName("mes_md_workstation")
@KeySequence("mes_md_workstation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MdWorkstationDO extends BaseDO {

    /**
     * 工作站ID
     */
    @TableId
    private Long id;
    /**
     * 工作站编码
     */
    private String workstationCode;
    /**
     * 工作站名称
     */
    private String workstationName;
    /**
     * 工作站地点
     */
    private String workstationAddress;
    /**
     * 所在车间ID
     */
    private Long workshopId;
    /**
     * 所在车间编码
     */
    private String workshopCode;
    /**
     * 所在车间名称
     */
    private String workshopName;
    /**
     * 工序ID
     */
    private Long processId;
    /**
     * 工序编码
     */
    private String processCode;
    /**
     * 工序名称
     */
    private String processName;
    /**
     * 线边库ID
     */
    private Long warehouseId;
    /**
     * 线边库编码
     */
    private String warehouseCode;
    /**
     * 线边库名称
     */
    private String warehouseName;
    /**
     * 库区ID
     */
    private Long locationId;
    /**
     * 库区编码
     */
    private String locationCode;
    /**
     * 库区名称
     */
    private String locationName;
    /**
     * 库位ID
     */
    private Long areaId;
    /**
     * 库位编码
     */
    private String areaCode;
    /**
     * 库位名称
     */
    private String areaName;
    /**
     * 是否启用
     */
    private String enableFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 预留字段1
     */
    private String attr1;
    /**
     * 预留字段2
     */
    private String attr2;
    /**
     * 预留字段3
     */
    private Integer attr3;
    /**
     * 预留字段4
     */
    private Integer attr4;

}
