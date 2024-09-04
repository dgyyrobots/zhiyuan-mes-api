package com.dofast.module.pro.service.transorder;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.transorder.vo.*;
import com.dofast.module.pro.dal.dataobject.transorder.TransOrderDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 流转单 Service 接口
 *
 * @author 芋道源码
 */
public interface TransOrderService {

    /**
     * 创建流转单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTransOrder(@Valid TransOrderCreateReqVO createReqVO);

    /**
     * 更新流转单
     *
     * @param updateReqVO 更新信息
     */
    void updateTransOrder(@Valid TransOrderUpdateReqVO updateReqVO);

    /**
     * 删除流转单
     *
     * @param id 编号
     */
    void deleteTransOrder(Long id);

    /**
     * 获得流转单
     *
     * @param id 编号
     * @return 流转单
     */
    TransOrderDO getTransOrder(Long id);

    /**
     * 获得流转单列表
     *
     * @param ids 编号
     * @return 流转单列表
     */
    List<TransOrderDO> getTransOrderList(Collection<Long> ids);

    /**
     * 获得流转单分页
     *
     * @param pageReqVO 分页查询
     * @return 流转单分页
     */
    PageResult<TransOrderDO> getTransOrderPage(TransOrderPageReqVO pageReqVO);

    /**
     * 获得流转单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 流转单列表
     */
    List<TransOrderDO> getTransOrderList(TransOrderExportReqVO exportReqVO);

}
