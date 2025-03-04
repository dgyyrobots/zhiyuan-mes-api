package com.dofast.module.qms.controller.admin.ipqc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 过程检验单 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class IpqcExcelVO {

    @ExcelProperty("检验单ID")
    private Long id;

    @ExcelProperty("检验单编号")
    private String ipqcCode;

    @ExcelProperty("检验单名称")
    private String ipqcName;

    @ExcelProperty("检验类型")
    private String ipqcType;

    @ExcelProperty("检验模板ID")
    private Long templateId;

    @ExcelProperty("工单ID")
    private Long workorderId;

    @ExcelProperty("工单编码")
    private String workorderCode;

    @ExcelProperty("工单名称")
    private String workorderName;

    @ExcelProperty("任务ID")
    private Long taskId;

    @ExcelProperty("任务编号")
    private String taskCode;

    @ExcelProperty("任务名称")
    private String taskName;

    @ExcelProperty("工作站ID")
    private Long workstationId;

    @ExcelProperty("工作站编号")
    private String workstationCode;

    @ExcelProperty("工作站名称")
    private String workstationName;

    @ExcelProperty("工序ID")
    private Long processId;

    @ExcelProperty("工序编码")
    private String processCode;

    @ExcelProperty("工序名称")
    private String processName;

    @ExcelProperty("产品物料ID")
    private Long itemId;

    @ExcelProperty("产品物料编码")
    private String itemCode;

    @ExcelProperty("产品物料名称")
    private String itemName;

    @ExcelProperty("规格型号")
    private String specification;

    @ExcelProperty("单位")
    private String unitOfMeasure;

    @ExcelProperty("检测数量")
    private BigDecimal quantityCheck;

    @ExcelProperty("不合格数")
    private BigDecimal quantityUnqualified;

    @ExcelProperty("合格品数量")
    private BigDecimal quantityQualified;

    @ExcelProperty("致命缺陷率")
    private BigDecimal crRate;

    @ExcelProperty("严重缺陷率")
    private BigDecimal majRate;

    @ExcelProperty("轻微缺陷率")
    private BigDecimal minRate;

    @ExcelProperty("致命缺陷数量")
    private BigDecimal crQuantity;

    @ExcelProperty("严重缺陷数量")
    private BigDecimal majQuantity;

    @ExcelProperty("轻微缺陷数量")
    private BigDecimal minQuantity;

    @ExcelProperty("检测结果")
    private String checkResult;

    @ExcelProperty("检测日期")
    private LocalDateTime inspectDate;

    @ExcelProperty("检测人员")
    private String inspector;

    @ExcelProperty("单据状态")
    private String status;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("预留字段1")
    private String attr1;

    @ExcelProperty("预留字段2")
    private String attr2;

    @ExcelProperty("预留字段3")
    private Integer attr3;

    @ExcelProperty("预留字段4")
    private Integer attr4;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("附件")
    private String adjuncts;

}
