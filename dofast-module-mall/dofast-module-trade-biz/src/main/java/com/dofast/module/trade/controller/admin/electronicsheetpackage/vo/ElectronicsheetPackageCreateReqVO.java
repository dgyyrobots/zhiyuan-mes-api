package com.dofast.module.trade.controller.admin.electronicsheetpackage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 电子面单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ElectronicsheetPackageCreateReqVO extends ElectronicsheetPackageBaseVO {

}
