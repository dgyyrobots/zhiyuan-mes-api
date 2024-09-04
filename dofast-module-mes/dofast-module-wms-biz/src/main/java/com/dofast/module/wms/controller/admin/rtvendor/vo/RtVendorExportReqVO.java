package com.dofast.module.wms.controller.admin.rtvendor.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 供应商退货 Excel 导出 Request VO，参数和 RtVendorPageReqVO 是一致的")
@Data
public class RtVendorExportReqVO {

    @Schema(description = "退货单编号")
    private String rtCode;

    @Schema(description = "退货单名称", example = "芋艿")
    private String rtName;

    @Schema(description = "采购订单编号")
    private String poCode;

    @Schema(description = "供应商ID", example = "1021")
    private Long vendorId;

    @Schema(description = "供应商编码")
    private String vendorCode;

    @Schema(description = "供应商名称", example = "张三")
    private String vendorName;

    @Schema(description = "供应商简称")
    private String vendorNick;

    @Schema(description = "批次号")
    private String batchCode;

    @Schema(description = "退货日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] rtDate;

    @Schema(description = "单据状态", example = "2")
    private String status;

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

}
