package com.dofast.module.mes.controller.admin.mdunitmeasure.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 单位 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class MdUnitMeasureExcelVO {

    @ExcelProperty("单位ID")
    private Long id;

    @ExcelProperty("单位编码")
    private String measureCode;

    @ExcelProperty("单位名称")
    private String measureName;

    @ExcelProperty("是否是主单位")
    private String primaryFlag;

    @ExcelProperty("主单位ID")
    private Long primaryId;

    @ExcelProperty("与主单位换算比例")
    private BigDecimal changeRate;

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
