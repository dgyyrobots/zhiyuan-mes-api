package com.dofast.module.iot.controller.admin.productauthorize.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 产品授权码 Excel 导出 Request VO，参数和 ProductAuthorizePageReqVO 是一致的")
@Data
public class ProductAuthorizeExportReqVO {

    @Schema(description = "授权码")
    private String authorizeCode;

    @Schema(description = "产品ID", example = "6229")
    private Long productId;

    @Schema(description = "设备ID", example = "26711")
    private Long deviceId;

    @Schema(description = "设备编号")
    private String serialNumber;

    @Schema(description = "用户ID", example = "23587")
    private Long userId;

    @Schema(description = "用户名称", example = "赵六")
    private String userName;

    @Schema(description = "状态（1-未使用，2-使用中）", example = "2")
    private Boolean status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
