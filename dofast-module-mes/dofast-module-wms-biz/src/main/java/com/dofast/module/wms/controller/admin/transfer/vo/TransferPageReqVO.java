package com.dofast.module.wms.controller.admin.transfer.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 转移单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransferPageReqVO extends PageParam {

    @Schema(description = "转移单编号")
    private String transferCode;

    @Schema(description = "转移单名称", example = "张三")
    private String transferName;

    @Schema(description = "转移单类型", example = "1")
    private String transferType;

    @Schema(description = "目的地")
    private String destination;

    @Schema(description = "承运商")
    private String carrier;

    @Schema(description = "托运单号")
    private String bookingNote;

    @Schema(description = "收货人")
    private String receiver;

    @Schema(description = "收货人名称")
    private String receiverNick;

    @Schema(description = "移出仓库ID", example = "17717")
    private Long fromWarehouseId;

    @Schema(description = "移出仓库编码")
    private String fromWarehouseCode;

    @Schema(description = "移出仓库名称", example = "赵六")
    private String fromWarehouseName;

    @Schema(description = "移入仓库ID", example = "15425")
    private Long toWarehouseId;

    @Schema(description = "移入仓库编码")
    private String toWarehouseCode;

    @Schema(description = "移入仓库名称", example = "王五")
    private String toWarehouseName;

    @Schema(description = "转移日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] transferDate;

    @Schema(description = "单据状态", example = "1")
    private String status;

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
