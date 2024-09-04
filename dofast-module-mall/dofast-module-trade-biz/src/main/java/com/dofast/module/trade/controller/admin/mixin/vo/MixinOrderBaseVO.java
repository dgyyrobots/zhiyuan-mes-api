package com.dofast.module.trade.controller.admin.mixin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 销售订单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MixinOrderBaseVO {

    @Schema(description = "流程实例的编号")
    private String processInstanceId;


    @Schema(description = "销售单编码")
    private String saleNo;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "标题不能为空")
    private String title;

    @Schema(description = "业务员", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "业务员不能为空")
    private Integer saler;

    @Schema(description = "业务员名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String salerName;

    @Schema(description = "结算方式")
    private Integer settlementMethod;

    @Schema(description = "地址信息")
    private String address;

    @Schema(description = "预计交付时间")
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @NotNull(message = "预计发货时间不能为空")
    private LocalDateTime estimatedDeliveryTime;

    @Schema(description = "订单总金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "25753")
    @NotNull(message = "订单总金额不能为空")
    private BigDecimal price;

    @Schema(description = "附件图片")
    private String pics;

    @Schema(description = "相关商品")
    private String goodsList;

    @Schema(description = "是否打印")
    private String isPrint;

    @Schema(description = "订单状态")
    private String status;

}
