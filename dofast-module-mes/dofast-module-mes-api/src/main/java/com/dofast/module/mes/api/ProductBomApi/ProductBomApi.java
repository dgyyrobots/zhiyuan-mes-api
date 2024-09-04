package com.dofast.module.mes.api.ProductBomApi;

import com.dofast.module.mes.api.ProductBomApi.dto.MdProductBomDTO;

import java.util.List;

public interface ProductBomApi {

    List<MdProductBomDTO> selectListByItemId(Long itemId);
}
