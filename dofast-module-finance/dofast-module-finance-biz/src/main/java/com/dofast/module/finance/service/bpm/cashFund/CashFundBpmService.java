package com.dofast.module.finance.service.bpm.cashFund;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.finance.controller.admin.bpm.cashFund.vo.CashFundBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashFund.vo.CashFundBpmPageReqVO;
import com.dofast.module.finance.dal.dataobject.cash.CashFundDO;

import javax.validation.Valid;

public interface CashFundBpmService {

    /**
     * 创建财务退款信息
     *
     * @param userId 用户编号
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCashFund(Long userId, @Valid CashFundBpmCreateReqVO createReqVO);

    /**
     * 更新财务退款的状态
     *
     * @param id 编号
     * @param result 结果
     */
    void updateCashFundResult(Long id, Integer result);

    /**
     * 获得财务退款信息
     *
     * @param id 编号
     * @return 请假申请
     */
    CashFundDO getCashFund(Long id);

    /**
     * 获得财务退款信息分页
     *
     * @param userId 用户编号
     * @param pageReqVO 分页查询
     */
    PageResult<CashFundDO> getCashFundPage(Long userId, CashFundBpmPageReqVO pageReqVO);
}
