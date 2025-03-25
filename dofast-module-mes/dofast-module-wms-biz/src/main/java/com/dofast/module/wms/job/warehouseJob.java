package com.dofast.module.wms.job;

import com.dofast.framework.quartz.core.handler.JobHandler;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.mysql.storagearea.StorageAreaMapper;
import com.dofast.module.wms.dal.mysql.storagelocation.StorageLocationMapper;
import com.dofast.module.wms.service.warehouse.WarehouseOracleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class warehouseJob implements JobHandler {

    @Resource
    private WarehouseOracleService warehouseOracleService;

    @Resource
    private StorageLocationMapper storageLocationMapper;

    @Resource
    private StorageAreaMapper storageAreaMapper;

    /**
     * ERP系统获取仓库信息
     * ERP不管看所属仓库, 仅区分是否为线边仓
     *
     * @param param 参数
     * @return
     * @throws Exception
     */
    @Override
    public String execute(String param) throws Exception {
        // 根据ERP初始化仓库-库区信息
        List<Map<String, Object>> localList = warehouseOracleService.initLocationInfo();

        // 根据ERP初始化库位-库位信息
        List<Map<String, Object>> areaList = warehouseOracleService.initAreaInfo();

        if (localList.isEmpty()) {
            return "无库区信息";
        }
        List<StorageLocationDO> addLocation = new ArrayList<>(); // 新增库区信息
        List<StorageLocationDO> updateLocation = new ArrayList<>(); // 更新库区信息

        List<StorageAreaDO> addArea = new ArrayList<>(); // 新增库位信息
        List<StorageAreaDO> updateArea = new ArrayList<>(); // 更新库位信息

        // 遍历库区信息
        for (Map<String, Object> localMap : localList) {
            // 校验当前是否为线边仓
            String isLineWarehouse = Optional.ofNullable((String) localMap.get("IS_LINE_WAREHOUSE")).orElse("N"); // Y-线边仓 N-仓库
            // 校验当前线边仓信息是否存在, 基于库区的编码进行校验
            boolean flag = !"Y".equals((String) localMap.get("LOCATION_DELETED"));
            // ERP的数据存在相同的库区编码, 追加库区名称比对
            StorageLocationDO location = storageLocationMapper.selectOne(StorageLocationDO::getLocationCode, localMap.get("LOCATION_CODE") ,StorageLocationDO::getLocationName, localMap.get("LOCATION_NAME"), StorageLocationDO::getDeleted, flag);
            // 根据是否为线边仓判断处理逻辑
            if (location != null) {
                location.setLocationName((String) localMap.get("LOCATION_NAME")); // 更新库区名称
                location.setDeleted(!"Y".equals((String) localMap.get("LOCATION_DELETED")));
                updateLocation.add(location);
            } else {
                // 新增线边仓信息
                StorageLocationDO addInfo = new StorageLocationDO();
                if ("Y".equals(isLineWarehouse)) {
                    addInfo.setWarehouseId(4L); // 线边仓ID
                } else {
                    addInfo.setWarehouseId(5L); // 仓库ID
                }
                addInfo.setLocationCode((String) localMap.get("LOCATION_CODE")); // 库区编码
                addInfo.setLocationName((String) localMap.get("LOCATION_NAME")); // 库区名称
                addInfo.setDeleted(!"Y".equals((String) localMap.get("LOCATION_DELETED"))); // 是否删除
                addLocation.add(addInfo);
            }
        }
        if (!addLocation.isEmpty()) {
            storageLocationMapper.insertBatch(addLocation);
        }
        if (!updateLocation.isEmpty()) {
            storageLocationMapper.updateBatch(updateLocation);
        }
        if (areaList.isEmpty()) {
            return "无库位信息";
        }
        for (Map<String, Object> areaMap : areaList) {
            // 获取当前库区信息
            StorageLocationDO location = storageLocationMapper.selectOne(StorageLocationDO::getLocationCode, areaMap.get("LOCATION_CODE") , StorageLocationDO::getLocationName, areaMap.get("LOCATION_NAME"));
            Long locationId = 0L; // 库区ID
            if (location != null) {
                locationId = location.getId();
            }
            if (locationId == 0L) { // 当前库区信息不存在, 跳过
                continue;
            }
            StorageAreaDO area = storageAreaMapper.selectOne(StorageAreaDO::getAreaCode, (String) areaMap.get("AREA_CODE"), StorageAreaDO::getLocationId, locationId);
            if (area != null) {
                // 变更库位信息
                area.setAreaName((String) areaMap.get("AREA_NAME")); // 更新库位名称
                area.setDeleted(!"Y".equals((String) areaMap.get("AREA_DELETED")));
                updateArea.add(area);
            } else {
                // 新增库位信息
                StorageAreaDO addInfo = new StorageAreaDO();
                addInfo.setLocationId(locationId); // 库区ID
                addInfo.setAreaCode((String) areaMap.get("AREA_CODE")); // 库位编码
                addInfo.setAreaName((String) areaMap.get("AREA_NAME")); // 库位名称
                addInfo.setDeleted(!"Y".equals((String) areaMap.get("AREA_DELETED"))); // 是否删除
                addInfo.setEnableFlag((String) areaMap.get("AREA_DELETED")); // 是否启用
                addArea.add(addInfo);
            }
        }
        if (!addArea.isEmpty()) {
            storageAreaMapper.insertBatch(addArea);
        }
        if (!updateArea.isEmpty()) {
            storageAreaMapper.updateBatch(updateArea);
        }
        return "success";
    }
}
