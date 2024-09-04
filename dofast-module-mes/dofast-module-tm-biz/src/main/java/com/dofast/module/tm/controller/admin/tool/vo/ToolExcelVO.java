package com.dofast.module.tm.controller.admin.tool.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 工装夹具清单 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ToolExcelVO {

    @ExcelProperty("工装夹具ID")
    private Long id;

    @ExcelProperty("工装夹具编码")
    private String toolCode;

    @ExcelProperty("工装夹具名称")
    private String toolName;

    @ExcelProperty("品牌")
    private String brand;

    @ExcelProperty("型号")
    private String spec;

    @ExcelProperty("工装夹具类型ID")
    private Long toolTypeId;

    @ExcelProperty("工装夹具类型编码")
    private String toolTypeCode;

    @ExcelProperty("工装夹具类型名称")
    private String toolTypeName;

    @ExcelProperty(value = "是否单独编码管理", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String codeFlag;

    @ExcelProperty("数量")
    private Integer quantity;

    @ExcelProperty("可用数量")
    private Integer quantityAvail;

    @ExcelProperty("保养维护类型")
    private String maintenType;

    @ExcelProperty("下一次保养周期")
    private Integer nextMaintenPeriod;

    @ExcelProperty("下一次保养日期")
    private LocalDateTime nextMaintenDate;

    @ExcelProperty(value = "状态[MES_TOOL_STATUS]", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String status;

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
