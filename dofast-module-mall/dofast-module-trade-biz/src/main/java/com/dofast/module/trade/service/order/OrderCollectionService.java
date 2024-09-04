package com.dofast.module.trade.service.order;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.order.vo.*;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 采集任务 Service 接口
 *
 * @author 惠智造
 */
public interface OrderCollectionService {

    /**
     * 创建采集任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createOrderCollection(@Valid OrderCollectionCreateReqVO createReqVO);

    /**
     * 更新采集任务
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderCollection(@Valid OrderCollectionUpdateReqVO updateReqVO);

    /**
     * 更新采集任务状态
     *
     * @param updateStatusReqVO
     */
    void updateOrderCollectionStatus(@Valid OrderCollectionUpdateStatusReqVO updateStatusReqVO);
    /**
     * 删除采集任务
     *
     * @param id 编号
     */
    void deleteOrderCollection(Integer id);

    /**
     * 获得采集任务
     *
     * @param id 编号
     * @return 采集任务
     */
    OrderCollectionDO getOrderCollection(Integer id);

    /**
     * 获得采集任务列表
     *
     * @param ids 编号
     * @return 采集任务列表
     */
    List<OrderCollectionDO> getOrderCollectionList(Collection<Integer> ids);

    /**
     * 获得采集任务分页
     *
     * @param pageReqVO 分页查询
     * @return 采集任务分页
     */
    PageResult<OrderCollectionDO> getOrderCollectionPage(OrderCollectionPageReqVO pageReqVO);

    /**
     * 获得采集任务列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 采集任务列表
     */
    List<OrderCollectionDO> getOrderCollectionList(OrderCollectionExportReqVO exportReqVO);

}
