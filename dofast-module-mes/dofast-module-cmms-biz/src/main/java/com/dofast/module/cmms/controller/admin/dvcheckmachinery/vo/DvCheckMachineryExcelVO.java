package com.dofast.module.cmms.controller.admin.dvcheckmachinery.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 点检设备 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class DvCheckMachineryExcelVO {

    @ExcelProperty("流水号")
    private Long id;

    @ExcelProperty("计划ID")
    private Long planId;

    @ExcelProperty("设备ID")
    private Long machineryId;

    @ExcelProperty("设备编码")
    private String machineryCode;

    @ExcelProperty("设备名称")
    private String machineryName;

    @ExcelProperty("品牌")
    private String machineryBrand;

    @ExcelProperty("规格型号")
    private String machinerySpec;

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

    @ExcelProperty("ERP设备编码")
    private String erpMachineryCode;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
