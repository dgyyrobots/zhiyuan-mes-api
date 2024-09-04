package com.dofast.module.mes.controller.admin.qualityabnormity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 品质异常信息 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class QualityAbnormityBaseVO {

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

    @Schema(description = "到货数量	", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "到货数量	不能为空")
    private Double quantity;

    @Schema(description = "不良代码")
    private String badCode;

    @Schema(description = "检测日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime inspectDate;

    @Schema(description = "检测人员")
    private String inspector;

    @Schema(description = "复检日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime reinspectDate;

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

    @Schema(description = "不良数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "不良数量不能为空")
    private Double badQuantity;

}
