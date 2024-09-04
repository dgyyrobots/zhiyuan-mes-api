package com.dofast.module.wiki.controller.admin.coursewarefile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 课件文件的保存地址 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CoursewareFileExcelVO {

    @ExcelProperty("文件id")
    private Long id;

    @ExcelProperty("课件id")
    private Long coursewareId;

    @ExcelProperty("文件名称")
    private String filename;

    @ExcelProperty("文件路径")
    private String filepath;

    @ExcelProperty("文件大小")
    private String fileSize;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
