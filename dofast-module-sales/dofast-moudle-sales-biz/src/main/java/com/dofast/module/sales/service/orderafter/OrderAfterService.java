package com.dofast.module.sales.service.orderafter;

import java.util.*;
import javax.validation.*;
import com.dofast.module.sales.controller.admin.orderafter.vo.*;
import com.dofast.module.sales.dal.dataobject.orderafter.OrderAfterDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 售后流程表单 Service 接口
 *
 * @author a1
 */
public interface OrderAfterService {

    /**
     * 创建售后流程表单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderAfter(@Valid OrderAfterCreateReqVO createReqVO);

    /**
     * 更新售后流程表单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderAfter(@Valid OrderAfterUpdateReqVO updateReqVO);

    /**
     * 删除售后流程表单
     *
     * @param id 编号
     */
    void deleteOrderAfter(Long id);

    /**
     * 获得售后流程表单
     *
     * @param id 编号
     * @return 售后流程表单
     */
    OrderAfterDO getOrderAfter(Long id);

    /**
     * 获得售后流程表单列表
     *
     * @param ids 编号
     * @return 售后流程表单列表
     */
    List<OrderAfterDO> getOrderAfterList(Collection<Long> ids);

    /**
     * 获得售后流程表单分页
     *
     * @param pageReqVO 分页查询
     * @return 售后流程表单分页
     */
    PageResult<OrderAfterDO> getOrderAfterPage(OrderAfterPageReqVO pageReqVO);

    /**
     * 获得售后流程表单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 售后流程表单列表
     */
    List<OrderAfterDO> getOrderAfterList(OrderAfterExportReqVO exportReqVO);

}
