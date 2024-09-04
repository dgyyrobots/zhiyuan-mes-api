package com.dofast.module.wiki.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 首页的分类 Excel VO
 *
 * @author 惠智造
 */
@Data
public class WikiCategoryExcelVO {

    @ExcelProperty("种类id")
    private Long id;

    @ExcelProperty("父id")
    private Long pid;

    @ExcelProperty("类别等级")
    private Boolean categoryLevel;

    @ExcelProperty("种类名称")
    private String name;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
