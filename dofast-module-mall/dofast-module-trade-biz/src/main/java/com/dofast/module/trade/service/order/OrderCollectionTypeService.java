package com.dofast.module.trade.service.order;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.order.vo.OrderCollectionTypeCreateReqVO;
import com.dofast.module.trade.controller.admin.order.vo.OrderCollectionTypeExportReqVO;
import com.dofast.module.trade.controller.admin.order.vo.OrderCollectionTypePageReqVO;
import com.dofast.module.trade.controller.admin.order.vo.OrderCollectionTypeUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionTypeDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 采集任务类型 Service 接口
 *
 * @author 惠智造
 */
public interface OrderCollectionTypeService {

    /**
     * 创建采集任务类型
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderCollectionType(@Valid OrderCollectionTypeCreateReqVO createReqVO);

    /**
     * 更新采集任务类型
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderCollectionType(@Valid OrderCollectionTypeUpdateReqVO updateReqVO);

    /**
     * 删除采集任务类型
     *
     * @param id 编号
     */
    void deleteOrderCollectionType(Long id);

    /**
     * 获得采集任务类型
     *
     * @param id 编号
     * @return 采集任务类型
     */
    OrderCollectionTypeDO getOrderCollectionType(Long id);

    /**
     * 获得采集任务类型列表
     *
     * @param ids 编号
     * @return 采集任务类型列表
     */
    List<OrderCollectionTypeDO> getOrderCollectionTypeList(Collection<Long> ids);

    /**
     * 获得采集任务类型分页
     *
     * @param pageReqVO 分页查询
     * @return 采集任务类型分页
     */
    PageResult<OrderCollectionTypeDO> getOrderCollectionTypePage(OrderCollectionTypePageReqVO pageReqVO);

    /**
     * 获得采集任务类型列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 采集任务类型列表
     */
    List<OrderCollectionTypeDO> getOrderCollectionTypeList(OrderCollectionTypeExportReqVO exportReqVO);

}
