package com.dofast.module.tm.controller.admin.tooltype.vo;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工装夹具类型 Excel 导出 Request VO，参数和 ToolTypePageReqVO 是一致的")
@Data
public class ToolTypeExportReqVO {

    @Schema(description = "类型编码")
    private String toolTypeCode;

    @Schema(description = "类型名称", example = "张三")
    private String toolTypeName;

    @Schema(description = "是否编码管理")
    private String codeFlag;

    @Schema(description = "保养维护类型", example = "1")
    private String maintenType;

    @Schema(description = "保养周期")
    private Integer maintenPeriod;

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
