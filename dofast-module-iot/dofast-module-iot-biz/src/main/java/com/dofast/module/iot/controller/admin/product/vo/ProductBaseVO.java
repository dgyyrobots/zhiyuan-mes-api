package com.dofast.module.iot.controller.admin.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 产品 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProductBaseVO {

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "产品名称不能为空")
    private String productName;

    @Schema(description = "产品分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10115")
    @NotNull(message = "产品分类ID不能为空")
    private Long categoryId;

    @Schema(description = "产品分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "产品分类名称不能为空")
    private String categoryName;

    @Schema(description = "是否系统通用（0-否，1-是）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否系统通用（0-否，1-是）不能为空")
    private Boolean isSys;

    @Schema(description = "是否启用授权码（0-否，1-是）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否启用授权码（0-否，1-是）不能为空")
    private Boolean isAuthorize;

    @Schema(description = "mqtt账号", example = "14828")
    private String mqttAccount;

    @Schema(description = "mqtt密码")
    private String mqttPassword;

    @Schema(description = "产品秘钥")
    private String mqttSecret;

    @Schema(description = "状态（1-未发布，2-已发布）", example = "1")
    private Boolean status;

    @Schema(description = "物模型JSON（属性、功能、事件）")
    private String thingsModelsJson;

    @Schema(description = "设备类型（1-直连设备、2-网关子设备、3-网关设备）", example = "1")
    private Boolean deviceType;

    @Schema(description = "联网方式（1=wifi、2=蜂窝(2G/3G/4G/5G)、3=以太网、4=其他）")
    private Boolean networkMethod;

    @Schema(description = "认证方式（1-简单认证、2-加密认证、3-简单+加密）")
    private Boolean vertificateMethod;

    @Schema(description = "图片地址", example = "https://www.iocoder.cn")
    private String imgUrl;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}
