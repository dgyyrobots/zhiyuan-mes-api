package com.dofast.module.wiki.controller.admin.category.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 首页的分类 Excel 导出 Request VO，参数和 WikiCategoryPageReqVO 是一致的")
@Data
public class WikiCategoryExportReqVO {

    @Schema(description = "父id", example = "16854")
    private Long pid;

    @Schema(description = "类别等级")
    private Boolean categoryLevel;

    @Schema(description = "种类名称", example = "赵六")
    private String name;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
