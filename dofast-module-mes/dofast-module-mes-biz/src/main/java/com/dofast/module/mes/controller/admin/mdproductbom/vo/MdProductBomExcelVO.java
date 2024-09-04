package com.dofast.module.mes.controller.admin.mdproductbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 产品BOM关系 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class MdProductBomExcelVO {

    @ExcelProperty("流水号")
    private Long id;

    @ExcelProperty("物料产品ID")
    private Long itemId;

    @ExcelProperty("BOM物料ID")
    private Long bomItemId;

    @ExcelProperty("BOM物料编码")
    private String bomItemCode;

    @ExcelProperty("BOM物料名称")
    private String bomItemName;

    @ExcelProperty("BOM物料规格")
    private String bomItemSpec;

    @ExcelProperty("BOM物料单位")
    private String unitOfMeasure;

    @ExcelProperty("产品物料标识")
    private String itemOrProduct;

    @ExcelProperty("物料使用比例")
    private Double quantity;

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
