package com.dofast.module.mes.controller.admin.defectiveinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 不良品信息管理 Excel VO
 *
 * @author 惠智造
 */
@Data
public class DefectiveInfoExcelVO {

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

    @ExcelProperty("不良数量")
    private Double badQuantity;

    @ExcelProperty("抽检数量")
    private Double inspectQty;

    @ExcelProperty("故障码名称")
    private String errorCodeName;

    @ExcelProperty("不良发生时间")
    private LocalDateTime badTime;

    @ExcelProperty("录入人")
    private String entryPerson;

    @ExcelProperty("录入时间")
    private LocalDateTime entryTime;

    @ExcelProperty("复判人")
    private String reinspector;

    @ExcelProperty("复检日期")
    private LocalDateTime reinspectDate;

    @ExcelProperty("复检结论")
    private String reinspectConclusion;

    @ExcelProperty("检验组")
    private String inspectGroup;

    @ExcelProperty("执行状态")
    private String excuteState;

    @ExcelProperty("执行状态代码")
    private String excuteCode;

}
