package com.dofast.module.wms.controller.admin.productsalse.vo;

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
 * 销售出库单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProductSalseBaseVO {

    @Schema(description = "出库单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "出库单编号不能为空")
    private String salseCode;

    @Schema(description = "出库单名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotNull(message = "出库单名称不能为空")
    private String salseName;

    @Schema(description = "出货检验单ID", example = "11082")
    private Long oqcId;

    @Schema(description = "出货检验单编号")
    private String oqcCode;

    @Schema(description = "销售订单编号")
    private String soCode;

    @Schema(description = "客户ID", example = "11524")
    private Long clientId;

    @Schema(description = "客户编码")
    private String clientCode;

    @Schema(description = "客户名称", example = "张三")
    private String clientName;

    @Schema(description = "客户简称")
    private String clientNick;

    @Schema(description = "仓库ID", example = "31415")
    private Long warehouseId;

    @Schema(description = "仓库编码")
    private String warehouseCode;

    @Schema(description = "仓库名称", example = "王五")
    private String warehouseName;

    @Schema(description = "库区ID", example = "22859")
    private Long locationId;

    @Schema(description = "库区编码")
    private String locationCode;

    @Schema(description = "库区名称", example = "赵六")
    private String locationName;

    @Schema(description = "库位ID", example = "21328")
    private Long areaId;

    @Schema(description = "库位编码")
    private String areaCode;

    @Schema(description = "库位名称", example = "李四")
    private String areaName;

    @Schema(description = "出库日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime salseDate;

    @Schema(description = "单据状态", example = "2")
    private String status;

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

    public Long getId(){return null;}
}
