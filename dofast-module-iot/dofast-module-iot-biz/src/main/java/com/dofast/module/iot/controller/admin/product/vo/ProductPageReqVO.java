package com.dofast.module.iot.controller.admin.product.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 产品分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductPageReqVO extends PageParam {

    @Schema(description = "产品名称", example = "李四")
    private String productName;

    @Schema(description = "产品分类ID", example = "10115")
    private Long categoryId;

    @Schema(description = "产品分类名称", example = "李四")
    private String categoryName;

    @Schema(description = "是否系统通用（0-否，1-是）")
    private Boolean isSys;

    @Schema(description = "是否启用授权码（0-否，1-是）")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
