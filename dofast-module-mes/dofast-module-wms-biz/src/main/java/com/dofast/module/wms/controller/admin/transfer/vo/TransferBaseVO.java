package com.dofast.module.wms.controller.admin.transfer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 转移单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TransferBaseVO {

    @Schema(description = "转移单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "转移单编号不能为空")
    private String transferCode;

    @Schema(description = "转移单名称", example = "张三")
    private String transferName;

    @Schema(description = "转移单类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "转移单类型不能为空")
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
    private LocalDateTime transferDate;

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

    public Long getId(){return null;}
}
