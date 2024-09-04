package com.dofast.module.iot.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 产品分类 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CategoryExcelVO {

    @ExcelProperty("产品分类ID")
    private Long categoryId;

    @ExcelProperty("产品分类名称")
    private String categoryName;

    @ExcelProperty("是否系统通用（0-否，1-是）")
    private Boolean isSys;

    @ExcelProperty("父级ID")
    private Long parentId;

    @ExcelProperty("显示顺序")
    private Integer orderNum;

    @ExcelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
