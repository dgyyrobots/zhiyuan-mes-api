package com.dofast.module.mes.controller.admin.mdworkstationtool.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工装夹具资源 Excel 导出 Request VO，参数和 MdWorkstationToolPageReqVO 是一致的")
@Data
public class MdWorkstationToolExportReqVO {

    @Schema(description = "工作站ID", example = "20057")
    private Long workstationId;

    @Schema(description = "工装夹具类型ID", example = "17686")
    private Long toolTypeId;

    @Schema(description = "类型编码")
    private String toolTypeCode;

    @Schema(description = "类型名称", example = "张三")
    private String toolTypeName;

    @Schema(description = "数量")
    private Integer quantity;

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
