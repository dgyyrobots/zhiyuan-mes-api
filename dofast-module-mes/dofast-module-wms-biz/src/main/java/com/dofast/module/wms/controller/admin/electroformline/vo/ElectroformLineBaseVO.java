package com.dofast.module.wms.controller.admin.electroformline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 制版房领料单身体 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ElectroformLineBaseVO {

    @Schema(description = "制版领料单ID", example = "8891")
    private Long issueId;

    @Schema(description = "库存ID", example = "27651")
    private Long materialStockId;

    @Schema(description = "产品物料ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28600")
    @NotNull(message = "产品物料ID不能为空")
    private Long itemId;

    @Schema(description = "产品物料编码")
    private String itemCode;

    @Schema(description = "产品物料名称", example = "张三")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位")
    private String unitOfMeasure;

    @Schema(description = "领料数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "领料数量不能为空")
    private Double quantityIssued;

    @Schema(description = "领料批次号")
    private String batchCode;

    @Schema(description = "仓库ID", example = "2024")
    private Long warehouseId;

    @Schema(description = "仓库编码")
    private String warehouseCode;

    @Schema(description = "仓库名称", example = "赵六")
    private String warehouseName;

    @Schema(description = "库区ID", example = "8176")
    private Long locationId;

    @Schema(description = "库区编码")
    private String locationCode;

    @Schema(description = "库区名称", example = "李四")
    private String locationName;

    @Schema(description = "库位ID", example = "21710")
    private Long areaId;

    @Schema(description = "库位编码")
    private String areaCode;

    @Schema(description = "库位名称", example = "张三")
    private String areaName;

    @Schema(description = "备注", example = "随便")
    private String remark;

}
