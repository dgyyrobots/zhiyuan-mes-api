package com.dofast.module.mes.dal.dataobject.mdproductsop;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品SOP DO
 *
 * @author 芋道源码
 */
@TableName("mes_md_product_sop")
@KeySequence("mes_md_product_sop_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MdProductSopDO extends BaseDO {

    /**
     * 记录ID
     */
    @TableId
    private Long id;
    /**
     * 物料产品ID
     */
    private Long itemId;
    /**
     * 排列顺序
     */
    private Integer orderNum;
    /**
     * 对应的工序
     */
    private Long processId;
    /**
     * 工序编号
     */
    private String processCode;
    /**
     * 工序名称
     */
    private String processName;
    /**
     * 标题
     */
    private String sopTitle;
    /**
     * 详细描述
     */
    private String sopDescription;
    /**
     * 图片地址
     */
    private String sopUrl;
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
