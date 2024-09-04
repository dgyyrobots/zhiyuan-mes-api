package com.dofast.module.cmms.controller.admin.dvchecksubject.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 点检项目创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvCheckSubjectCreateReqVO extends DvCheckSubjectBaseVO {

}
