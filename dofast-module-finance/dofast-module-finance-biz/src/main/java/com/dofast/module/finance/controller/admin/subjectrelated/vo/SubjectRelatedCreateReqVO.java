package com.dofast.module.finance.controller.admin.subjectrelated.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 收支科目创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SubjectRelatedCreateReqVO extends SubjectRelatedBaseVO {

}
