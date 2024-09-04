package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 资金账号 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CashDepositorBaseVO {

    @Schema(description = "账户类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "bank")
    @NotNull(message = "账户类型不能为空")
    private String type;

    @Schema(description = "简称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "简称不能为空")
    private String abbr;

    @Schema(description = "账户名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "账户名称不能为空")
    private String title;

    @Schema(description = "账户标签", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "账户标签不能为空")
    private String tags;

    @Schema(description = "供应者", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "供应者不能为空")
    private String provider;

    @Schema(description = "开户银行", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开户银行不能为空")
    private String bank;

    @Schema(description = "客户编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "客户编码不能为空")
    private String customerNo;

    @Schema(description = "银行账号", requiredMode = Schema.RequiredMode.REQUIRED, example = "23260")
    @NotNull(message = "银行账号不能为空")
    private String account;

    @Schema(description = "联合银行编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "联合银行编码不能为空")
    private String unionBankNo;

    @Schema(description = "清算银行编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "清算银行编码不能为空")
    private String clearingBankNo;

    @Schema(description = "是否公开", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "是否公开不能为空")
    private String isPublic;

    @Schema(description = "当前余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前余额不能为空")
    private String currency;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "normal")
    @NotNull(message = "状态不能为空")
    private String status;

}
