package com.dofast.module.mes.controller.admin.electroplatelog.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 制版房记录 Excel 导出 Request VO，参数和 ElectroplateLogPageReqVO 是一致的")
@Data
public class ElectroplateLogExportReqVO {

    @Schema(description = "设备ID", example = "18636")
    private Long machineryId;

    @Schema(description = "设备编码")
    private String machineryCode;

    @Schema(description = "设备名称", example = "制版机1号")
    private String machineryName;

    @Schema(description = "比例", example = "33")
    private String proportion;

    @Schema(description = "温度", example = "35℃")
    private String temperature;

    @Schema(description = "PH值", example = "3.9")
    private String phValue;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
