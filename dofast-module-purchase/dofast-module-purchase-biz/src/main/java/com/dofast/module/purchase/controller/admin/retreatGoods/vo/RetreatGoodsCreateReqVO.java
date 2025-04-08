package com.dofast.module.purchase.controller.admin.retreatGoods.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - ERP仓退单单身创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RetreatGoodsCreateReqVO extends RetreatGoodsBaseVO {

}
