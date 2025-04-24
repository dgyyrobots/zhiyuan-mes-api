package com.dofast.module.cmms.controller.admin.dvcheckmachinery.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 点检设备分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvCheckMachineryPageReqVO extends PageParam {

    @Schema(description = "计划ID", example = "10978")
    private Long planId;

    @Schema(description = "设备ID", example = "32489")
    private Long machineryId;

    @Schema(description = "设备编码")
    private String machineryCode;

    @Schema(description = "设备名称", example = "芋艿")
    private String machineryName;

    @Schema(description = "品牌")
    private String machineryBrand;

    @Schema(description = "规格型号")
    private String machinerySpec;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

    @Schema(description = "ERP设备编码")
    private String erpMachineryCode;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
