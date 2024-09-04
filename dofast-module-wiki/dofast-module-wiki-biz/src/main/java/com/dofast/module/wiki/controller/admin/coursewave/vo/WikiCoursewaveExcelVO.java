package com.dofast.module.wiki.controller.admin.coursewave.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 首页知识列表的信息	 Excel VO
 *
 * @author 惠智造
 */
@Data
public class WikiCoursewaveExcelVO {

    @ExcelProperty("课件id")
    private Long id;

    @ExcelProperty("种类id")
    private Long categoryId;

    @ExcelProperty("课件名称")
    private String coursewareName;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
