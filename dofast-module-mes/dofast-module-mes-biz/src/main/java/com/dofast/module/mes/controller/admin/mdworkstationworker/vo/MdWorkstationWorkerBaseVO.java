package com.dofast.module.mes.controller.admin.mdworkstationworker.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 人力资源 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MdWorkstationWorkerBaseVO {

    @Schema(description = "工作站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22737")
    @NotNull(message = "工作站ID不能为空")
    private Long workstationId;

    @Schema(description = "岗位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4290")
    @NotNull(message = "岗位ID不能为空")
    private Long postId;

    @Schema(description = "岗位编码")
    private String postCode;

    @Schema(description = "岗位名称", example = "芋艿")
    private String postName;

    @Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "数量不能为空")
    private Integer quantity;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

    public Long getId(){
        return null;
    }
}
