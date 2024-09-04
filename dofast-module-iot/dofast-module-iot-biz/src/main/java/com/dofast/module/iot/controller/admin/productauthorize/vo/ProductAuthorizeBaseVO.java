package com.dofast.module.iot.controller.admin.productauthorize.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 产品授权码 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProductAuthorizeBaseVO {

    @Schema(description = "授权码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "授权码不能为空")
    private String authorizeCode;

    @Schema(description = "产品ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6229")
    @NotNull(message = "产品ID不能为空")
    private Long productId;

    @Schema(description = "设备ID", example = "26711")
    private Long deviceId;

    @Schema(description = "设备编号")
    private String serialNumber;

    @Schema(description = "用户ID", example = "23587")
    private Long userId;

    @Schema(description = "用户名称", example = "赵六")
    private String userName;

    @Schema(description = "状态（1-未使用，2-使用中）", example = "2")
    private Boolean status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}
