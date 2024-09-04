package com.dofast.module.hr.controller.admin.employeeeducation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 员工教育培训经历创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeEducationCreateReqVO extends EmployeeEducationBaseVO {

}
