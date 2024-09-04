package com.dofast.module.wms.api.WarehouseApi;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.api.WarehosueApi.WarehouseApi;
import com.dofast.module.wms.api.WarehosueApi.dto.WarehouseDTO;
import com.dofast.module.wms.convert.warehouse.WarehouseConvert;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.dal.mysql.storagearea.StorageAreaMapper;
import com.dofast.module.wms.dal.mysql.storagelocation.StorageLocationMapper;
import com.dofast.module.wms.dal.mysql.warehouse.WarehouseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WarehouseApiImpl implements WarehouseApi {
    @Resource
    private WarehouseMapper warehouseMapper;
    @Resource
    private StorageLocationMapper storageLocationMapper;
    @Resource
    private StorageAreaMapper storageAreaMapper;


    @Override
    public WarehouseDTO getWarehouse(Long id) {
        WarehouseDO warehouseDO = warehouseMapper.selectById(id);
        WarehouseDTO dto = WarehouseConvert.INSTANCE.convert01(warehouseDO);
        return dto;
    }

    @Override
    public WarehouseDTO selectWmWarehouseByWarehouseCode(String code) {
        WarehouseDO warehouseDO = warehouseMapper.selectById(code);
        WarehouseDTO dto = WarehouseConvert.INSTANCE.convert01(warehouseDO);
        return dto;
    }

    @Override
    public WarehouseDTO initVirtualWarehouse() {
        WarehouseDO warehouse = warehouseMapper.selectWmWarehouseByWarehouseCode(Constant.VIRTUAL_WH);
        if(!StrUtils.isNotNull(warehouse)){
            warehouse = new WarehouseDO();
            warehouse.setWarehouseCode(Constant.VIRTUAL_WH);
            warehouse.setWarehouseName("线边库-虚拟");
            warehouseMapper.insert(warehouse);
        }


        StorageLocationDO location = storageLocationMapper.selectWmStorageLocationByLocationCode(Constant.VIRTUAL_WS);
        if(!StrUtils.isNotNull(location)){
            location = new StorageLocationDO();
            location.setWarehouseId(warehouse.getId());
            location.setLocationCode(Constant.VIRTUAL_WS);
            location.setLocationName("线边库库区-虚拟");
            location.setAreaFlag(Constant.YES);
            storageLocationMapper.insert(location);
        }


        StorageAreaDO area = storageAreaMapper.selectWmStorageAreaByAreaCode(Constant.VIRTUAL_WA);
        if(!StrUtils.isNotNull(area)){
            area = new StorageAreaDO();
            area.setLocationId(location.getId());
            area.setAreaCode(Constant.VIRTUAL_WA);
            area.setAreaName("线边库库位-虚拟");
            storageAreaMapper.insert(area);
        }
        WarehouseDTO dto =WarehouseConvert.INSTANCE.convert01(warehouse);
        return dto;
    }
}
