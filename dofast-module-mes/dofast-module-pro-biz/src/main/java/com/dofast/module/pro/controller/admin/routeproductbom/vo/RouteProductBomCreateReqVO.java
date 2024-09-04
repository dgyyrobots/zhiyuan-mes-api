package com.dofast.module.pro.controller.admin.routeproductbom.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品制程物料BOM创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RouteProductBomCreateReqVO extends RouteProductBomBaseVO {

}
