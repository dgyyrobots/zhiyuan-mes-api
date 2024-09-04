package com.dofast.module.wms.api.WarehosueApi;

import com.dofast.module.wms.api.WarehosueApi.dto.WarehouseDTO;

public interface WarehouseApi {
    WarehouseDTO getWarehouse(Long id);
    WarehouseDTO selectWmWarehouseByWarehouseCode(String code);
    public WarehouseDTO initVirtualWarehouse();
}
