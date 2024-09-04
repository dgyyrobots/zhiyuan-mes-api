package com.dofast.module.mes.controller.admin.mdproductsop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 产品SOP Excel VO
 *
 * @author 芋道源码
 */
@Data
public class MdProductSopExcelVO {

    @ExcelProperty("记录ID")
    private Long id;

    @ExcelProperty("物料产品ID")
    private Long itemId;

    @ExcelProperty("排列顺序")
    private Integer orderNum;

    @ExcelProperty("对应的工序")
    private Long processId;

    @ExcelProperty("工序编号")
    private String processCode;

    @ExcelProperty("工序名称")
    private String processName;

    @ExcelProperty("标题")
    private String sopTitle;

    @ExcelProperty("详细描述")
    private String sopDescription;

    @ExcelProperty("图片地址")
    private String sopUrl;

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
