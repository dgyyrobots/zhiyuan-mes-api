package com.dofast.module.hr.controller.admin.salarydetail.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 工资明细 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class SalarydetailBaseVO {

    @Schema(description = "工资", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "工资不能为空")
    private Integer salary;

    @Schema(description = "科目", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "科目不能为空")
    private String item;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "类型不能为空")
    private String type;

    @Schema(description = "金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "金额不能为空")
    private BigDecimal amount;

    @Schema(description = "描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "描述不能为空")
    private String desc;

}
