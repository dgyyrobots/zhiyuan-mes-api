package com.dofast.module.cmms.controller.admin.dvmachinerytype.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备类型 Excel 导出 Request VO，参数和 DvMachineryTypePageReqVO 是一致的")
@Data
public class DvMachineryTypeExportReqVO {

    @Schema(description = "设备类型编码")
    private String machineryTypeCode;

    @Schema(description = "设备类型名称", example = "赵六")
    private String machineryTypeName;

    @Schema(description = "父类型ID", example = "23622")
    private Long parentTypeId;

    @Schema(description = "所有父节点ID")
    private String ancestors;

    @Schema(description = "是否启用")
    private String enableFlag;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
