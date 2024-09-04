package com.dofast.module.wms.dal.dataobject.storagearea;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 库位 DO
 *
 * @author 芋道源码
 */
@TableName("wms_storage_area")
@KeySequence("wms_storage_area_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageAreaDO extends BaseDO {

    /**
     * 库位ID
     */
    @TableId
    private Long id;
    /**
     * 库位编码
     */
    private String areaCode;
    /**
     * 库位名称
     */
    private String areaName;
    /**
     * 库区ID
     */
    private Long locationId;
    /**
     * 面积
     */
    private BigDecimal area;
    /**
     * 最大载重量
     */
    private BigDecimal maxLoa;
    /**
     * 库位位置X
     */
    private Integer positionX;
    /**
     * 库位位置y
     */
    private Integer positionY;
    /**
     * 库位位置z
     */
    private Integer positionZ;
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
