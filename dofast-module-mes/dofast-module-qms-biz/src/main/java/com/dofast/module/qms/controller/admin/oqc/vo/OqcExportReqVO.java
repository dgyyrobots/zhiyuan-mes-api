package com.dofast.module.qms.controller.admin.oqc.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 出货检验单 Excel 导出 Request VO，参数和 OqcPageReqVO 是一致的")
@Data
public class OqcExportReqVO {

    @Schema(description = "出货检验单编号")
    private String oqcCode;

    @ExcelProperty("订单编号")
    private String sourceCode;

    @Schema(description = "出货检验单名称", example = "赵六")
    private String oqcName;

    @Schema(description = "检验模板ID", example = "22519")
    private Long templateId;

    @Schema(description = "客户ID", example = "15420")
    private Long clientId;

    @Schema(description = "客户编码")
    private String clientCode;

    @Schema(description = "客户名称", example = "张三")
    private String clientName;

    @Schema(description = "批次号")
    private String batchCode;

    @Schema(description = "产品物料ID", example = "14491")
    private Long itemId;

    @Schema(description = "产品物料编码")
    private String itemCode;

    @Schema(description = "产品物料名称", example = "李四")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位")
    private String unitOfMeasure;

    @Schema(description = "最低检测数")
    private BigDecimal quantityMinCheck;

    @Schema(description = "最大不合格数")
    private BigDecimal quantityMaxUnqualified;

    @Schema(description = "发货数量")
    private BigDecimal quantityOut;

    @Schema(description = "本次检测数量")
    private BigDecimal quantityCheck;

    @Schema(description = "不合格数")
    private BigDecimal quantityUnqualified;

    @Schema(description = "合格数量")
    private BigDecimal quantityQuanlified;

    @Schema(description = "致命缺陷率")
    private BigDecimal crRate;

    @Schema(description = "严重缺陷率")
    private BigDecimal majRate;

    @Schema(description = "轻微缺陷率")
    private BigDecimal minRate;

    @Schema(description = "致命缺陷数量")
    private BigDecimal crQuantity;

    @Schema(description = "严重缺陷数量")
    private BigDecimal majQuantity;

    @Schema(description = "轻微缺陷数量")
    private BigDecimal minQuantity;

    @Schema(description = "检测结果")
    private String checkResult;

    @Schema(description = "出货日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] outDate;

    @Schema(description = "检测日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] inspectDate;

    @Schema(description = "检测人员")
    private String inspector;

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

    @Schema(description = "附件")
    private String adjuncts;
}
