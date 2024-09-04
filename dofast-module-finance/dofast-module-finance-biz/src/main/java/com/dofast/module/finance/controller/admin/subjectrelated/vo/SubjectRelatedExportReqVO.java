package com.dofast.module.finance.controller.admin.subjectrelated.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 收支科目 Excel 导出 Request VO，参数和 SubjectRelatedPageReqVO 是一致的")
@Data
public class SubjectRelatedExportReqVO {

    @Schema(description = "科目名称", example = "张三")
    private String subjectName;

    @Schema(description = "科目类型(in收 out支)（0/1）", example = "1")
    private Integer subjectType;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
