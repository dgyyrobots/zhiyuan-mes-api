package com.dofast.module.mes.controller.admin.mdworkstationmachine.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 设备资源 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MdWorkstationMachineBaseVO {

    @Schema(description = "工作站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "82")
    @NotNull(message = "工作站ID不能为空")
    private Long workstationId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21155")
    @NotNull(message = "设备ID不能为空")
    private Long machineryId;

    @Schema(description = "设备编码")
    private String machineryCode;

    @Schema(description = "设备名称", example = "张三")
    private String machineryName;

    @Schema(description = "数量")
    private Integer quantity;

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
