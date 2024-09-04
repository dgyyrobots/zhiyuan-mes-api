package com.dofast.module.wms.dal.dataobject.transferline;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 转移单行 DO
 *
 * @author 芋道源码
 */
@TableName("wms_transfer_line")
@KeySequence("wms_transfer_line_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferLineDO extends BaseDO {

    /**
     * 明细行ID
     */
    @TableId
    private Long id;
    /**
     * 装箱单ID
     */
    private Long transferId;
    /**
     * 库存记录ID
     */
    private Long materialStockId;
    /**
     * 产品物料ID
     */
    private Long itemId;
    /**
     * 产品物料编码
     */
    private String itemCode;
    /**
     * 产品物料名称
     */
    private String itemName;
    /**
     * 规格型号
     */
    private String specification;
    /**
     * 单位
     */
    private String unitOfMeasure;
    /**
     * 装箱数量
     */
    private Double quantityTransfer;
    /**
     * 生产工单ID
     */
    private Long workorderId;
    /**
     * 生产工单编号
     */
    private String workorderCode;
    /**
     * 批次号
     */
    private String batchCode;
    /**
     * 移出仓库ID
     */
    private Long fromWarehouseId;
    /**
     * 移出仓库编码
     */
    private String fromWarehouseCode;
    /**
     * 移出仓库名称
     */
    private String fromWarehouseName;
    /**
     * 移出库区ID
     */
    private Long fromLocationId;
    /**
     * 移出库区编码
     */
    private String fromLocationCode;
    /**
     * 移出库区名称
     */
    private String fromLocationName;
    /**
     * 移出库位ID
     */
    private Long fromAreaId;
    /**
     * 移出库位编码
     */
    private String fromAreaCode;
    /**
     * 移出库位名称
     */
    private String fromAreaName;
    /**
     * 移入仓库ID
     */
    private Long toWarehouseId;
    /**
     * 移入仓库编码
     */
    private String toWarehouseCode;
    /**
     * 移入仓库名称
     */
    private String toWarehouseName;
    /**
     * 移入库区ID
     */
    private Long toLocationId;
    /**
     * 移入库区编码
     */
    private String toLocationCode;
    /**
     * 移入库区名称
     */
    private String toLocationName;
    /**
     * 移入库位ID
     */
    private Long toAreaId;
    /**
     * 移入库位编码
     */
    private String toAreaCode;
    /**
     * 移入库位名称
     */
    private String toAreaName;
    /**
     * 有效期
     */
    private LocalDateTime expireDate;
    /**
     * 供应商ID
     */
    private Long vendorId;
    /**
     * 供应商编码
     */
    private String vendorCode;
    /**
     * 供应商名称
     */
    private String vendorName;
    /**
     * 供应商简称
     */
    private String vendorNick;
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
