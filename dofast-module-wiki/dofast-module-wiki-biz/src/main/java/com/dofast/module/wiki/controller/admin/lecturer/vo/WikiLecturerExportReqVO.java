package com.dofast.module.wiki.controller.admin.lecturer.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 讲师的信息	 Excel 导出 Request VO，参数和 WikiLecturerPageReqVO 是一致的")
@Data
public class WikiLecturerExportReqVO {

    @Schema(description = "讲师名称", example = "张三")
    private String name;

    @Schema(description = "讲师授课方向")
    private String direction;

    @Schema(description = "讲师照片")
    private String picture;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
