package com.dofast.module.mes.controller.admin.mditemtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 物料产品分类 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class MdItemTypeExcelVO {

    @ExcelProperty("产品物料类型ID")
    private Long id;

    @ExcelProperty("产品物料类型编码")
    private String itemTypeCode;

    @ExcelProperty("产品物料类型名称")
    private String itemTypeName;

    @ExcelProperty("父类型ID")
    private Long parentTypeId;

    @ExcelProperty("所有层级父节点")
    private String ancestors;

    @ExcelProperty("产品物料标识")
    private String itemOrProduct;

    @ExcelProperty("排列顺序")
    private Integer orderNum;

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
