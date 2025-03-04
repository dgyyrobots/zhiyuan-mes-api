package com.dofast.module.pro.controller.admin.route.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 工艺路线 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class RouteExcelVO {

    @ExcelProperty("工艺路线ID")
    private Long id;

    @ExcelProperty("工艺路线编号")
    private String routeCode;

    @ExcelProperty("工艺路线名称")
    private String routeName;

    @ExcelProperty("工艺路线说明")
    private String routeDesc;

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

    @ExcelProperty("产品编号")
    private String productCode;

    @ExcelProperty("文件")
    private String file;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
