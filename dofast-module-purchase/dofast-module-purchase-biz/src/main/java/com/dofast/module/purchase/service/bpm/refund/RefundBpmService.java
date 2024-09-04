package com.dofast.module.purchase.service.bpm.refund;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.purchase.controller.admin.bpm.refund.vo.RefundBpmCreateReqVO;
import com.dofast.module.purchase.controller.admin.bpm.refund.vo.RefundBpmPageReqVO;

import javax.validation.Valid;

public interface RefundBpmService {

    /**
     * 创建采购退货信息
     *
     * @param userId 用户编号
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRefund(Long userId, @Valid RefundBpmCreateReqVO createReqVO);

    /**
     * 更新采购退货的状态
     *
     * @param id 编号
     * @param result 结果
     */
    void updateRefundResult(Long id, Integer result);

    /**
     * 获得采购退货信息
     *
     * @param id 编号
     * @return 请假申请
     */
    com.dofast.module.purchase.dal.dataobject.refund.RefundDO getRefundDO(Long id);

    /**
     * 获得采购退货信息分页
     *
     * @param userId 用户编号
     * @param pageReqVO 分页查询
     * @return 请假申请分页
     */
    PageResult<com.dofast.module.purchase.dal.dataobject.refund.RefundDO> getRefundDOPage(Long userId, RefundBpmPageReqVO pageReqVO);
}
