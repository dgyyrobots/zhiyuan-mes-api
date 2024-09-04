package com.dofast.module.channel.dian3logisticspojo.logistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;

@Data
@ToString(callSuper = true)
@Schema(description = "Dian3 物流列表查询 增值服务 VO")
public class Dian3LogisticsReq {
    @Schema(description = "物流名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    @Schema(description = "物流编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String code;

    @Schema(description = "状态,VALID(有效), INVALID(禁用), DELETED(删除)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String status;

    @Schema(description = "返回页码 默认 1 当前采用分页返回，数量和页数会一起传，如果不传，则采用 默认值", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer pageNo = 1;

    @Schema(description = "返回数量，默认 50。最大 100", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Max(100)
    private Integer pageSize = 50;

    @Schema(description = "店铺编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String posCode;
}
