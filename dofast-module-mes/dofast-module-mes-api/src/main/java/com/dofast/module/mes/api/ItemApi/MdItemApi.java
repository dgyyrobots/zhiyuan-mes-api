package com.dofast.module.mes.api.ItemApi;

import com.dofast.module.mes.api.ItemApi.dto.MdItemDTO;

public interface MdItemApi {
    public MdItemDTO getMditemById(Long itemId);

    public MdItemDTO getMdItemByCode(String itemCode);
}
