package com.dofast.module.wms.service.storagearea;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.storagearea.vo.*;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 库位 Service 接口
 *
 * @author 芋道源码
 */
public interface StorageAreaService {
    /**
     * 根据库位编码查询库位
     * @param areaCode
     * @return
     */
    public StorageAreaDO selectWmStorageAreaByAreaCode(String areaCode);
    /**
     * 根据仓库删除对应的库区
     * @param warehouseId
     * @return
     */
    public int deleteByWarehouseId(Long warehouseId);

    /**
     * 创建库位
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStorageArea(@Valid StorageAreaCreateReqVO createReqVO);

    /**
     * 更新库位
     *
     * @param updateReqVO 更新信息
     */
    void updateStorageArea(@Valid StorageAreaUpdateReqVO updateReqVO);

    /**
     * 删除库位
     *
     * @param id 编号
     */
    void deleteStorageArea(Long id);

    /**
     * 获得库位
     *
     * @param id 编号
     * @return 库位
     */
    StorageAreaDO getStorageArea(Long id);

    StorageAreaDO getStorageArea(String areaCode);


    /**
     * 获得库位列表
     *
     * @param ids 编号
     * @return 库位列表
     */
    List<StorageAreaDO> getStorageAreaList(Collection<Long> ids);

    /**
     * 获得库位分页
     *
     * @param pageReqVO 分页查询
     * @return 库位分页
     */
    PageResult<StorageAreaDO> getStorageAreaPage(StorageAreaPageReqVO pageReqVO);

    /**
     * 获得库位列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 库位列表
     */
    List<StorageAreaDO> getStorageAreaList(StorageAreaExportReqVO exportReqVO);

    /**
     * 根据库区删除对应的库位
     * @param locationId
     * @return
     */
    public int deleteByLocationId(Long locationId);

    /**
     * 根据库区查询所有库位信息
     * @param locationId
     * @return
     */
    List<StorageAreaDO> getStorageAreaByLocationId(Long locationId);

}
