package com.dofast.module.sales.controller.admin.orderafter.vo;

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
 * 售后流程表单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class OrderAfterBaseVO {

    @Schema(description = "编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "29182")
    @NotNull(message = "编码不能为空")
    private String code;

    @Schema(description = "关联订单id")
    private String associatedBusiness;

    @Schema(description = "业务数据")
    private String businessData;

    @Schema(description = "关联对象")
    private String associatedObjects;

    @Schema(description = "对象名称", example = "赵六")
    private String objectName;

    @Schema(description = "关联店铺")
    private String associatedStores;

    @Schema(description = "关联仓库")
    private String associatedRepository;

    @Schema(description = "关联金额")
    private Double associatedAmounts;

    @Schema(description = "事务类别", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "事务类别不能为空")
    private String transactionCategory;

    @Schema(description = "优先级")
    private Integer priority;

    @Schema(description = "添加抄送")
    private String addCopy;

    @Schema(description = "主要负责人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主要负责人不能为空")
    private String mainPersonResponsible;

    @Schema(description = "截止时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime deadline;

    @Schema(description = "标记", example = "随便")
    private String remark;

    @Schema(description = "事务标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "事务标题不能为空")
    private String transactionTitle;

    @Schema(description = "事务内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "事务内容不能为空")
    private String transactionContent;

    @Schema(description = "图片")
    private String picture;

    @Schema(description = "售后结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "售后结果不能为空")
    private String status;

    @Schema(description = "流程实例的编号", example = "31303")
    private String processInstanceId;

}
