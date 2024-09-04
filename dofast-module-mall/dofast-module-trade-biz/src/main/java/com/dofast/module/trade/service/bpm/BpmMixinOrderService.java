package com.dofast.module.trade.service.bpm;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.bpm.mixin.vo.MixinOrderBpmCreateReqVO;
import com.dofast.module.trade.controller.admin.bpm.mixin.vo.MixinOrderBpmPageReqVO;
import com.dofast.module.trade.dal.dataobject.mixin.MixinOrderDO;

import javax.validation.Valid;

public interface BpmMixinOrderService {

    /**
     * 创建销售订单信息
     *
     * @param userId 用户编号
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMixinOrder(Long userId, @Valid MixinOrderBpmCreateReqVO createReqVO);

    /**
     * 更新销售订单的状态
     *
     * @param id 编号
     * @param result 结果
     */
    void updateMixinOrderResult(Long id, Integer result);

    /**
     * 获得销售订单信息
     *
     * @param id 编号
     * @return 请假申请
     */
    MixinOrderDO getMixinOrder(Long id);

    /**
     * 获得销售订单信息分页
     *
     * @param userId 用户编号
     * @param pageReqVO 分页查询
     * @return 请假申请分页
     */
    PageResult<MixinOrderDO> getMixinOrderPage(Long userId, MixinOrderBpmPageReqVO pageReqVO);
}
