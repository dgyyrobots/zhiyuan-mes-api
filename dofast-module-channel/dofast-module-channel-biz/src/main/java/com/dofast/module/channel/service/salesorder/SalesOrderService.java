package com.dofast.module.channel.service.salesorder;

import java.util.*;
import javax.validation.*;
import com.dofast.module.channel.controller.admin.salesorder.vo.*;
import com.dofast.module.channel.dal.dataobject.salesorder.SalesOrderDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 销售订单 Service 接口
 *
 * @author 惠智造
 */
public interface SalesOrderService {

    /**
     * 创建销售订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSalesOrder(@Valid SalesOrderCreateReqVO createReqVO);

    /**
     * 更新销售订单
     *
     * @param updateReqVO 更新信息
     */
    void updateSalesOrder(@Valid SalesOrderUpdateReqVO updateReqVO);

    /**
     * 删除销售订单
     *
     * @param id 编号
     */
    void deleteSalesOrder(Long id);

    /**
     * 获得销售订单
     *
     * @param id 编号
     * @return 销售订单
     */
    SalesOrderDO getSalesOrder(Long id);

    /**
     * 获得销售订单列表
     *
     * @param ids 编号
     * @return 销售订单列表
     */
    List<SalesOrderDO> getSalesOrderList(Collection<Long> ids);

    /**
     * 获得销售订单分页
     *
     * @param pageReqVO 分页查询
     * @return 销售订单分页
     */
    PageResult<SalesOrderDO> getSalesOrderPage(SalesOrderPageReqVO pageReqVO);

    /**
     * 获得销售订单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 销售订单列表
     */
    List<SalesOrderDO> getSalesOrderList(SalesOrderExportReqVO exportReqVO);

}
