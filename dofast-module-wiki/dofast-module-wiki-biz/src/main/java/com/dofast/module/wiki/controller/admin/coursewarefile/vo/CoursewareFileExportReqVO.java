package com.dofast.module.wiki.controller.admin.coursewarefile.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 课件文件的保存地址 Excel 导出 Request VO，参数和 CoursewareFilePageReqVO 是一致的")
@Data
public class CoursewareFileExportReqVO {

    @Schema(description = "课件id", example = "13310")
    private Long coursewareId;

    @Schema(description = "文件名称", example = "李四")
    private String filename;

    @Schema(description = "文件路径")
    private String filepath;

    @Schema(description = "文件大小")
    private String fileSize;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
