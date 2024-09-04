package com.dofast.module.system.controller.admin.systemconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 参数配置 Excel VO
 *
 * @author 惠智造
 */
@Data
public class SystemConfigExcelVO {

    @ExcelProperty("主键")
    private Integer id;

    @ExcelProperty("应用端口关键字")
    private String appModule;

    @ExcelProperty("配置项关键字")
    private String configKey;

    @ExcelProperty("配置值json")
    private String value;

    @ExcelProperty("描述")
    private String configDesc;

    @ExcelProperty(value = "是否启用 1启用 0不启用", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Byte isUse;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
