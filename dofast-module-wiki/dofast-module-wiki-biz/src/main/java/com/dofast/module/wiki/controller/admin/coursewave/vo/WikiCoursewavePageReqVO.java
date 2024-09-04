package com.dofast.module.wiki.controller.admin.coursewave.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 首页知识列表的信息	分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WikiCoursewavePageReqVO extends PageParam {

    @Schema(description = "种类id", example = "31158")
    private Long categoryId;

    @Schema(description = "讲师ID", example = "1")
    private Long lecturerId;

    @Schema(description = "课件名称", example = "王五")
    private String coursewareName;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
