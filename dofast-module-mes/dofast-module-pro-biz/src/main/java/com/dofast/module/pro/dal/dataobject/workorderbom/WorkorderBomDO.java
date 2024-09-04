package com.dofast.module.pro.dal.dataobject.workorderbom;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 生产工单BOM组成 DO
 *
 * @author 芋道源码
 */
@TableName("pro_workorder_bom")
@KeySequence("pro_workorder_bom_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkorderBomDO extends BaseDO {

    /**
     * 行ID
     */
    @TableId
    private Long id;
    /**
     * 生产工单ID
     */
    private Long workorderId;
    /**
     * BOM物料ID
     */
    private Long itemId;
    /**
     * BOM物料编号
     */
    private String itemCode;
    /**
     * BOM物料名称
     */
    private String itemName;
    /**
     * 规格型号
     */
    private String itemSpc;
    /**
     * 单位
     */
    private String unitOfMeasure;
    /**
     * 物料产品标识
     */
    private String itemOrProduct;
    /**
     * 预计使用量
     */
    private Double quantity;
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
