package com.dofast.module.wms.controller.admin.rtvendorline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.security.core.parameters.P;

import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 供应商退货行 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class RtVendorLineBaseVO {

    @Schema(description = "退货单ID", example = "24441")
    private Long rtId;

    @Schema(description = "库存记录ID", example = "13833")
    private Long materialStockId;

    @Schema(description = "产品物料ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4301")
    @NotNull(message = "产品物料ID不能为空")
    private Long itemId;

    @Schema(description = "产品物料编码")
    private String itemCode;

    @Schema(description = "产品物料名称", example = "赵六")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位")
    private String unitOfMeasure;

    @Schema(description = "退货数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "退货数量不能为空")
    private Object quantityRted;

    @Schema(description = "批次号")
    private String batchCode;

    @Schema(description = "仓库ID", example = "14045")
    private Long warehouseId;

    @Schema(description = "仓库编码")
    private String warehouseCode;

    @Schema(description = "仓库名称", example = "赵六")
    private String warehouseName;

    @Schema(description = "库区ID", example = "15617")
    private Long locationId;

    @Schema(description = "库区编码")
    private String locationCode;

    @Schema(description = "库区名称", example = "赵六")
    private String locationName;

    @Schema(description = "库位ID", example = "70")
    private Long areaId;

    @Schema(description = "库位编码")
    private String areaCode;

    @Schema(description = "库位名称", example = "芋艿")
    private String areaName;

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

    public Long getId(){
        return null;
    }
}
