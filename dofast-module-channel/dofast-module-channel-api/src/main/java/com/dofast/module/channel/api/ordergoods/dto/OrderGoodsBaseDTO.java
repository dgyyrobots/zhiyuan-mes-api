package com.dofast.module.channel.api.ordergoods.dto;

import lombok.Data;

@Data
public class OrderGoodsBaseDTO {
    private Long id;
    private String refOid;
    private String refOlId;
    private String refSpuId;
}
