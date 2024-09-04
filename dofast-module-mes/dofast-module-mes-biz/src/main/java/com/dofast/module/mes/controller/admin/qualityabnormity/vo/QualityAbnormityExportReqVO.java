package com.dofast.module.mes.controller.admin.qualityabnormity.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 品质异常信息 Excel 导出 Request VO，参数和 QualityAbnormityPageReqVO 是一致的")
@Data
public class QualityAbnormityExportReqVO {

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "产品物料编码")
    private String itemCode;

    @Schema(description = "产品物料名称", example = "张三")
    private String itemName;

    @Schema(description = "检验批次号")
    private String batchesCode;

    @Schema(description = "检验批次")
    private String batches;

    @Schema(description = "不良描述", example = "你猜")
    private String badDescription;

    @Schema(description = "到货数量	")
    private Double quantity;

    @Schema(description = "不良代码")
    private String badCode;

    @Schema(description = "检测日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] inspectDate;

    @Schema(description = "检测人员")
    private String inspector;

    @Schema(description = "复检日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reinspectDate;

    @Schema(description = "复检人员")
    private String reinspector;

    @Schema(description = "复检结论")
    private String reinspectConclusion;

    @Schema(description = "工单号")
    private Long orderNum;

    @Schema(description = "销售单号")
    private String saleNum;

    @Schema(description = "检验组")
    private String inspectGroup;

    @Schema(description = "不良数量")
    private Double badQuantity;

}
