package com.dofast.module.mes.dal.dataobject.mdproductbom;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品BOM关系 DO
 *
 * @author 芋道源码
 */
@TableName("mes_md_product_bom")
@KeySequence("mes_md_product_bom_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MdProductBomDO extends BaseDO {

    /**
     * 流水号
     */
    @TableId
    private Long id;
    /**
     * 物料产品ID
     */
    private Long itemId;
    /**
     * BOM物料ID
     */
    private Long bomItemId;
    /**
     * BOM物料编码
     */
    private String bomItemCode;
    /**
     * BOM物料名称
     */
    private String bomItemName;
    /**
     * BOM物料规格
     */
    private String bomItemSpec;
    /**
     * BOM物料单位
     */
    private String unitOfMeasure;
    /**
     * 产品物料标识
     */
    private String itemOrProduct;
    /**
     * 物料使用比例
     */
    private Double quantity;
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
