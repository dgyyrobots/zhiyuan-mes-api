package com.dofast.module.pro.controller.admin.processdefect.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工序异常缺陷名称 Excel 导出 Request VO，参数和 ProcessDefectPageReqVO 是一致的")
@Data
public class ProcessDefectExportReqVO {

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "工序编码")
    private String processCode;

    @Schema(description = "缺陷名称", example = "芋艿")
    private String defectName;

}
