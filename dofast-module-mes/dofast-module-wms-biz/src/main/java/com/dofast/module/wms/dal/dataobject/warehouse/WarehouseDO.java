package com.dofast.module.wms.dal.dataobject.warehouse;

import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 仓库 DO
 *
 * @author 惠智造
 */
@TableName("wms_warehouse")
@KeySequence("wms_warehouse_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDO extends BaseDO {

    /**
     * 仓库ID
     */
    @TableId
    private Long id;
    /**
     * 仓库编码
     */
    private String warehouseCode;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 位置
     */
    private String location;
    /**
     * 面积
     */
    private BigDecimal area;
    /**
     * 负责人
     */
    private String charge;
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
    /**
     * 寄件人省
     */
    private String sendState;
    /**
     * 寄件人市
     */
    private String sendCity;
    /**
     * 寄件人区/县
     */
    private String sendDistrict;
    /**
     * 寄件人镇
     */
    private String sendStreet;
    /**
     * 寄件人详细地址
     */
    private String sendDetail;
    /**
     * 寄件人姓名
     */
    private String sendName;
    /**
     * 寄件人电话号码
     */
    private String sendPhone;
    /**
     * 寄件人手机号码
     */
    private String sendMobile;

    @TableField(exist = false)
    private List<StorageLocationDO> children;
}
