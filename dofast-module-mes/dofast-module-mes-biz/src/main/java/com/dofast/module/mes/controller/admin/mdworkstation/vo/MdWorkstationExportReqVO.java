package com.dofast.module.mes.controller.admin.mdworkstation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工作站 Excel 导出 Request VO，参数和 MdWorkstationPageReqVO 是一致的")
@Data
public class MdWorkstationExportReqVO {

    @Schema(description = "工作站编码")
    private String workstationCode;

    @Schema(description = "工作站名称", example = "李四")
    private String workstationName;

    @Schema(description = "工作站地点")
    private String workstationAddress;

    @Schema(description = "所在车间ID", example = "30961")
    private Long workshopId;

    @Schema(description = "所在车间编码")
    private String workshopCode;

    @Schema(description = "所在车间名称", example = "赵六")
    private String workshopName;

    @Schema(description = "工序ID", example = "20399")
    private Long processId;

    @Schema(description = "工序编码")
    private String processCode;

    @Schema(description = "工序名称", example = "赵六")
    private String processName;

    @Schema(description = "线边库ID", example = "721")
    private Long warehouseId;

    @Schema(description = "线边库编码")
    private String warehouseCode;

    @Schema(description = "线边库名称", example = "李四")
    private String warehouseName;

    @Schema(description = "库区ID", example = "10233")
    private Long locationId;

    @Schema(description = "库区编码")
    private String locationCode;

    @Schema(description = "库区名称", example = "张三")
    private String locationName;

    @Schema(description = "库位ID", example = "15797")
    private Long areaId;

    @Schema(description = "库位编码")
    private String areaCode;

    @Schema(description = "库位名称", example = "李四")
    private String areaName;

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

    @Schema(description = "工作站编码")
    private List<Long> ids;

}
