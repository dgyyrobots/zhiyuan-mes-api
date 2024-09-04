package com.dofast.module.wms.dal.dataobject.itemconsume;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 物料消耗记录 DO
 *
 * @author 惠智造
 */
@TableName("wms_item_consume")
@KeySequence("wms_item_consume_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemConsumeDO extends BaseDO {

    /**
     * 记录ID
     */
    @TableId
    private Long id;
    /**
     * 生产工单ID
     */
    private Long workorderId;
    /**
     * 生产工单编码
     */
    private String workorderCode;
    /**
     * 生产工单名称
     */
    private String workorderName;
    /**
     * 生产任务ID
     */
    private Long taskId;
    /**
     * 生产任务编号
     */
    private String taskCode;
    /**
     * 生产任务名称
     */
    private String taskName;
    /**
     * 工作站ID
     */
    private Long workstationId;
    /**
     * 工作站编号
     */
    private String workstationCode;
    /**
     * 工作站名称
     */
    private String workstationName;
    /**
     * 工序ID
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
     * 消耗日期
     */
    private LocalDateTime consumeDate;
    /**
     * 单据状态
     */
    private String status;
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
