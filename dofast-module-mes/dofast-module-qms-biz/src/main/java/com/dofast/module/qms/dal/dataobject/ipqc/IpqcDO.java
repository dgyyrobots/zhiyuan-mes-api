package com.dofast.module.qms.dal.dataobject.ipqc;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 过程检验单 DO
 *
 * @author 芋道源码
 */
@TableName("qms_ipqc")
@KeySequence("qms_ipqc_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpqcDO extends BaseDO {

    /**
     * 检验单ID
     */
    @TableId
    private Long id;
    /**
     * 检验单编号
     */
    private String ipqcCode;
    /**
     * 检验单名称
     */
    private String ipqcName;
    /**
     * 检验类型
     */
    private String ipqcType;
    /**
     * 检验模板ID
     */
    private Long templateId;
    /**
     * 工单ID
     */
    private Long workorderId;
    /**
     * 工单编码
     */
    private String workorderCode;
    /**
     * 工单名称
     */
    private String workorderName;
    /**
     * 任务ID
     */
    private Long taskId;
    /**
     * 任务编号
     */
    private String taskCode;
    /**
     * 任务名称
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
     * 工序编码
     */
    private String processCode;
    /**
     * 工序名称
     */
    private String processName;
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
     * 检测数量
     */
    private BigDecimal quantityCheck;
    /**
     * 不合格数
     */
    private BigDecimal quantityUnqualified;
    /**
     * 合格品数量
     */
    private BigDecimal quantityQualified;
    /**
     * 致命缺陷率
     */
    private BigDecimal crRate;
    /**
     * 严重缺陷率
     */
    private BigDecimal majRate;
    /**
     * 轻微缺陷率
     */
    private BigDecimal minRate;
    /**
     * 致命缺陷数量
     */
    private BigDecimal crQuantity;
    /**
     * 严重缺陷数量
     */
    private BigDecimal majQuantity;
    /**
     * 轻微缺陷数量
     */
    private BigDecimal minQuantity;
    /**
     * 检测结果
     */
    private String checkResult;
    /**
     * 检测日期
     */
    private LocalDateTime inspectDate;
    /**
     * 检测人员
     */
    private String inspector;
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
     * 附件
     */
    private String adjuncts;
}
