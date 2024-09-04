package com.dofast.module.channel.api.ordergoods;

import com.dofast.module.channel.api.ordergoods.dto.OrderGoodsGetDTO;

import java.util.Collection;
import java.util.List;

public interface OrderGoodsApi {
    public int updateAddressIdByRefOid(String refOid, Integer addressId);

    public List<OrderGoodsGetDTO> getOrderGoodsByRefOid(String refOid);
}
