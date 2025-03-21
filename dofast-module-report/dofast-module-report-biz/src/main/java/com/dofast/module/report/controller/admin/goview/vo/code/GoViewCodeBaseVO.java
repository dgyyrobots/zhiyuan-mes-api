package com.dofast.module.report.controller.admin.goview.vo.code;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * GoView登录 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class GoViewCodeBaseVO {

    @Schema(description = "随机码", example = "1asd151")
    private String code;

    @Schema(description = "状态(0未使用 1已扫码 2已确认)", example = "1")
    private Long status;

    @Schema(description = "过期时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime expireTime;

    @Schema(description = "PDA用户ID", example = "29789")
    private String pdaUserId;

    @Schema(description = "PDA原始token")
    private String pdaToken;

    @Schema(description = "生成的goview token")
    private String goviewToken;

}
