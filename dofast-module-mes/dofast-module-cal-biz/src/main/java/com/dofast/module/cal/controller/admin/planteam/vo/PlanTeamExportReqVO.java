package com.dofast.module.cal.controller.admin.planteam.vo;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 计划班组 Excel 导出 Request VO，参数和 PlanTeamPageReqVO 是一致的")
@Data
public class PlanTeamExportReqVO {

    @Schema(description = "计划ID", example = "21447")
    private Long planId;

    @Schema(description = "班组ID", example = "18303")
    private Long teamId;

    @Schema(description = "班组编号")
    private String teamCode;

    @Schema(description = "班组名称", example = "芋艿")
    private String teamName;

    @Schema(description = "备注", example = "随便")
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
