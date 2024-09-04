package com.dofast.module.mes.controller.admin.userworkstation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 用户工作站绑定关系创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserWorkstationCreateReqVO extends UserWorkstationBaseVO {

}
