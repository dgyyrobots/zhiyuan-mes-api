package com.dofast.module.purchase.service.retreatOrder;

import java.util.*;
import javax.validation.*;

import com.dofast.module.purchase.controller.admin.retreatOrder.vo.RetreatOrderCreateReqVO;
import com.dofast.module.purchase.controller.admin.retreatOrder.vo.RetreatOrderExportReqVO;
import com.dofast.module.purchase.controller.admin.retreatOrder.vo.RetreatOrderPageReqVO;
import com.dofast.module.purchase.controller.admin.retreatOrder.vo.RetreatOrderUpdateReqVO;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.purchase.dal.dataobject.retreatOrder.RetreatOrderDO;

/**
 * ERP仓退单 Service 接口
 *
 * @author 惠智造
 */
public interface RetreatOrderService {

    /**
     * 创建ERP仓退单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrder(@Valid RetreatOrderCreateReqVO createReqVO);

    /**
     * 更新ERP仓退单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrder(@Valid RetreatOrderUpdateReqVO updateReqVO);

    /**
     * 删除ERP仓退单
     *
     * @param id 编号
     */
    void deleteOrder(Long id);

    /**
     * 获得ERP仓退单
     *
     * @param id 编号
     * @return ERP仓退单
     */
    RetreatOrderDO getOrder(Long id);

    RetreatOrderDO getOrder(String retreatCode);


    /**
     * 获得ERP仓退单列表
     *
     * @param ids 编号
     * @return ERP仓退单列表
     */
    List<RetreatOrderDO> getOrderList(Collection<Long> ids);

    /**
     * 获得ERP仓退单分页
     *
     * @param pageReqVO 分页查询
     * @return ERP仓退单分页
     */
    PageResult<RetreatOrderDO> getOrderPage(RetreatOrderPageReqVO pageReqVO);

    /**
     * 获得ERP仓退单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return ERP仓退单列表
     */
    List<RetreatOrderDO> getOrderList(RetreatOrderExportReqVO exportReqVO);

    void updateBatch(List<RetreatOrderDO> retreatOrderDOS);

    void insertBatch(List<RetreatOrderDO> retreatOrderDOS);

}
