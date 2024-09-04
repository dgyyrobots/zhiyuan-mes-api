package com.dofast.module.wms.controller.admin.transferline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 转移单行 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TransferLineBaseVO {

    @Schema(description = "装箱单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27471")
    @NotNull(message = "装箱单ID不能为空")
    private Long transferId;

    @Schema(description = "库存记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22732")
    @NotNull(message = "库存记录ID不能为空")
    private Long materialStockId;

    @Schema(description = "产品物料ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28756")
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

    @Schema(description = "装箱数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "装箱数量不能为空")
    private Double quantityTransfer;

    @Schema(description = "生产工单ID", example = "21997")
    private Long workorderId;

    @Schema(description = "生产工单编号")
    private String workorderCode;

    @Schema(description = "批次号")
    private String batchCode;

    @Schema(description = "移出仓库ID", example = "29717")
    private Long fromWarehouseId;

    @Schema(description = "移出仓库编码")
    private String fromWarehouseCode;

    @Schema(description = "移出仓库名称", example = "李四")
    private String fromWarehouseName;

    @Schema(description = "移出库区ID", example = "7698")
    private Long fromLocationId;

    @Schema(description = "移出库区编码")
    private String fromLocationCode;

    @Schema(description = "移出库区名称", example = "王五")
    private String fromLocationName;

    @Schema(description = "移出库位ID", example = "16678")
    private Long fromAreaId;

    @Schema(description = "移出库位编码")
    private String fromAreaCode;

    @Schema(description = "移出库位名称", example = "张三")
    private String fromAreaName;

    @Schema(description = "移入仓库ID", example = "23793")
    private Long toWarehouseId;

    @Schema(description = "移入仓库编码")
    private String toWarehouseCode;

    @Schema(description = "移入仓库名称", example = "李四")
    private String toWarehouseName;

    @Schema(description = "移入库区ID", example = "6898")
    private Long toLocationId;

    @Schema(description = "移入库区编码")
    private String toLocationCode;

    @Schema(description = "移入库区名称", example = "芋艿")
    private String toLocationName;

    @Schema(description = "移入库位ID", example = "625")
    private Long toAreaId;

    @Schema(description = "移入库位编码")
    private String toAreaCode;

    @Schema(description = "移入库位名称", example = "张三")
    private String toAreaName;

    @Schema(description = "有效期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime expireDate;

    @Schema(description = "供应商ID", example = "6195")
    private Long vendorId;

    @Schema(description = "供应商编码")
    private String vendorCode;

    @Schema(description = "供应商名称", example = "张三")
    private String vendorName;

    @Schema(description = "供应商简称")
    private String vendorNick;

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

}
