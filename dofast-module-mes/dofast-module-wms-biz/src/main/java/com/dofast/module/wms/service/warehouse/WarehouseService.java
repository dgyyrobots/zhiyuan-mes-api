package com.dofast.module.wms.service.warehouse;

import java.util.*;
import javax.validation.*;

import com.dofast.module.wms.api.WarehosueApi.dto.WarehouseDTO;
import com.dofast.module.wms.controller.admin.warehouse.vo.*;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 仓库 Service 接口
 *
 * @author 惠智造
 */
public interface WarehouseService {
    public WarehouseDO selectWmWarehouseByWarehouseCode(String houseCode);
    public List<WarehouseDO> getTreeList();
    /**
     * 检查仓库编码是否重复
     * @param wmWarehouse
     * @return
     */
    public String checkWarehouseCodeUnique(WarehouseBaseVO wmWarehouse);

    /**
     * 检查仓库名称是否重复
     * @param wmWarehouse
     * @return
     */
    public String checkWarehouseNameUnique(WarehouseBaseVO wmWarehouse);
    /**
     * 创建仓库
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWarehouse(@Valid WarehouseCreateReqVO createReqVO);

    /**
     * 更新仓库
     *
     * @param updateReqVO 更新信息
     */
    void updateWarehouse(@Valid WarehouseUpdateReqVO updateReqVO);

    /**
     * 删除仓库
     *
     * @param id 编号
     */
    void deleteWarehouse(Long id);

    /**
     * 获得仓库
     *
     * @param id 编号
     * @return 仓库
     */
    WarehouseDO getWarehouse(Long id);

    /**
     * 获得仓库列表
     *
     * @param ids 编号
     * @return 仓库列表
     */
    List<WarehouseDO> getWarehouseList(Collection<Long> ids);

    /**
     * 获得仓库分页
     *
     * @param pageReqVO 分页查询
     * @return 仓库分页
     */
    PageResult<WarehouseDO> getWarehousePage(WarehousePageReqVO pageReqVO);

    /**
     * 获得仓库列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 仓库列表
     */
    List<WarehouseDO> getWarehouseList(WarehouseExportReqVO exportReqVO);

}
