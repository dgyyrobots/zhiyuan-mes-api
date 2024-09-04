package com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Config implements Serializable {
    @Schema(description = "快递编码")
    private String code;

    @Schema(description = "客户账号")
    private String CustomerName;

    @Schema(description = "客户密码")
    private String CustomerPwd;

    @Schema(description = "月结账号")
    private String MonthCode;

    @Schema(description = "服务类型")
    private Integer ExpType;

    /**
     * 1：现付
     * 2：到付
     * 3：月结
     * 4：第三方付
     */
    @Schema(description = "运费支付方式 ")
    private Integer PayType;
}