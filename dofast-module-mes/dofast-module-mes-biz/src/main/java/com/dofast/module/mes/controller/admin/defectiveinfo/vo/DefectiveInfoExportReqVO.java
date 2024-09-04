package com.dofast.module.mes.controller.admin.defectiveinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 不良品信息管理 Excel 导出 Request VO，参数和 DefectiveInfoPageReqVO 是一致的")
@Data
public class DefectiveInfoExportReqVO {

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

    @Schema(description = "不良描述", example = "你说的对")
    private String badDescription;

    @Schema(description = "到货数量	")
    private Double quantity;

    @Schema(description = "不良数量")
    private Double badQuantity;

    @Schema(description = "抽检数量")
    private Double inspectQty;

    @Schema(description = "故障码名称", example = "赵六")
    private String errorCodeName;

    @Schema(description = "不良发生时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] badTime;

    @Schema(description = "录入人")
    private String entryPerson;

    @Schema(description = "录入时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] entryTime;

    @Schema(description = "复判人")
    private String reinspector;

    @Schema(description = "复检日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reinspectDate;

    @Schema(description = "复检结论")
    private String reinspectConclusion;

    @Schema(description = "检验组")
    private String inspectGroup;

    @Schema(description = "执行状态")
    private String excuteState;

    @Schema(description = "执行状态代码")
    private String excuteCode;

}
