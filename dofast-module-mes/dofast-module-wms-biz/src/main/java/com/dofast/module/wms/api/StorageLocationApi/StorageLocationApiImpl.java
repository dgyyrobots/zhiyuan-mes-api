package com.dofast.module.wms.api.StorageLocationApi;

import com.dofast.module.wms.api.StorageLocationApi.dto.StorageLocationDTO;
import com.dofast.module.wms.convert.storagelocation.StorageLocationConvert;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageLocationApiImpl implements StorageLocationApi{
    @Resource
    private StorageLocationService storageLocationService;
    @Override
    public StorageLocationDTO getLocation(Long id) {
        StorageLocationDO locationDO = storageLocationService.getStorageLocation(id);
        StorageLocationDTO dto = StorageLocationConvert.INSTANCE.convert01(locationDO);
        return dto;
    }

    @Override
    public StorageLocationDTO selectWmStorageLocationByLocationCode(String code) {
        StorageLocationDO locationDO = storageLocationService.selectWmStorageLocationByLocationCode(code);
        StorageLocationDTO dto = StorageLocationConvert.INSTANCE.convert01(locationDO);
        return dto;
    }
}
