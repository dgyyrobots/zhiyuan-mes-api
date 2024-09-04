package com.dofast.module.wms.api.StorageLocationApi;

import com.dofast.module.wms.api.StorageLocationApi.dto.StorageLocationDTO;

public interface StorageLocationApi {
    StorageLocationDTO getLocation(Long id);
    StorageLocationDTO selectWmStorageLocationByLocationCode(String code);
}
