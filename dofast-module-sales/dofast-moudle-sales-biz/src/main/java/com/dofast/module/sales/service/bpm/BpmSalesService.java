package com.dofast.module.sales.service.bpm;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmCreateReqVO;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmPageReqVO;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmUpdateReqVO;
import com.dofast.module.sales.controller.admin.orderafter.vo.OrderAfterUpdateReqVO;
import com.dofast.module.sales.dal.dataobject.orderafter.OrderAfterDO;

import javax.validation.Valid;

/**
 * 售后工作流
 */
public interface BpmSalesService {

    /**
     * 创建售后信息
     *
     * @param userId 用户编号
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSales(Long userId, @Valid OrderAfterBpmCreateReqVO createReqVO);

    /**
     * 更新售后的状态
     *
     * @param id 编号
     * @param result 结果
     */
    void updateSaleResult(Long id, Integer result);


    /**
     * 获得售后信息
     *
     * @param id 编号
     * @return 请假申请
     */
    OrderAfterDO getSales(Long id);

    /**
     * 获得售后信息分页
     *
     * @param userId 用户编号
     * @param pageReqVO 分页查询
     * @return 请假申请分页
     */
    PageResult<OrderAfterDO> getSalesPage(Long userId, OrderAfterBpmPageReqVO pageReqVO);
}
