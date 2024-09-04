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
 * 字段 Excel VO
 *
 * @author 惠智造
 */
@Data
public class FormItemExcelVO {

    @ExcelProperty("ID")
    private Integer id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("字段名")
    private String field;

    @ExcelProperty(value = "验证器", converter = DictConvert.class)
    @DictFormat("system_form_validator") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String validator;

    @ExcelProperty(value = "类型", converter = DictConvert.class)
    @DictFormat("system_form_item_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String type;

    @ExcelProperty("默认值")
    private String defaultValue;

    @ExcelProperty("排序")
    private Integer sort;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
