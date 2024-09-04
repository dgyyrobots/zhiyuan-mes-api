package com.dofast.module.trade.controller.admin.electronicsheetpackage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import liquibase.pro.packaged.S;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 电子面单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ElectronicsheetPackageBaseVO {

    @Schema(description = "订单编码outerCode", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "订单编码outerCode不能为空")
    private String orderNo;

    @Schema(description = "面单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "面单号不能为空")
    private String waybillCode;

    @Schema(description = "父面单号"/*, requiredMode = Schema.RequiredMode.REQUIRED*/)
//    @NotNull(message = "父面单号不能为空")
    private String parentWaybillCode;

    @Schema(description = "状态（0正常 -1不使用）")/*, requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态（0正常 -1不使用）不能为空")*/
    private Byte status;

    @Schema(description = "电子面板模板", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "电子面板模板不能为空")
    private String printTemplate;

}
