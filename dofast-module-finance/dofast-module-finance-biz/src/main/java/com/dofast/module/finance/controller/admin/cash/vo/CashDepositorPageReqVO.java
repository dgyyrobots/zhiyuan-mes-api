package com.dofast.module.finance.controller.admin.cash.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资金账号分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashDepositorPageReqVO extends PageParam {

    @Schema(description = "账户类型", example = "bank")
    private String type;

    @Schema(description = "简称")
    private String abbr;

    @Schema(description = "账户名称")
    private String title;

    @Schema(description = "账户标签")
    private String tags;

    @Schema(description = "供应者")
    private String provider;

    @Schema(description = "开户银行")
    private String bank;

    @Schema(description = "客户编码")
    private String customerNo;

    @Schema(description = "银行账号", example = "23260")
    private String account;

    @Schema(description = "联合银行编码")
    private String unionBankNo;

    @Schema(description = "清算银行编码")
    private String clearingBankNo;

    @Schema(description = "是否公开", example = "0")
    private String isPublic;

    @Schema(description = "当前余额")
    private String currency;

    @Schema(description = "状态", example = "normal")
    private String status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
