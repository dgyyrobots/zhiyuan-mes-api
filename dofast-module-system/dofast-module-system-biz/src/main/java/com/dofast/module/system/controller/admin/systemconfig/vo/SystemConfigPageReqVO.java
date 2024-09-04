package com.dofast.module.system.controller.admin.systemconfig.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 参数配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SystemConfigPageReqVO extends PageParam {

    @Schema(description = "应用端口关键字")
    private String appModule;

    @Schema(description = "配置项关键字")
    private String configKey;

    @Schema(description = "配置值json")
    private String value;

    @Schema(description = "描述")
    private String configDesc;

    @Schema(description = "是否启用 1启用 0不启用")
    private Byte isUse;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
