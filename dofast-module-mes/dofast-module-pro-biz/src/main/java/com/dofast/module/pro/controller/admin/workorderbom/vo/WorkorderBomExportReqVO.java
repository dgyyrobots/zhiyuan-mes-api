package com.dofast.module.pro.controller.admin.workorderbom.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 生产工单BOM组成 Excel 导出 Request VO，参数和 WorkorderBomPageReqVO 是一致的")
@Data
public class WorkorderBomExportReqVO {

    @Schema(description = "生产工单ID", example = "7482")
    private Long workorderId;

    @Schema(description = "BOM物料ID", example = "11673")
    private Long itemId;

    @Schema(description = "BOM物料编号")
    private String itemCode;

    @Schema(description = "BOM物料名称", example = "王五")
    private String itemName;

    @Schema(description = "规格型号")
    private String itemSpc;

    @Schema(description = "单位")
    private String unitOfMeasure;

    @Schema(description = "物料产品标识")
    private String itemOrProduct;

    @Schema(description = "预计使用量")
    private Double quantity;

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
