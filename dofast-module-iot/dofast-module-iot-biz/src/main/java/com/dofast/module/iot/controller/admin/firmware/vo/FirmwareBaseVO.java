package com.dofast.module.iot.controller.admin.firmware.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 产品固件 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class FirmwareBaseVO {

    @Schema(description = "固件名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "固件名称不能为空")
    private String firmwareName;

    @Schema(description = "产品ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1372")
    @NotNull(message = "产品ID不能为空")
    private Long productId;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "产品名称不能为空")
    private String productName;

    @Schema(description = "是否系统通用（0-否，1-是）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否系统通用（0-否，1-是）不能为空")
    private Boolean isSys;

    @Schema(description = "是否最新版本（0-否，1-是）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否最新版本（0-否，1-是）不能为空")
    private Boolean isLatest;

    @Schema(description = "固件版本", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "固件版本不能为空")
    private Double version;

    @Schema(description = "文件路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "文件路径不能为空")
    private String filePath;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}
