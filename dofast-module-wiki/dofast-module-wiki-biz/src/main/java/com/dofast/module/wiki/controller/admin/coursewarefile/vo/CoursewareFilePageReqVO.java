package com.dofast.module.wiki.controller.admin.coursewarefile.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 课件文件的保存地址分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CoursewareFilePageReqVO extends PageParam {

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
