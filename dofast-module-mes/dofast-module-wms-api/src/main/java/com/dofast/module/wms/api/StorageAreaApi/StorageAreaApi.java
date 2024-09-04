package com.dofast.module.wms.api.StorageAreaApi;

import com.dofast.module.wms.api.StorageAreaApi.dto.StorageAreaDTO;

public interface StorageAreaApi {
    StorageAreaDTO getArea(Long id);
    StorageAreaDTO selectWmStorageAreaByAreaCode(String code);
}
