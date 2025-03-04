package com.dofast.module.wms.controller.admin.electroformheader.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 制版房领料单头创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ElectroformHeaderCreateReqVO extends ElectroformHeaderBaseVO {

}
