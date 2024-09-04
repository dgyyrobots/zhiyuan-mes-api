package com.dofast.module.mes.controller.admin.defectiveinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 不良品信息管理 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DefectiveInfoBaseVO {

    @Schema(description = "产品物料编码")
    private String itemCode;

    @Schema(description = "产品物料名称", example = "张三")
    private String itemName;

    @Schema(description = "检验批次号")
    private String batchesCode;

    @Schema(description = "检验批次")
    private String batches;

    @Schema(description = "不良描述", example = "你说的对")
    private String badDescription;

    @Schema(description = "到货数量	", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "到货数量	不能为空")
    private Double quantity;

    @Schema(description = "不良数量")
    private Double badQuantity;

    @Schema(description = "抽检数量")
    private Double inspectQty;

    @Schema(description = "故障码名称", example = "赵六")
    private String errorCodeName;

    @Schema(description = "不良发生时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime badTime;

    @Schema(description = "录入人")
    private String entryPerson;

    @Schema(description = "录入时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime entryTime;

    @Schema(description = "复判人")
    private String reinspector;

    @Schema(description = "复检日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime reinspectDate;

    @Schema(description = "复检结论")
    private String reinspectConclusion;

    @Schema(description = "检验组")
    private String inspectGroup;

    @Schema(description = "执行状态")
    private String excuteState;

    @Schema(description = "执行状态代码")
    private String excuteCode;

}
