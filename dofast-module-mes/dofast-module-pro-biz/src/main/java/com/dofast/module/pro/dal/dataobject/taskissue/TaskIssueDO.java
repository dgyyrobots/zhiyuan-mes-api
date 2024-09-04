package com.dofast.module.pro.dal.dataobject.taskissue;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 生产任务投料 DO
 *
 * @author 芋道源码
 */
@TableName("pro_task_issue")
@KeySequence("pro_task_issue_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskIssueDO extends BaseDO {

    /**
     * 记录ID
     */
    @TableId
    private Long id;
    /**
     * 生产任务ID
     */
    private Long taskId;
    /**
     * 生产工单ID
     */
    private Long workorderId;
    /**
     * 工作站ID
     */
    private Long workstationId;

    /**
     * 单据类型
     */
    private String sourceDocType;
    /**
     * 单据ID
     */
    private Long sourceDocId;
    /**
     * 单据编号
     */
    private String sourceDocCode;
    /**
     * 投料批次
     */
    private String batchCode;
    /**
     * 行ID
     */
    private Long sourceLineId;
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
     * 总的投料数量
     */
    private BigDecimal quantityIssued;
    /**
     * 当前可用数量
     */
    private BigDecimal quantityAvailable;
    /**
     * 当前使用数量
     */
    private BigDecimal quantityUsed;
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
