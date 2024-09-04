package com.dofast.module.pro.controller.admin.transconsume.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 物料消耗记录 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class TransConsumeExcelVO {

    @ExcelProperty("记录ID")
    private Long id;

    @ExcelProperty("流转单ID")
    private Long transOrderId;

    @ExcelProperty("流转单编号")
    private String transOrderCode;

    @ExcelProperty("生产任务ID")
    private Long taskId;

    @ExcelProperty("工作站ID")
    private Long workstationId;

    @ExcelProperty("工序ID")
    private Long processId;

    @ExcelProperty("生产工单ID")
    private Long workorderId;

    @ExcelProperty("批次号")
    private String batchCode;

    @ExcelProperty("被消耗单据ID")
    private Long sourceDocId;

    @ExcelProperty("被消耗单据编号")
    private String sourceDocCode;

    @ExcelProperty("被消耗单据类型")
    private String sourceDocType;

    @ExcelProperty("被消耗单据行ID")
    private Long sourceLineId;

    @ExcelProperty("被消耗物料批次号")
    private String sourceBatchCode;

    @ExcelProperty("被消耗产品物料ID")
    private Long itemId;

    @ExcelProperty("被消耗产品物料编码")
    private String itemCode;

    @ExcelProperty("被消耗产品物料名称")
    private String itemName;

    @ExcelProperty("规格型号")
    private String specification;

    @ExcelProperty("单位")
    private String unitOfMeasure;

    @ExcelProperty("消耗数量")
    private BigDecimal quantityConsumed;

    @ExcelProperty("消耗时间")
    private LocalDateTime consumeDate;

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

}
