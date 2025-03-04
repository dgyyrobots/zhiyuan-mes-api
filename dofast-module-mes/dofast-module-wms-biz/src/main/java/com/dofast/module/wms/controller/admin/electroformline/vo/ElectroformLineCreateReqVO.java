package com.dofast.module.wms.controller.admin.electroformline.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 制版房领料单身体创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ElectroformLineCreateReqVO extends ElectroformLineBaseVO {

}
