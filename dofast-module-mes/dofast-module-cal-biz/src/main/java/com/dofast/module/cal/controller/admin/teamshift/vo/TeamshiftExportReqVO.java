package com.dofast.module.cal.controller.admin.teamshift.vo;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 班组排班 Excel 导出 Request VO，参数和 TeamshiftPageReqVO 是一致的")
@Data
public class TeamshiftExportReqVO {

    @Schema(description = "日期")
    private String theDay;

    @Schema(description = "班组ID", example = "9486")
    private Long teamId;

    @Schema(description = "班组名称", example = "张三")
    private String teamName;

    @Schema(description = "班次ID", example = "9721")
    private Long shiftId;

    @Schema(description = "班次名称", example = "李四")
    private String shiftName;

    @Schema(description = "序号")
    private Integer orderNum;

    @Schema(description = "计划ID", example = "27150")
    private Long planId;

    @Schema(description = "班组类型", example = "2")
    private String calendarType;

    @Schema(description = "轮班方式", example = "2")
    private String shiftType;

    @Schema(description = "备注", example = "你说的对")
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
