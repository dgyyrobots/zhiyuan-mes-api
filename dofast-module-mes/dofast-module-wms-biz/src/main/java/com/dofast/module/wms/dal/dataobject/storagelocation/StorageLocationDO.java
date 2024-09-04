package com.dofast.module.wms.dal.dataobject.storagelocation;

import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 库区 DO
 *
 * @author 芋道源码
 */
@TableName("wms_storage_location")
@KeySequence("wms_storage_location_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageLocationDO extends BaseDO {

    /**
     * 库区ID
     */
    @TableId
    private Long id;
    /**
     * 库区编码
     */
    private String locationCode;
    /**
     * 库区名称
     */
    private String locationName;
    /**
     * 仓库ID
     */
    private Long warehouseId;
    /**
     * 面积
     */
    private BigDecimal area;
    /**
     * 是否开启库位管理
     */
    private String areaFlag;
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

    @TableField(exist = false)
    private List<StorageAreaDO> children;
}
