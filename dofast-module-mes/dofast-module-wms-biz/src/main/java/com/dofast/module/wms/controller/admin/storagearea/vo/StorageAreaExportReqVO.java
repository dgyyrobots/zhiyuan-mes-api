package com.dofast.module.wms.controller.admin.storagearea.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 库位 Excel 导出 Request VO，参数和 StorageAreaPageReqVO 是一致的")
@Data
public class StorageAreaExportReqVO {

    @Schema(description = "库位编码")
    private String areaCode;

    @Schema(description = "库位名称", example = "王五")
    private String areaName;

    @Schema(description = "库区ID", example = "14951")
    private Long locationId;

    @Schema(description = "面积")
    private BigDecimal area;

    @Schema(description = "最大载重量")
    private BigDecimal maxLoa;

    @Schema(description = "库位位置X")
    private Integer positionX;

    @Schema(description = "库位位置y")
    private Integer positionY;

    @Schema(description = "库位位置z")
    private Integer positionZ;

    @Schema(description = "是否启用")
    private String enableFlag;

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
