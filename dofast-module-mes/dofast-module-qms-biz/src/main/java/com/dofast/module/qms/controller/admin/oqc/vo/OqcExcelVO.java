package com.dofast.module.qms.controller.admin.oqc.vo;

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
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

import javax.validation.constraints.NotNull;

/**
 * 出货检验单 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class OqcExcelVO {

    @ExcelProperty("出货检验单ID")
    private Long id;

    @ExcelProperty("出货检验单编号")
    private String oqcCode;

    @ExcelProperty("订单编号")
    private String sourceCode;

    @ExcelProperty("出货检验单名称")
    private String oqcName;

    @ExcelProperty("检验模板ID")
    private Long templateId;

    @ExcelProperty("客户ID")
    private Long clientId;

    @ExcelProperty("客户编码")
    private String clientCode;

    @ExcelProperty("客户名称")
    private String clientName;

    @ExcelProperty("批次号")
    private String batchCode;

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

    @ExcelProperty("最低检测数")
    private BigDecimal quantityMinCheck;

    @ExcelProperty("最大不合格数")
    private BigDecimal quantityMaxUnqualified;

    @ExcelProperty("发货数量")
    private BigDecimal quantityOut;

    @ExcelProperty("本次检测数量")
    private BigDecimal quantityCheck;

    @ExcelProperty("不合格数")
    private BigDecimal quantityUnqualified;

    @ExcelProperty("合格数量")
    private BigDecimal quantityQuanlified;

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

    @ExcelProperty("出货日期")
    private LocalDateTime outDate;

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
