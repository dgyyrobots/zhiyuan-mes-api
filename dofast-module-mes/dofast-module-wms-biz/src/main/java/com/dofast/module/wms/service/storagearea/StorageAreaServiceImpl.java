package com.dofast.module.wms.service.storagearea;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.storagearea.vo.*;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.storagearea.StorageAreaConvert;
import com.dofast.module.wms.dal.mysql.storagearea.StorageAreaMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 库位 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class StorageAreaServiceImpl implements StorageAreaService {

    @Resource
    private StorageAreaMapper storageAreaMapper;

    @Override
    public StorageAreaDO selectWmStorageAreaByAreaCode(String areaCode) {
        return storageAreaMapper.selectWmStorageAreaByAreaCode(areaCode);
    }

    @Override
    public int deleteByWarehouseId(Long warehouseId) {
        return storageAreaMapper.deleteByWarehouseId(warehouseId);
    }

    @Override
    public Long createStorageArea(StorageAreaCreateReqVO createReqVO) {
        // 插入
        StorageAreaDO storageArea = StorageAreaConvert.INSTANCE.convert(createReqVO);
        storageAreaMapper.insert(storageArea);
        // 返回
        return storageArea.getId();
    }

    @Override
    public void updateStorageArea(StorageAreaUpdateReqVO updateReqVO) {
        // 校验存在
        validateStorageAreaExists(updateReqVO.getId());
        // 更新
        StorageAreaDO updateObj = StorageAreaConvert.INSTANCE.convert(updateReqVO);
        storageAreaMapper.updateById(updateObj);
    }

    @Override
    public void deleteStorageArea(Long id) {
        // 校验存在
        validateStorageAreaExists(id);
        // 删除
        storageAreaMapper.deleteById(id);
    }

    private void validateStorageAreaExists(Long id) {
        if (storageAreaMapper.selectById(id) == null) {
            throw exception(STORAGE_AREA_NOT_EXISTS);
        }
    }

    @Override
    public StorageAreaDO getStorageArea(Long id) {
        return storageAreaMapper.selectById(id);
    }

    @Override
    public List<StorageAreaDO> getStorageAreaList(Collection<Long> ids) {
        return storageAreaMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<StorageAreaDO> getStorageAreaPage(StorageAreaPageReqVO pageReqVO) {
        return storageAreaMapper.selectPage(pageReqVO);
    }

    @Override
    public List<StorageAreaDO> getStorageAreaList(StorageAreaExportReqVO exportReqVO) {
        return storageAreaMapper.selectList(exportReqVO);
    }

    @Override
    public int deleteByLocationId(Long locationId) {
        return storageAreaMapper.deleteByLocationId(locationId);
    }

}
