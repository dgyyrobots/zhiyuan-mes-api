package com.dofast.module.iot.controller.admin.productauthorize.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品授权码创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductAuthorizeCreateReqVO extends ProductAuthorizeBaseVO {

}
