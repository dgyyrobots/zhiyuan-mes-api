package com.dofast.module.pro.controller.admin.processdefect.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 工序异常缺陷名称创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProcessDefectCreateReqVO extends ProcessDefectBaseVO {

}
