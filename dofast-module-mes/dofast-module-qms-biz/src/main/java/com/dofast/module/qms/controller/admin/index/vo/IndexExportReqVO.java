package com.dofast.module.qms.controller.admin.index.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 检测项 Excel 导出 Request VO，参数和 IndexPageReqVO 是一致的")
@Data
public class IndexExportReqVO {

    @Schema(description = "检测项编码")
    private String indexCode;

    @Schema(description = "检测项名称", example = "王五")
    private String indexName;

    @Schema(description = "检测项类型", example = "1")
    private String indexType;

    @Schema(description = "检测工具")
    private String qcTool;

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
