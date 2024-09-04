package com.dofast.module.iot.controller.admin.thingsmodeltemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 物模型模板 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ThingsModelTemplateExcelVO {

    @ExcelProperty("物模型ID")
    private Long id;

    @ExcelProperty("物模型名称")
    private String templateName;

    @ExcelProperty("标识符，产品下唯一")
    private String identifier;

    @ExcelProperty("模型类别（1-属性，2-功能，3-事件）")
    private Boolean type;

    @ExcelProperty("数据类型（integer、decimal、string、bool、array、enum）")
    private String datatype;

    @ExcelProperty("数据定义")
    private String specs;

    @ExcelProperty("是否系统通用（0-否，1-是）")
    private Boolean isSys;

    @ExcelProperty("是否首页显示（0-否，1-是）")
    private Boolean isTop;

    @ExcelProperty("是否实时监测（0-否，1-是）")
    private Boolean isMonitor;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
