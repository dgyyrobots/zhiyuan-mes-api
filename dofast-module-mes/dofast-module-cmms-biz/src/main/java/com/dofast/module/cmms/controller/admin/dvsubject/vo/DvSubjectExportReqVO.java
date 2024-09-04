package com.dofast.module.cmms.controller.admin.dvsubject.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备点检保养项目 Excel 导出 Request VO，参数和 DvSubjectPageReqVO 是一致的")
@Data
public class DvSubjectExportReqVO {

    @Schema(description = "项目编码")
    private String subjectCode;

    @Schema(description = "项目名称", example = "赵六")
    private String subjectName;

    @Schema(description = "项目类型", example = "1")
    private String subjectType;

    @Schema(description = "项目内容")
    private String subjectContent;

    @Schema(description = "标准")
    private String subjectStandard;

    @Schema(description = "是否启用")
    private String enableFlag;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
