package com.dofast.module.wms.controller.admin.warehouse.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 仓库 Excel 导出 Request VO，参数和 WarehousePageReqVO 是一致的")
@Data
public class WarehouseExportReqVO {

    @Schema(description = "仓库编码")
    private String warehouseCode;

    @Schema(description = "仓库名称", example = "李四")
    private String warehouseName;

    @Schema(description = "位置")
    private String location;

    @Schema(description = "面积")
    private BigDecimal area;

    @Schema(description = "负责人")
    private String charge;

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

    @Schema(description = "寄件人省")
    private String sendState;

    @Schema(description = "寄件人市")
    private String sendCity;

    @Schema(description = "寄件人区/县")
    private String sendDistrict;

    @Schema(description = "寄件人镇")
    private String sendStreet;

    @Schema(description = "寄件人详细地址")
    private String sendDetail;

    @Schema(description = "寄件人姓名", example = "王五")
    private String sendName;

    @Schema(description = "寄件人电话号码")
    private String sendPhone;

    @Schema(description = "寄件人手机号码")
    private String sendMobile;

}
