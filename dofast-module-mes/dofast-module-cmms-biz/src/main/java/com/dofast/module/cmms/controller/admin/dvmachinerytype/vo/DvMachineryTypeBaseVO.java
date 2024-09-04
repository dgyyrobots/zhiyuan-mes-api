package com.dofast.module.cmms.controller.admin.dvmachinerytype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 设备类型 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DvMachineryTypeBaseVO {

    @Schema(description = "设备类型编码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String machineryTypeCode;

    @Schema(description = "设备类型名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotNull(message = "设备类型名称不能为空")
    private String machineryTypeName;

    @Schema(description = "父类型ID", example = "23622")
    private Long parentTypeId;

    @Schema(description = "所有父节点ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String ancestors;

    @Schema(description = "是否启用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否启用不能为空")
    private String enableFlag;

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
