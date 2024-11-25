package com.dofast.module.pro.controller.admin.workorderbom.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产工单BOM组成创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkorderBomCreateReqVO extends WorkorderBomBaseVO {

}
