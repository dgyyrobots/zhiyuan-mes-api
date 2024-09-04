package com.dofast.module.wms.service.warehouse;

import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.api.WarehosueApi.dto.WarehouseDTO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import com.dofast.module.wms.controller.admin.warehouse.vo.*;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.warehouse.WarehouseConvert;
import com.dofast.module.wms.dal.mysql.warehouse.WarehouseMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 仓库 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class WarehouseServiceImpl implements WarehouseService {

    @Resource
    private WarehouseMapper warehouseMapper;

    @Override
    public WarehouseDO selectWmWarehouseByWarehouseCode(String houseCode) {
        return warehouseMapper.selectWmWarehouseByWarehouseCode(houseCode);
    }

    @Override
    public List<WarehouseDO> getTreeList() {
        List<WarehouseDO> list = warehouseMapper.getTreeList();
        if (list == null || list.isEmpty()) {
            return list;
        }
        return list.stream().filter((warehouseDO) -> {
            boolean isPass = warehouseDO != null && warehouseDO.getId() != null;
            List<StorageLocationDO> locList = warehouseDO.getChildren();
            if (locList == null) {
                warehouseDO.setChildren(new ArrayList<>());
            } else {
                warehouseDO.setChildren(locList.stream().filter((locationDO) -> {
                    boolean isLocPass = locationDO != null && locationDO.getId() != null;
                    List<StorageAreaDO> areaList = locationDO.getChildren();
                    if (areaList == null) {
                        locationDO.setChildren(new ArrayList<>());
                    } else {
                        locationDO.setChildren(areaList.stream().filter((areaDO) -> {
                            boolean isAreaPass = areaDO != null && areaDO.getId() != null;
                            return isAreaPass;
                        }).collect(Collectors.toList()));
                    }
                    return isLocPass;
                }).collect(Collectors.toList()));
            }
            return isPass;
        }).collect(Collectors.toList());
    }

    @Override
    public String checkWarehouseCodeUnique(WarehouseBaseVO wmWarehouse) {
        WarehouseDO warehouse = warehouseMapper.checkWarehouseCodeUnique(wmWarehouse);
        Long warehouseId = wmWarehouse.getId() == null ? -1L : wmWarehouse.getId();
        if (!StrUtils.isNull(warehouse) && warehouse.getId().longValue() != warehouseId.longValue()) {
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public String checkWarehouseNameUnique(WarehouseBaseVO wmWarehouse) {
        WarehouseDO warehouse = warehouseMapper.checkWarehouseNameUnique(wmWarehouse);
        Long warehouseId = wmWarehouse.getId() == null ? -1L : wmWarehouse.getId();
        if (!StrUtils.isNull(warehouse) && warehouse.getId().longValue() != warehouseId.longValue()) {
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createWarehouse(WarehouseCreateReqVO createReqVO) {
        // 插入
        WarehouseDO warehouse = WarehouseConvert.INSTANCE.convert(createReqVO);
        warehouseMapper.insert(warehouse);
        // 返回
        return warehouse.getId();
    }

    @Override
    public void updateWarehouse(WarehouseUpdateReqVO updateReqVO) {
        // 校验存在
        validateWarehouseExists(updateReqVO.getId());
        // 更新
        WarehouseDO updateObj = WarehouseConvert.INSTANCE.convert(updateReqVO);
        warehouseMapper.updateById(updateObj);
    }

    @Override
    public void deleteWarehouse(Long id) {
        // 校验存在
        validateWarehouseExists(id);
        // 删除
        warehouseMapper.deleteById(id);
    }

    private void validateWarehouseExists(Long id) {
        if (warehouseMapper.selectById(id) == null) {
            throw exception(WAREHOUSE_NOT_EXISTS);
        }
    }

    @Override
    public WarehouseDO getWarehouse(Long id) {
        return warehouseMapper.selectById(id);
    }

    @Override
    public List<WarehouseDO> getWarehouseList(Collection<Long> ids) {
        return warehouseMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<WarehouseDO> getWarehousePage(WarehousePageReqVO pageReqVO) {
        return warehouseMapper.selectPage(pageReqVO);
    }

    @Override
    public List<WarehouseDO> getWarehouseList(WarehouseExportReqVO exportReqVO) {
        return warehouseMapper.selectList(exportReqVO);
    }

}
