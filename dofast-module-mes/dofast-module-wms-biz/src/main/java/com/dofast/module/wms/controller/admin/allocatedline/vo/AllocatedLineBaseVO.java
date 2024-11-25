package com.dofast.module.wms.controller.admin.allocatedline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 调拨单身 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AllocatedLineBaseVO {

    @Schema(description = "调拨单ID", example = "15669")
    private Long allocatedId;

    @Schema(description = "库存ID", example = "6292")
    private Long materialStockId;

    @Schema(description = "产品物料ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24267")
    @NotNull(message = "产品物料ID不能为空")
    private Long itemId;

    @Schema(description = "产品物料编码")
    private String itemCode;

    @Schema(description = "产品物料名称", example = "芋艿")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位")
    private String unitOfMeasure;

    @Schema(description = "调拨数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "调拨数量不能为空")
    private Double quantityAllocated;

    @Schema(description = "调拨批次号")
    private String batchCode;

    @Schema(description = "仓库ID", example = "16707")
    private Long warehouseId;

    @Schema(description = "仓库编码")
    private String warehouseCode;

    @Schema(description = "仓库名称", example = "李四")
    private String warehouseName;

    @Schema(description = "库区ID", example = "11999")
    private Long locationId;

    @Schema(description = "库区编码")
    private String locationCode;

    @Schema(description = "库区名称", example = "李四")
    private String locationName;

    @Schema(description = "库位ID", example = "22420")
    private Long areaId;

    @Schema(description = "库位编码")
    private String areaCode;

    @Schema(description = "库位名称", example = "赵六")
    private String areaName;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}
