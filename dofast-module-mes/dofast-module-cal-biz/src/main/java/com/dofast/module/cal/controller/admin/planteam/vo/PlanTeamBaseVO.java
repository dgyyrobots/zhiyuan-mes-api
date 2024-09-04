package com.dofast.module.cal.controller.admin.planteam.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 计划班组 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class PlanTeamBaseVO {

    @Schema(description = "计划ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21447")
    @NotNull(message = "计划ID不能为空")
    private Long planId;

    @Schema(description = "班组ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18303")
    @NotNull(message = "班组ID不能为空")
    private Long teamId;

    @Schema(description = "班组编号")
    private String teamCode;

    @Schema(description = "班组名称", example = "芋艿")
    private String teamName;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

}
