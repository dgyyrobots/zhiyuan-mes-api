package com.dofast.module.mes.controller.admin.electroplatelog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 制版房记录 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ElectroplateLogBaseVO {

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18636")
    @NotNull(message = "设备ID不能为空")
    private Long machineryId;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "设备编码不能为空")
    private String machineryCode;

    @Schema(description = "设备名称", example = "制版机1号")
    private String machineryName;

    @Schema(description = "比例", example = "33")
    private String proportion;

    @Schema(description = "温度", example = "35℃")
    private String temperature;

    @Schema(description = "PH值", example = "3.9")
    private String phValue;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}
