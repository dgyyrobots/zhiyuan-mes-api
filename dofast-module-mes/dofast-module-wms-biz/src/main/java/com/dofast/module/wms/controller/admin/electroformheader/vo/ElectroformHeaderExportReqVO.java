package com.dofast.module.wms.controller.admin.electroformheader.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 制版房领料单头 Excel 导出 Request VO，参数和 ElectroformHeaderPageReqVO 是一致的")
@Data
public class ElectroformHeaderExportReqVO {

    @Schema(description = "制版领料单编号")
    private String issueCode;

    @Schema(description = "制版领料单名称", example = "王五")
    private String issueName;

    @Schema(description = "领料日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] issueDate;

    @Schema(description = "单据状态", example = "1")
    private String status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "生产设备名称", example = "李四")
    private String machineryName;

    @Schema(description = "生产设备编码")
    private String machineryCode;

    @Schema(description = "生产设备ID", example = "27745")
    private Long machineryId;

}
