package com.dofast.module.trade.service.mixin;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.mixin.vo.*;
import com.dofast.module.trade.dal.dataobject.mixin.MixinOrderDO;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * 销售订单 Service 接口
 *
 * @author 惠智造
 */
public interface MixinOrderService {

    /**
     * 创建销售订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMixinOrder(@Valid MixinOrderCreateReqVO createReqVO);

    /**
     * 更新销售订单
     *
     * @param updateReqVO 更新信息
     */
    void updateMixinOrder(@Valid MixinOrderUpdateReqVO updateReqVO);

    /**
     * 删除销售订单
     *
     * @param id 编号
     */
    void deleteMixinOrder(Long id);

    /**
     * 获得销售订单
     *
     * @param id 编号
     * @return 销售订单
     */
    MixinOrderDO getMixinOrder(Long id);

    /**
     * 获得销售订单
     *
     * @param id 编号
     * @return 销售订单
     */
    MixinOrderDetailRespVO getMixinOrderDetail(Long id);

    /**
     * 获得销售订单列表
     *
     * @param ids 编号
     * @return 销售订单列表
     */
    List<MixinOrderDO> getMixinOrderList(Collection<Long> ids);

    /**
     * 获得销售订单分页
     *
     * @param pageReqVO 分页查询
     * @return 销售订单分页
     */
    PageResult<MixinOrderDO> getMixinOrderPage(MixinOrderPageReqVO pageReqVO);

    PageResult<MixinOrderDO> getMixinOrderPageDesc(MixinOrderPageReqVO pageReqVO);

    /**
     * 获得销售订单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 销售订单列表
     */
    List<MixinOrderDO> getMixinOrderList(MixinOrderExportReqVO exportReqVO);

    /**
     * 获取店铺订单日销售金额
     *
     * @return
     */
    BigDecimal getDayOrderMoney();

    MixinOrderDO getByNo(String no);

    Boolean updatebyIsPrint(Long id);

}
