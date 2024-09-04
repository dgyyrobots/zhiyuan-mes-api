package com.dofast.module.channel.api.order;

import com.dofast.module.channel.api.order.dto.OrderBaseDTO;

public interface OrderApi {
    /**
     * 根据关联的第三方的oid更新addressId
     * @param refOid
     * @param addressId
     * @return
     */
    public int updateAddressIdByRefOid(String refOid, Integer addressId);

    public OrderBaseDTO selectById(String id);

    public int updateByIdAndStatus(Integer id, String status, OrderBaseDTO update);

}
