package com.dofast.module.system.controller.admin.expresscompany.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 租户物流公司创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressCompanyCreateReqVO extends ExpressCompanyBaseVO {

}
