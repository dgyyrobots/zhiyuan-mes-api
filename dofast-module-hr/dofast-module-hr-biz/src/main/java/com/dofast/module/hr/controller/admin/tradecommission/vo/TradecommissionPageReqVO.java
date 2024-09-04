package com.dofast.module.hr.controller.admin.tradecommission.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工资提成分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TradecommissionPageReqVO extends PageParam {

    @Schema(description = "提成类型", example = "1")
    private String type;

    @Schema(description = "科目类型", example = "1")
    private String signType;

    @Schema(description = "产品类型", example = "1")
    private String saleType;

    @Schema(description = "业务")
    private Integer trade;

    @Schema(description = "合同")
    private Integer contract;

    @Schema(description = "账号", example = "20244")
    private String account;

    @Schema(description = "业绩额度")
    private BigDecimal contribution;

    @Schema(description = "提成比例")
    private BigDecimal rate;

    @Schema(description = "提成金额")
    private BigDecimal amount;

    @Schema(description = "描述")
    private String desc;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
