package com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo;

import com.dofast.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 计价模型分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CalculateModelPageReqVO extends PageParam {

    @Schema(description = "名称", example = "王五")
    private String name;

    @Schema(description = "备注", example = "你猜")
    private String description;

    @Schema(description = "计价类型id", example = "19639")
    private Integer categoryId;

    @Schema(description = "表单", example = "25593")
    private Long formId;

    @Schema(description = "表达式类型", example = "1")
    private String type;

    @Schema(description = "表达式")
    private String expression;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
