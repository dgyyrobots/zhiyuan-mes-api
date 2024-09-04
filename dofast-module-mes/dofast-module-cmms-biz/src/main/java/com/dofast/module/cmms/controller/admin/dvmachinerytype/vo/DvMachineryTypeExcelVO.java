package com.dofast.module.cmms.controller.admin.dvmachinerytype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 设备类型 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class DvMachineryTypeExcelVO {

    @ExcelProperty("设备类型ID")
    private Long machineryTypeId;

    @ExcelProperty("设备类型编码")
    private String machineryTypeCode;

    @ExcelProperty("设备类型名称")
    private String machineryTypeName;

    @ExcelProperty("父类型ID")
    private Long parentTypeId;

    @ExcelProperty("所有父节点ID")
    private String ancestors;

    @ExcelProperty("是否启用")
    private String enableFlag;

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
