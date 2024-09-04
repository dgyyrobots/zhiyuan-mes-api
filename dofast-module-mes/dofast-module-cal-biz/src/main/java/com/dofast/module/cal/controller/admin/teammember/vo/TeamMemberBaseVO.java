package com.dofast.module.cal.controller.admin.teammember.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 班组成员 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TeamMemberBaseVO {

    @Schema(description = "班组ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21314")
    @NotNull(message = "班组ID不能为空")
    private Long teamId;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14114")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "用户名不能为空")
    private String userName;

    @Schema(description = "用户昵称", example = "赵六")
    private String nickName;

    @Schema(description = "电话")
    private String tel;

    @Schema(description = "备注", example = "你说的对")
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
