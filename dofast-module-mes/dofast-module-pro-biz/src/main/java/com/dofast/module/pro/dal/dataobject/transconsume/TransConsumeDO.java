package com.dofast.module.pro.dal.dataobject.transconsume;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 物料消耗记录 DO
 *
 * @author 芋道源码
 */
@TableName("pro_trans_consume")
@KeySequence("pro_trans_consume_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransConsumeDO extends BaseDO {

    /**
     * 记录ID
     */
    @TableId
    private Long id;
    /**
     * 流转单ID
     */
    private Long transOrderId;
    /**
     * 流转单编号
     */
    private String transOrderCode;
    /**
     * 生产任务ID
     */
    private Long taskId;
    /**
     * 工作站ID
     */
    private Long workstationId;
    /**
     * 工序ID
     */
    private Long processId;
    /**
     * 生产工单ID
     */
    private Long workorderId;
    /**
     * 批次号
     */
    private String batchCode;
    /**
     * 被消耗单据ID
     */
    private Long sourceDocId;
    /**
     * 被消耗单据编号
     */
    private String sourceDocCode;
    /**
     * 被消耗单据类型
     */
    private String sourceDocType;
    /**
     * 被消耗单据行ID
     */
    private Long sourceLineId;
    /**
     * 被消耗物料批次号
     */
    private String sourceBatchCode;
    /**
     * 被消耗产品物料ID
     */
    private Long itemId;
    /**
     * 被消耗产品物料编码
     */
    private String itemCode;
    /**
     * 被消耗产品物料名称
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
     * 消耗数量
     */
    private BigDecimal quantityConsumed;
    /**
     * 消耗时间
     */
    private LocalDateTime consumeDate;
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
