package com.dofast.module.channel.kndpojo.eorder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
@Schema(description = "快递鸟 电子面单 商品 VO")
public class KDNEOrderCommodity {
    @Schema(description = "商品品类（如衣服、文件、电子产品不要传完整的商品明细，如需展示明细，传到Remark字段",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String GoodsName;

    @Schema(description = "商品编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String GoodsCode;

    @Schema(description = "商品件数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer Goodsquantity;

    @Schema(description = "商品价格, 百腾物流必填", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double GoodsPrice;

    @Schema(description = "商品重量Kg", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double GoodsWeight;

    @Schema(description = "商品描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String GoodsDesc;

    @Schema(description = "商品体积m^3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double GoodsVol;
}
