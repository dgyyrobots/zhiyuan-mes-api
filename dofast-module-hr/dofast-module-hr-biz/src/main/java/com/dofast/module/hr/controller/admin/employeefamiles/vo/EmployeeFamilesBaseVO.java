package com.dofast.module.hr.controller.admin.employeefamiles.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 员工家庭成员 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class EmployeeFamilesBaseVO {

    @Schema(description = "家庭成员姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "家庭成员姓名不能为空")
    private String familesName;

    @Schema(description = "家庭成员关系", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "家庭成员关系不能为空")
    private String familesRealtion;

    @Schema(description = "家庭成员工作单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "家庭成员工作单位不能为空")
    private String familesWorkunit;

    @Schema(description = "家庭成员工作地区", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "家庭成员工作地区不能为空")
    private String familesWorkplace;

    @Schema(description = "家庭成员电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "家庭成员电话不能为空")
    private String familesPhone;

    @Schema(description = "员工id", requiredMode = Schema.RequiredMode.REQUIRED, example = "16304")
    @NotNull(message = "员工id不能为空")
    private Long employeeId;
}