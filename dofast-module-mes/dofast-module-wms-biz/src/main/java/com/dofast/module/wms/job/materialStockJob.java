package com.dofast.module.wms.job;

import com.dofast.framework.quartz.core.handler.JobHandler;
import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;
import com.dofast.module.mes.dal.mysql.mditem.MdItemMapper;
import com.dofast.module.wms.dal.dataobject.materialstock.MaterialStockDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.dal.mysql.materialstock.MaterialStockMapper;
import com.dofast.module.wms.dal.mysql.storagearea.StorageAreaMapper;
import com.dofast.module.wms.dal.mysql.storagelocation.StorageLocationMapper;
import com.dofast.module.wms.service.materialstock.MaterialStockOracleService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class materialStockJob implements JobHandler {

    @Resource
    private MaterialStockOracleService materialStockOracleService;

    @Resource
    private MaterialStockMapper materialStockMapper;

    @Resource
    private MdItemMapper mdItemMapper;

    @Resource
    private StorageLocationMapper storageLocationMapper;

    @Resource
    private StorageAreaMapper storageAreaMapper;

    @Resource
    private WarehouseService warehouseService;

    @Override
    public String execute(String param) throws Exception {
        // 初始化ERP数据
        List<Map<String, Object>> materiaList = materialStockOracleService.initMaterialStock();
        if (materiaList.isEmpty()) {
            return "无库存信息";
        }
        List<MaterialStockDO> addList = new ArrayList<>();
        List<MaterialStockDO> updateList = new ArrayList<>();
        for (Map<String, Object> material : materiaList) {
            // 获取物料号, 去物料表中获取详细信息
            String itemCode = (String) material.get("ITEM_CODE");
            MdItemDO item = mdItemMapper.selectOne(MdItemDO::getItemCode, itemCode);
            // 获取库区编码
            String stockCode = (String) material.get("LOCATION_CODE");
            // 库区信息
            StorageLocationDO location = storageLocationMapper.selectOne(StorageLocationDO::getLocationCode, stockCode);
            // 获取库位编码
            String areaCode = (String) material.get("AREA_CODE");
            // 库位信息
            StorageAreaDO area = storageAreaMapper.selectOne(StorageAreaDO::getLocationId, location.getId(), StorageAreaDO::getAreaCode, areaCode);
            // 获取当前的批次号, 去物料表中获取详细信息
            String batch = (String) material.get("BATCH_CODE");
            // 基于物料号与批次号视为同一批物料, 校验是否存在库存
            System.out.println("物料号:" + itemCode + " 批次号:" + batch + " 库区编码:" + stockCode + " 库位编码:" + areaCode);
            MaterialStockDO materialStock = materialStockMapper.selectOne(MaterialStockDO::getItemCode, itemCode, MaterialStockDO::getBatchCode, batch, MaterialStockDO::getLocationCode, stockCode, MaterialStockDO::getAreaCode, areaCode , MaterialStockDO::getUnitOfMeasure, material.get("UNIT_OF_MEASURE"));
            if (materialStock == null) {
                // 新增库存信息
                MaterialStockDO materialStockDO = new MaterialStockDO();
                materialStockDO.setItemCode(itemCode); // 物料编码
                if (item != null) {
                    materialStockDO.setItemId(item.getId()); // 物料ID
                    materialStockDO.setItemTypeId(item.getItemTypeId()); // 物料类型ID
                    materialStockDO.setItemName(item.getItemName()); // 物料名称
                    materialStockDO.setSpecification(item.getSpecification()); // 规格
                }
                materialStockDO.setUnitOfMeasure((String) material.get("UNIT_OF_MEASURE")); // 单位
                materialStockDO.setBatchCode(batch); // 批次号
                materialStockDO.setQuantityOnhand((BigDecimal) material.get("QUANTITY_ONHAND")); // 数量
                // 库区信息
                materialStockDO.setLocationCode(stockCode); // 库区编码
                if (location != null) {
                    materialStockDO.setWarehouseId(location.getWarehouseId()); // 仓库ID
                    // 基于仓库IdH获取仓库信息
                    WarehouseDO warehouse = warehouseService.getWarehouse(location.getWarehouseId());
                    materialStockDO.setWarehouseCode(warehouse.getWarehouseCode()); // 仓库编码
                    materialStockDO.setWarehouseName(warehouse.getWarehouseName()); // 仓库名称
                    materialStockDO.setLocationId(location.getId()); // 库区ID
                    materialStockDO.setLocationName(location.getLocationName()); // 库区名称
                }
                // 库位信息
                materialStockDO.setAreaCode(areaCode); // 库位编码
                if (area != null) {
                    materialStockDO.setAreaId(area.getId()); // 库位ID
                    materialStockDO.setAreaName(area.getAreaName()); // 库位名称
                }
                addList.add(materialStockDO);
            } else {
                // 更新库存信息
                materialStock.setQuantityOnhand((BigDecimal) material.get("QUANTITY_ONHAND")); // 数量
                if (item != null) {
                    materialStock.setItemId(item.getId()); // 物料ID
                    materialStock.setItemTypeId(item.getItemTypeId()); // 物料类型ID
                    materialStock.setItemName(item.getItemName()); // 物料名称
                    materialStock.setSpecification(item.getSpecification()); // 规格
                }
                materialStock.setUnitOfMeasure((String) material.get("UNIT_OF_MEASURE")); // 单位
                // 库区信息
                materialStock.setLocationCode(stockCode); // 库区编码
                if (location != null) {
                    // 基于仓库IdH获取仓库信息
                    WarehouseDO warehouse = warehouseService.getWarehouse(location.getWarehouseId());
                    materialStock.setWarehouseCode(warehouse.getWarehouseCode()); // 仓库编码
                    materialStock.setWarehouseName(warehouse.getWarehouseName()); // 仓库名称
                    materialStock.setLocationId(location.getId()); // 库区ID
                    materialStock.setLocationName(location.getLocationName()); // 库区名称
                }
                // 库位信息
                materialStock.setAreaCode(areaCode); // 库位编码
                if (area != null) {
                    materialStock.setAreaId(area.getId()); // 库位ID
                    materialStock.setAreaName(area.getAreaName()); // 库位名称
                }
                updateList.add(materialStock);
            }
        }
        if (!addList.isEmpty()) {
            materialStockMapper.insertBatch(addList);
        }
        if (!updateList.isEmpty()) {
            materialStockMapper.updateBatch(updateList);
        }
        System.out.println("库存信息初始化完成");
        return "success";
    }
}
