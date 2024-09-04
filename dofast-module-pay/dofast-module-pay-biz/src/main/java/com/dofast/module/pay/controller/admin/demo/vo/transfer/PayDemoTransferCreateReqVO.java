package com.dofast.module.pay.controller.admin.demo.vo.transfer;

import com.dofast.framework.common.util.validation.ValidationUtils;
import com.dofast.framework.common.validation.InEnum;
import com.dofast.framework.pay.core.enums.transfer.PayTransferTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author jason
 */
@Schema(description = "管理后台 - 示例转账单创建 Request VO")
@Data
public class PayDemoTransferCreateReqVO {

    @Schema(description = "转账类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "转账类型不能为空")
    @InEnum(PayTransferTypeEnum.class)
    private Integer type;

    @NotNull(message = "转账金额不能为空")
    @Min(value = 1, message = "转账金额必须大于零")
    private Integer price;

    @Schema(description = "收款人姓名", example = "test1")
    @NotBlank(message = "收款人姓名不能为空", groups = {com.dofast.module.pay.enums.transfer.PayTransferTypeEnum.Alipay.class})
    private String userName;

    // ========== 支付宝转账相关字段 ==========
    @Schema(description = "支付宝登录号,支持邮箱和手机号格式", example = "test1@@sandbox.com")
    @NotBlank(message = "支付宝登录号不能为空", groups = {com.dofast.module.pay.enums.transfer.PayTransferTypeEnum.Alipay.class})
    private String alipayLogonId;



    // ========== 微信转账相关字段 ==========
    @Schema(description = "微信 openId", example = "oLefc4g5Gxx")
    @NotBlank(message = "微信 openId 不能为空", groups = {com.dofast.module.pay.enums.transfer.PayTransferTypeEnum.WxPay.class})
    private String openid;

    // ========== 转账到银行卡和钱包相关字段 待补充 ==========


    public void validate(Validator validator) {
        PayTransferTypeEnum transferType = PayTransferTypeEnum.typeOf(type);
        switch (transferType) {
            case ALIPAY_BALANCE: {
                ValidationUtils.validate(validator, this, com.dofast.module.pay.enums.transfer.PayTransferTypeEnum.Alipay.class);
                break;
            }
            case WX_BALANCE: {
                ValidationUtils.validate(validator, this, com.dofast.module.pay.enums.transfer.PayTransferTypeEnum.WxPay.class);
                break;
            }
            default: {
                throw new UnsupportedOperationException("待实现");
            }
        }
    }

}
