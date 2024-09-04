package com.dofast.module.channel.controller.admin.address.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 交易客户 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AddressBaseVO {

    @Schema(description = "相关单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15544")
    @NotNull(message = "相关单ID不能为空")
    private String refOid;

    @Schema(description = "店铺编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "店铺编码不能为空")
    private String posCode;

    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "手机号不能为空")
    private String mobile;

    @Schema(description = "渠道", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "渠道不能为空")
    private String refType;

    @Schema(description = "收货人国家", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "收货人国家不能为空")
    private String receiverCountry;

    @Schema(description = "收货人省", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "收货人省不能为空")
    private String receiverState;

    @Schema(description = "收货人市", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "收货人市不能为空")
    private String receiverCity;

    @Schema(description = "收件人区/县", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "收件人区/县不能为空")
    private String receiverDistrict;

    @Schema(description = "收货人镇", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "收货人镇不能为空")
    private String receiverTown;

    @Schema(description = "收货人ID字段，可用于区分多个订单是否属于同一个收货人", requiredMode = Schema.RequiredMode.REQUIRED, example = "26583")
    @NotNull(message = "收货人ID字段，可用于区分多个订单是否属于同一个收货人不能为空")
    private String receiverId;

    @Schema(description = "买家昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "买家昵称不能为空")
    private String openBuyerNick;

    @Schema(description = "买家昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "30898")
    @NotNull(message = "买家昵称不能为空")
    private String openBuyerId;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "你猜")
//    @NotNull(message = "备注不能为空")
    private String remark;

    @Schema(description = "关联收货地址ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "26690")
    private Integer addressId;

    @Schema(description = "关联用户ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "16979")
    private Integer userId;


}
