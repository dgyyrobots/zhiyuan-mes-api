package com.dofast.module.report.controller.admin.goview.vo.code;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - GoView登录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GoViewCodePageReqVO extends PageParam {

    @Schema(description = "状态(0未使用 1已扫码 2已确认)", example = "1")
    private Long status;

    @Schema(description = "过期时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expireTime;

    @Schema(description = "PDA用户ID", example = "29789")
    private String pdaUserId;

    @Schema(description = "PDA原始token")
    private String pdaToken;

    @Schema(description = "生成的goview token")
    private String goviewToken;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
