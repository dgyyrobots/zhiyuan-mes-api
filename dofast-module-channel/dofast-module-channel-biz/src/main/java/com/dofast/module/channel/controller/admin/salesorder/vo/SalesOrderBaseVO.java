package com.dofast.module.channel.controller.admin.salesorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 销售订单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class SalesOrderBaseVO {

    @Schema(description = "销售单编码")
    private String saleNo;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "标题不能为空")
    private String title;

    @Schema(description = "结算方式(1现结2月结3周结)")
    private Integer settlementMethod;

    @Schema(description = "订单总金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "25849")
    @NotNull(message = "订单总金额不能为空")
    private BigDecimal price;

    @Schema(description = "业务员id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "业务员id不能为空")
    private Integer saler;

}
