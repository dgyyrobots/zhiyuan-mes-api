package com.dofast.module.pro.controller.admin.transorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 流转单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TransOrderBaseVO {

    @Schema(description = "流转单编号")
    private String transOrderCode;

    @Schema(description = "生产任务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9839")
    @NotNull(message = "生产任务ID不能为空")
    private Long taskId;

    @Schema(description = "生产任务编号")
    private String taskCode;

    @Schema(description = "工作站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16452")
    @NotNull(message = "工作站ID不能为空")
    private Long workstationId;

    @Schema(description = "工作站编号")
    private String workstationCode;

    @Schema(description = "工作站名称", example = "芋艿")
    private String workstationName;

    @Schema(description = "工序ID", example = "24987")
    private Long processId;

    @Schema(description = "工序编号")
    private String processCode;

    @Schema(description = "工序名称", example = "王五")
    private String processName;

    @Schema(description = "生产工单ID", example = "21190")
    private Long workorderId;

    @Schema(description = "生产工单编号")
    private String workorderCode;

    @Schema(description = "生产工单名称", example = "张三")
    private String workorderName;

    @Schema(description = "批次号")
    private String batchCode;

    @Schema(description = "产品物料ID", example = "9562")
    private Long itemId;

    @Schema(description = "产品物料编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "产品物料编码不能为空")
    private String itemCode;

    @Schema(description = "产品物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotNull(message = "产品物料名称不能为空")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "单位不能为空")
    private String unitOfMeasure;

    @Schema(description = "赋码地址", example = "https://www.iocoder.cn")
    private String barcodeUrl;

    @Schema(description = "流转数量")
    private BigDecimal quantityTransfered;

    @Schema(description = "生产日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime produceDate;

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

    public Long getId(){return null;}
}
