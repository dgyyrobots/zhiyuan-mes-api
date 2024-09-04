package com.dofast.module.hr.controller.admin.commissionrule.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 提成规则 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CommissionruleBaseVO {

    @Schema(description = "月份", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "月份不能为空")
    private String month;

    @Schema(description = "账户", requiredMode = Schema.RequiredMode.REQUIRED, example = "4454")
    @NotNull(message = "账户不能为空")
    private String account;

    @Schema(description = "薪资", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "薪资不能为空")
    private String sale;

    @Schema(description = "线", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "线不能为空")
    private String line;

}
