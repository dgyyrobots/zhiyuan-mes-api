package com.dofast.module.iot.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 产品分类 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CategoryBaseVO {

    @Schema(description = "产品分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "产品分类名称不能为空")
    private String categoryName;

    @Schema(description = "是否系统通用（0-否，1-是）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否系统通用（0-否，1-是）不能为空")
    private Boolean isSys;

    @Schema(description = "父级ID", example = "32293")
    private Long parentId;

    @Schema(description = "显示顺序")
    private Integer orderNum;

    @Schema(description = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    @Schema(description = "备注", example = "随便")
    private String remark;

}
