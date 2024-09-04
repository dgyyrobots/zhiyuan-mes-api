package com.dofast.module.hr.controller.admin.employeefamiles.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 员工家庭成员创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeFamilesCreateReqVO extends EmployeeFamilesBaseVO {

}
