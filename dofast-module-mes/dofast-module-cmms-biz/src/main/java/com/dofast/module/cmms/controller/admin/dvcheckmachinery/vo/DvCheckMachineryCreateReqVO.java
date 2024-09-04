package com.dofast.module.cmms.controller.admin.dvcheckmachinery.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 点检设备创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvCheckMachineryCreateReqVO extends DvCheckMachineryBaseVO {

}
