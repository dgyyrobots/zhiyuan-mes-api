package com.dofast.module.mes.controller.admin.qualityabnormity.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 品质异常信息创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class QualityAbnormityCreateReqVO extends QualityAbnormityBaseVO {

}
