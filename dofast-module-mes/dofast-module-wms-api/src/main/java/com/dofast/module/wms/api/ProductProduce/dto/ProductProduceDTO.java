package com.dofast.module.wms.api.ProductProduce.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductProduceDTO {
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
     * 生产日期
     */
    private LocalDateTime produceDate;
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
    /**
     * 入库单ID
     */
    private Long id;
}
