package com.dofast.module.system.controller.admin.form.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 系统表单 Excel VO
 *
 * @author 惠智造
 */
@Data
public class FormExcelVO {

    @ExcelProperty("ID")
    private Integer id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("描述")
    private String description;

    @ExcelProperty(value = "类型", converter = DictConvert.class)
    @DictFormat("system_form_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String type;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
