package com.dofast.module.mes.controller.admin.mditem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 物料产品 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MdItemBaseVO {

    @Schema(description = "产品物料编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "产品物料编码不能为空")
    private String itemCode;

    @Schema(description = "产品物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotNull(message = "产品物料名称不能为空")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "单位不能为空")
    private String unitOfMeasure;

    @Schema(description = "产品物料标识", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String itemOrProduct;

    @Schema(description = "物料类型ID", example = "24355")
    private Long itemTypeId;

    @Schema(description = "物料类型编码")
    private String itemTypeCode;

    @Schema(description = "物料类型名称", example = "芋艿")
    private String itemTypeName;

    @Schema(description = "是否启用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否启用不能为空")
    private String enableFlag;

    @Schema(description = "是否设置安全库存", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否设置安全库存不能为空")
    private String safeStockFlag;

    @Schema(description = "最低库存量")
    private BigDecimal minStock;

    @Schema(description = "最大库存量")
    private BigDecimal maxStock;

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

    @Schema(description = "仓库ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23520")
    private Long warehouseId;

    @Schema(description = "仓库编码")
    private String warehouseCode;

    @Schema(description = "库区编码")
    private String locationCode;

    @Schema(description = "库位编码")
    private String areaCode;

    @Schema(description = "仓库名称", example = "王五")
    private String warehouseName;

    @Schema(description = "库区ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26747")
    private Long locationId;


    @Schema(description = "库区名称", example = "芋艿")
    private String locationName;

    @Schema(description = "库位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7077")
    private Long areaId;


    @Schema(description = "库位名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    private String areaName;

    @Schema(description = "入库日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime recptDate;

    public Long getId(){return null;}
}
