package com.dofast.module.mes.dal.dataobject.mditemtype;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 物料产品分类 DO
 *
 * @author 芋道源码
 */
@TableName("mes_md_item_type")
@KeySequence("mes_md_item_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MdItemTypeDO extends BaseDO implements Serializable {

    /**
     * 产品物料类型ID
     */
    @TableId
    private Long id;
    /**
     * 产品物料类型编码
     */
    private String itemTypeCode;
    /**
     * 产品物料类型名称
     */
    private String itemTypeName;
    /**
     * 父类型ID
     */
    private Long parentTypeId;
    /**
     * 所有层级父节点
     */
    private String ancestors;
    /**
     * 产品物料标识
     */
    private String itemOrProduct;
    /**
     * 排列顺序
     */
    private Integer orderNum;
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

    @TableField(exist = false)
    private List<MdItemTypeDO> children = new ArrayList<MdItemTypeDO>();

}
