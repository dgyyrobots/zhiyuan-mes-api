package com.dofast.module.trade.controller.admin.mixin.vo;

import com.dofast.module.mes.api.ItemApi.dto.MdItemDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "管理后台 - 销售订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MixinOrderDetailRespVO extends MixinOrderBaseVO {

    @Schema(description = "销售订单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25322")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "物料集合", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<MdItemDTO> mdItemDTOS = new ArrayList<>();

    private List<String> tradeOrderNos;

    public void add(MdItemDTO mdItemDTO){
        mdItemDTOS.add(mdItemDTO);
    }

}
