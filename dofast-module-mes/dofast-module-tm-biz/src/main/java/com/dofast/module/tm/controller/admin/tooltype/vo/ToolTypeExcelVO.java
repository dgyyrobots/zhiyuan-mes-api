package com.dofast.module.tm.controller.admin.tooltype.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 工装夹具类型 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ToolTypeExcelVO {

    @ExcelProperty("工装夹具类型ID")
    private Long id;

    @ExcelProperty("类型编码")
    private String toolTypeCode;

    @ExcelProperty("类型名称")
    private String toolTypeName;

    @ExcelProperty(value = "是否编码管理", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String codeFlag;

    @ExcelProperty(value = "保养维护类型", converter = DictConvert.class)
    @DictFormat("tm-fix-type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String maintenType;

    @ExcelProperty("保养周期")
    private Integer maintenPeriod;

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
