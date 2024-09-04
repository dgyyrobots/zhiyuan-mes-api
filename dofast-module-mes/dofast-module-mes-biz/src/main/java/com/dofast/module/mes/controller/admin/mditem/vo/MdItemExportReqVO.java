package com.dofast.module.mes.controller.admin.mditem.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 物料产品 Excel 导出 Request VO，参数和 MdItemPageReqVO 是一致的")
@Data
public class MdItemExportReqVO {

    @Schema(description = "产品物料编码")
    private String itemCode;

    @Schema(description = "产品物料名称", example = "张三")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位")
    private String unitOfMeasure;

    @Schema(description = "产品物料标识")
    private String itemOrProduct;

    @Schema(description = "物料类型ID", example = "24355")
    private Long itemTypeId;

    @Schema(description = "物料类型编码")
    private String itemTypeCode;

    @Schema(description = "物料类型名称", example = "芋艿")
    private String itemTypeName;

    @Schema(description = "是否启用")
    private String enableFlag;

    @Schema(description = "是否设置安全库存")
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

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "仓库ID", example = "23520")
    private Long warehouseId;

    @Schema(description = "仓库编码")
    private String warehouseCode;

    @Schema(description = "仓库名称", example = "王五")
    private String warehouseName;

    @Schema(description = "库区ID", example = "26747")
    private Long locationId;

    @Schema(description = "库区编码")
    private String locationCode;

    @Schema(description = "库区名称", example = "芋艿")
    private String locationName;

    @Schema(description = "库位ID", example = "7077")
    private Long areaId;

    @Schema(description = "库位编码")
    private String areaCode;

    @Schema(description = "库位名称", example = "张三")
    private String areaName;

    @Schema(description = "入库日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] recptDate;

}
