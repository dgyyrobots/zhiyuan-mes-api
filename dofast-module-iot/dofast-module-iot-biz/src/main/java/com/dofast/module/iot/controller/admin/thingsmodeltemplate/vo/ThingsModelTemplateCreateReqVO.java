package com.dofast.module.iot.controller.admin.thingsmodeltemplate.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 物模型模板创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ThingsModelTemplateCreateReqVO extends ThingsModelTemplateBaseVO {

}
