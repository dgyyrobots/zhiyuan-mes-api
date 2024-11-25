package com.dofast.module.mes.controller.admin.mdunitconverse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 单位换算 Excel VO
 *
 * @author 惠智造
 */
@Data
public class MdUnitConverseExcelVO {

    @ExcelProperty("单位ID")
    private Long id;

    @ExcelProperty("原编码")
    private String measureCode;

    @ExcelProperty("原单位数量")
    private BigDecimal originCount;

    @ExcelProperty("转换单位")
    private String converseCode;

    @ExcelProperty("转换数量")
    private BigDecimal converseCount;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
