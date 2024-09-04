package com.dofast.module.mes.dal.dataobject.mditem;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 物料产品 DO
 *
 * @author 芋道源码
 */
@TableName("mes_md_item")
@KeySequence("mes_md_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MdItemDO extends BaseDO {

    /**
     * 产品物料ID
     */
    @TableId
    private Long id;
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
     * 产品物料标识
     */
    private String itemOrProduct;
    /**
     * 物料类型ID
     */
    private Long itemTypeId;
    /**
     * 物料类型编码
     */
    private String itemTypeCode;
    /**
     * 物料类型名称
     */
    private String itemTypeName;
    /**
     * 是否启用
     */
    private String enableFlag;
    /**
     * 是否设置安全库存
     */
    private String safeStockFlag;
    /**
     * 最低库存量
     */
    private BigDecimal minStock;
    /**
     * 最大库存量
     */
    private BigDecimal maxStock;
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
     * 仓库ID
     */
    private Long warehouseId;
    /**
     * 仓库编码
     */
    private String warehouseCode;
    /**
     * 仓库名称
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
     * 入库日期
     */
    private LocalDateTime recptDate;

}
