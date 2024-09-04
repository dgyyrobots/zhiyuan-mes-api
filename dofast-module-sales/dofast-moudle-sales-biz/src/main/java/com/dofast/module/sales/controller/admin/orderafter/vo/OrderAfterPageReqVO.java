package com.dofast.module.sales.controller.admin.orderafter.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 售后流程表单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderAfterPageReqVO extends PageParam {

    @Schema(description = "编码", example = "29182")
    private Long code;

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

    @Schema(description = "事务类别")
    private String transactionCategory;

    @Schema(description = "优先级")
    private Integer priority;

    @Schema(description = "添加抄送")
    private String addCopy;

    @Schema(description = "主要负责人")
    private String mainPersonResponsible;

    @Schema(description = "截止时间")
    private LocalDateTime deadline;

    @Schema(description = "标记", example = "随便")
    private String remark;

    @Schema(description = "事务标题")
    private String transactionTitle;

    @Schema(description = "事务内容")
    private String transactionContent;

    @Schema(description = "图片")
    private String picture;

    @Schema(description = "售后结果")
    private String status;

    @Schema(description = "流程实例的编号", example = "31303")
    private String processInstanceId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
