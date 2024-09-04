package com.dofast.module.wiki.controller.admin.lecturer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 讲师的信息	 Excel VO
 *
 * @author 惠智造
 */
@Data
public class WikiLecturerExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("讲师名称")
    private String name;

    @ExcelProperty("讲师授课方向")
    private String direction;

    @ExcelProperty("讲师照片")
    private String picture;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
