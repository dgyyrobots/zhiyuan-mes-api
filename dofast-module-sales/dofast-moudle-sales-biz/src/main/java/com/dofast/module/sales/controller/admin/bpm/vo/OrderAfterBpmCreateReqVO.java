package com.dofast.module.sales.controller.admin.bpm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 售后工作流流程表单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderAfterBpmCreateReqVO extends OrderAfterBpmBaseVO{

}
