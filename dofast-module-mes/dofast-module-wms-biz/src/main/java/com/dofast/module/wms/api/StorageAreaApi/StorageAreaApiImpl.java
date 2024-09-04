package com.dofast.module.wms.api.StorageAreaApi;

import com.dofast.module.wms.api.StorageAreaApi.dto.StorageAreaDTO;
import com.dofast.module.wms.convert.storagearea.StorageAreaConvert;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageAreaApiImpl implements StorageAreaApi{
    @Resource
    private StorageAreaService storageAreaService;
    @Override
    public StorageAreaDTO getArea(Long id) {
        StorageAreaDO storageAreaDO = storageAreaService.getStorageArea(id);
        StorageAreaDTO dto = StorageAreaConvert.INSTANCE.convert01(storageAreaDO);
        return dto;
    }

    @Override
    public StorageAreaDTO selectWmStorageAreaByAreaCode(String code) {
        StorageAreaDO storageAreaDO = storageAreaService.selectWmStorageAreaByAreaCode(code);
        StorageAreaDTO dto = StorageAreaConvert.INSTANCE.convert01(storageAreaDO);
        return dto;
    }
}
