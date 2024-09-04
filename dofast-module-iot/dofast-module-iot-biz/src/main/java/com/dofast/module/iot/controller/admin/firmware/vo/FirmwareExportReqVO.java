package com.dofast.module.iot.controller.admin.firmware.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 产品固件 Excel 导出 Request VO，参数和 FirmwarePageReqVO 是一致的")
@Data
public class FirmwareExportReqVO {

    @Schema(description = "固件名称", example = "芋艿")
    private String firmwareName;

    @Schema(description = "产品ID", example = "1372")
    private Long productId;

    @Schema(description = "产品名称", example = "王五")
    private String productName;

    @Schema(description = "是否系统通用（0-否，1-是）")
    private Boolean isSys;

    @Schema(description = "是否最新版本（0-否，1-是）")
    private Boolean isLatest;

    @Schema(description = "固件版本")
    private Double version;

    @Schema(description = "文件路径")
    private String filePath;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
