package com.dofast.module.hr.controller.admin.tradecommission.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 工资提成 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TradecommissionBaseVO {

    @Schema(description = "提成类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "提成类型不能为空")
    private String type;

    @Schema(description = "科目类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "科目类型不能为空")
    private String signType;

    @Schema(description = "产品类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "产品类型不能为空")
    private String saleType;

    @Schema(description = "业务", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "业务不能为空")
    private Integer trade;

    @Schema(description = "合同", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "合同不能为空")
    private Integer contract;

    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED, example = "20244")
    @NotNull(message = "账号不能为空")
    private String account;

    @Schema(description = "业绩额度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "业绩额度不能为空")
    private BigDecimal contribution;

    @Schema(description = "提成比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "提成比例不能为空")
    private BigDecimal rate;

    @Schema(description = "提成金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "提成金额不能为空")
    private BigDecimal amount;

    @Schema(description = "描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "描述不能为空")
    private String desc;

}
