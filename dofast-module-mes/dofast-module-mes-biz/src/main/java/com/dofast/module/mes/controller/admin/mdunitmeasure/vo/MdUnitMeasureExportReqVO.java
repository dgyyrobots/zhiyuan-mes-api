package com.dofast.module.mes.controller.admin.mdunitmeasure.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 单位 Excel 导出 Request VO，参数和 MdUnitMeasurePageReqVO 是一致的")
@Data
public class MdUnitMeasureExportReqVO {

    @Schema(description = "单位编码")
    private String measureCode;

    @Schema(description = "单位名称", example = "王五")
    private String measureName;

    @Schema(description = "是否是主单位")
    private String primaryFlag;

    @Schema(description = "主单位ID", example = "18490")
    private Long primaryId;

    @Schema(description = "与主单位换算比例")
    private BigDecimal changeRate;

    @Schema(description = "是否启用")
    private String enableFlag;

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
