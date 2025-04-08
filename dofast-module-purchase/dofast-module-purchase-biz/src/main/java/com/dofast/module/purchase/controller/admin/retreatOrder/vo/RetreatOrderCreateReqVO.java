package com.dofast.module.purchase.controller.admin.retreatOrder.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - ERP仓退单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RetreatOrderCreateReqVO extends RetreatOrderBaseVO {

}
