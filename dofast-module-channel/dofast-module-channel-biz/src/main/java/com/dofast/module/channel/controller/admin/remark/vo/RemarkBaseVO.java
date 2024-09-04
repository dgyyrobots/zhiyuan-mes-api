package com.dofast.module.channel.controller.admin.remark.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 订单备注 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class RemarkBaseVO {

    @Schema(description = "关联的商城订单的ID", example = "2086")
    @NotNull(message = "商城订单ID不能为空", groups={TradeOrderRemark.class})
    private Long tradeOrderId;

    @Schema(description = "排序权重", requiredMode = Schema.RequiredMode.NOT_REQUIRED, defaultValue = "0")
    private Integer sortCode;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "你猜")
    @NotNull(message = "备注不能为空")
    private String remark;

    @Schema(description = "种类", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "种类不能为空")
    private String type;

    @Schema(description = "销售订单ID", example = "20604")
    @NotNull(message = "销售订单ID不能为空", groups={MixinOrderRemark.class})
    private Long salId;

    public interface TradeOrderRemark{}
    public interface MixinOrderRemark{}
}
