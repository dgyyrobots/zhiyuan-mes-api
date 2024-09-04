package com.dofast.module.wms.controller.admin.sn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * SN码 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class SnExcelVO {

    @ExcelProperty("SN码ID")
    private Long id;

    @ExcelProperty("SN码")
    private String snCode;

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

    @ExcelProperty("批次号")
    private String batchCode;

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
    @ExcelProperty("生成时间")
    private LocalDateTime genDate;

    @ExcelProperty("SN码数量")
    private Integer snNum;
}
