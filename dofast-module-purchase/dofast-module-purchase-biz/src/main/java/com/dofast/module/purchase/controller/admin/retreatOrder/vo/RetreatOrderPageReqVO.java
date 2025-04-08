package com.dofast.module.purchase.controller.admin.retreatOrder.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - ERP仓退单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RetreatOrderPageReqVO extends PageParam {

    @Schema(description = "仓退单编号")
    private String retreatCode;

    @Schema(description = "仓退单名称", example = "王五")
    private String retreatName;

    @Schema(description = "供应商名称", example = "王五")
    private String vendorName;

    @Schema(description = "供应商编号")
    private String vendorCode;

    @Schema(description = "申请人员")
    private String retreatUser;

    @Schema(description = "申请人员")
    private String retreatNick;

    @Schema(description = "仓退原因")
    private String retreatType;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
