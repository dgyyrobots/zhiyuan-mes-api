package com.dofast.module.sales.controller.admin.bpm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 售后流程表单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class OrderAfterBpmBaseVO implements Serializable {

    /*@Schema(description = "申请人的用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "29182")
//    @NotNull(message = "申请人的用户编号不能为空")
    private Long userId;*/

    @Schema(description = "编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编码不能为空")
    private String code;

    @Schema(description = "关联订单id")
    private String associatedBusiness;

    /*@Schema(description = "业务数据")
    private String businessData;

    @Schema(description = "关联对象")
    private String associatedObjects;

    @Schema(description = "对象名称", example = "赵六")
    private String objectName;*/

    @Schema(description = "关联店铺")
    private String associatedStores;

    @Schema(description = "关联仓库")
    private String associatedRepository;

/*    @Schema(description = "关联金额")
    private Double associatedAmounts;*/

    @Schema(description = "事务类别", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "事务类别不能为空")
    private String transactionCategory;

/*    @Schema(description = "优先级")
    private Integer priority;

    @Schema(description = "添加抄送")
    private String addCopy;*/

    @Schema(description = "主要负责人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主要负责人不能为空")
    private String mainPersonResponsible;

    @Schema(description = "截止时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "截至时间不能为空")
    private String deadline;

    @Schema(description = "标记", example = "随便")
    private String remark;

    @Schema(description = "事务标题"/*, requiredMode = Schema.RequiredMode.REQUIRED*/)
//    @NotNull(message = "事务标题不能为空")
    private String transactionTitle;

    @Schema(description = "事务内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "事务内容不能为空")
    private String transactionContent;

    @Schema(description = "图片")
    private String picture;

    @Schema(description = "售后结果")
    private String status;

    @Schema(description = "流程实例的编号", example = "31303")
    private String processInstanceId;

}
