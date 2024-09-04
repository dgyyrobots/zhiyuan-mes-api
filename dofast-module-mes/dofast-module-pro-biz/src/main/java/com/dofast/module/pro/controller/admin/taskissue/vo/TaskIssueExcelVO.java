package com.dofast.module.pro.controller.admin.taskissue.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 生产任务投料 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class TaskIssueExcelVO {

    @ExcelProperty("记录ID")
    private Long id;

    @ExcelProperty("生产任务ID")
    private Long taskId;

    @ExcelProperty("生产工单ID")
    private Long workorderId;

    @ExcelProperty("工作站ID")
    private Long workstationId;

    @ExcelProperty("单据ID")
    private Long sourceDocId;
    @ExcelProperty("单据编号")
    private String sourceDocCode;
    @ExcelProperty("单据类型")
    private String sourceDocType;
    @ExcelProperty("投料批次")
    private String batchCode;

    @ExcelProperty("行ID")
    private Long sourceLineId;

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

    @ExcelProperty("总的投料数量")
    private BigDecimal quantityIssued;

    @ExcelProperty("当前可用数量")
    private BigDecimal quantityAvailable;

    @ExcelProperty("当前使用数量")
    private BigDecimal quantityUsed;

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
