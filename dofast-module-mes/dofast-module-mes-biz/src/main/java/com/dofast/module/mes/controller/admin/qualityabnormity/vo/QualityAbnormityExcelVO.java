package com.dofast.module.mes.controller.admin.qualityabnormity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 品质异常信息 Excel VO
 *
 * @author 惠智造
 */
@Data
public class QualityAbnormityExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("产品物料编码")
    private String itemCode;

    @ExcelProperty("产品物料名称")
    private String itemName;

    @ExcelProperty("检验批次号")
    private String batchesCode;

    @ExcelProperty("检验批次")
    private String batches;

    @ExcelProperty("不良描述")
    private String badDescription;

    @ExcelProperty("到货数量	")
    private Double quantity;

    @ExcelProperty("不良代码")
    private String badCode;

    @ExcelProperty("检测日期")
    private LocalDateTime inspectDate;

    @ExcelProperty("检测人员")
    private String inspector;

    @ExcelProperty("复检日期")
    private LocalDateTime reinspectDate;

    @ExcelProperty("复检人员")
    private String reinspector;

    @ExcelProperty("复检结论")
    private String reinspectConclusion;

    @ExcelProperty("工单号")
    private Long orderNum;

    @ExcelProperty("销售单号")
    private String saleNum;

    @ExcelProperty("检验组")
    private String inspectGroup;

    @ExcelProperty("不良数量")
    private Double badQuantity;

}
