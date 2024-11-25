package com.dofast.module.wms.controller.admin.storagelocation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 库区 Excel 导出 Request VO，参数和 StorageLocationPageReqVO 是一致的")
@Data
public class StorageLocationExportReqVO {

    @Schema(description = "库区编码")
    private String locationCode;

    @Schema(description = "库区名称", example = "芋艿")
    private String locationName;

    @Schema(description = "仓库ID", example = "2055")
    private Long warehouseId;

    @Schema(description = "面积")
    private Object area;

    @Schema(description = "是否开启库位管理")
    private String areaFlag;

    @Schema(description = "备注", example = "你猜")
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

    @Schema(description = "工序编码")
    private String processCode;

}
