package com.dofast.module.mes.controller.admin.mdproductbom.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 产品BOM关系 Excel 导出 Request VO，参数和 MdProductBomPageReqVO 是一致的")
@Data
public class MdProductBomExportReqVO {

    @Schema(description = "物料产品ID", example = "8195")
    private Long itemId;

    @Schema(description = "BOM物料ID", example = "10443")
    private Long bomItemId;

    @Schema(description = "BOM物料编码")
    private String bomItemCode;

    @Schema(description = "BOM物料名称", example = "王五")
    private String bomItemName;

    @Schema(description = "BOM物料规格")
    private String bomItemSpec;

    @Schema(description = "BOM物料单位")
    private String unitOfMeasure;

    @Schema(description = "产品物料标识")
    private String itemOrProduct;

    @Schema(description = "物料使用比例")
    private Double quantity;

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
