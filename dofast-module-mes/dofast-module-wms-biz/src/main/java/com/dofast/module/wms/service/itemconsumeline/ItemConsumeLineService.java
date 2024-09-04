package com.dofast.module.wms.service.itemconsumeline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.itemconsumeline.vo.*;
import com.dofast.module.wms.dal.dataobject.itemconsumeline.ItemConsumeLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 物料消耗记录行 Service 接口
 *
 * @author 惠智造
 */
public interface ItemConsumeLineService {

    /**
     * 创建物料消耗记录行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createItemConsumeLine(@Valid ItemConsumeLineCreateReqVO createReqVO);

    /**
     * 更新物料消耗记录行
     *
     * @param updateReqVO 更新信息
     */
    void updateItemConsumeLine(@Valid ItemConsumeLineUpdateReqVO updateReqVO);

    /**
     * 删除物料消耗记录行
     *
     * @param id 编号
     */
    void deleteItemConsumeLine(Long id);

    /**
     * 获得物料消耗记录行
     *
     * @param id 编号
     * @return 物料消耗记录行
     */
    ItemConsumeLineDO getItemConsumeLine(Long id);

    /**
     * 获得物料消耗记录行列表
     *
     * @param ids 编号
     * @return 物料消耗记录行列表
     */
    List<ItemConsumeLineDO> getItemConsumeLineList(Collection<Long> ids);

    /**
     * 获得物料消耗记录行分页
     *
     * @param pageReqVO 分页查询
     * @return 物料消耗记录行分页
     */
    PageResult<ItemConsumeLineDO> getItemConsumeLinePage(ItemConsumeLinePageReqVO pageReqVO);

    /**
     * 获得物料消耗记录行列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 物料消耗记录行列表
     */
    List<ItemConsumeLineDO> getItemConsumeLineList(ItemConsumeLineExportReqVO exportReqVO);

}
