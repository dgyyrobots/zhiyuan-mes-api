package com.dofast.module.iot.controller.admin.deviceuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 设备用户 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DeviceUserBaseVO {

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotNull(message = "设备名称不能为空")
    private String deviceName;

    @Schema(description = "手机号码")
    private String phonenumber;

    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "用户昵称不能为空")
    private String userName;

    @Schema(description = "是否为设备所有者（0=否，1=是）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否为设备所有者（0=否，1=是）不能为空")
    private Byte isOwner;

    @Schema(description = "备注", example = "随便")
    private String remark;

}
