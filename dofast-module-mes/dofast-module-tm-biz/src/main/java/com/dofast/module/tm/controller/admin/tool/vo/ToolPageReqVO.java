package com.dofast.module.tm.controller.admin.tool.vo;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工装夹具清单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ToolPageReqVO extends PageParam {

    @Schema(description = "工装夹具编码")
    private String toolCode;

    @Schema(description = "工装夹具名称", example = "王五")
    private String toolName;

    @Schema(description = "品牌")
    private String brand;

    @Schema(description = "型号")
    private String spec;

    @Schema(description = "工装夹具类型ID", example = "27166")
    private Long toolTypeId;

    @Schema(description = "工装夹具类型编码")
    private String toolTypeCode;

    @Schema(description = "工装夹具类型名称", example = "张三")
    private String toolTypeName;

    @Schema(description = "是否单独编码管理")
    private String codeFlag;

    @Schema(description = "数量")
    private Integer quantity;

    @Schema(description = "可用数量")
    private Integer quantityAvail;

    @Schema(description = "保养维护类型", example = "1")
    private String maintenType;

    @Schema(description = "下一次保养周期")
    private Integer nextMaintenPeriod;

    @Schema(description = "下一次保养日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] nextMaintenDate;

    @Schema(description = "状态[MES_TOOL_STATUS]", example = "2")
    private String status;

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
