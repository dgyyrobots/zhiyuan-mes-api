package com.dofast.module.wms.controller.admin.warehouse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 仓库 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class WarehouseBaseVO {

    @Schema(description = "仓库编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "仓库编码不能为空")
    private String warehouseCode;

    @Schema(description = "仓库名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "仓库名称不能为空")
    private String warehouseName;

    @Schema(description = "位置")
    private String location;

    @Schema(description = "面积")
    private BigDecimal area;

    @Schema(description = "负责人")
    private String charge;

    @Schema(description = "是否启用")
    private String enableFlag;

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

    @Schema(description = "寄件人省", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "寄件人省不能为空")
    private String sendState;

    @Schema(description = "寄件人市", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "寄件人市不能为空")
    private String sendCity;

    @Schema(description = "寄件人区/县", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "寄件人区/县不能为空")
    private String sendDistrict;

    @Schema(description = "寄件人镇", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "寄件人镇不能为空")
    private String sendStreet;

    @Schema(description = "寄件人详细地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "寄件人详细地址不能为空")
    private String sendDetail;

    @Schema(description = "寄件人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "寄件人姓名不能为空")
    private String sendName;

    @Schema(description = "寄件人电话号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "寄件人电话号码不能为空")
    private String sendPhone;

    @Schema(description = "寄件人手机号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "寄件人手机号码不能为空")
    private String sendMobile;

    public Long getId(){return null;}
}
