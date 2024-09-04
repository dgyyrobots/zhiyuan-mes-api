package com.dofast.module.finance.service.bpm.cashTrade;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.finance.controller.admin.bpm.cashTrade.vo.CashTradeBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashTrade.vo.CashTradeBpmPageReqVO;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeDO;

import javax.validation.Valid;

public interface CashTradeBpmService {

    /**
     * 创建财务流水信息
     *
     * @param userId 用户编号
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCashTrade(Long userId, @Valid CashTradeBpmCreateReqVO createReqVO);

    /**
     * 更新财务流水的状态
     *
     * @param id 编号
     * @param result 结果
     */
    void updateCashTradeResult(Long id, Integer result);

    /**
     * 获得财务流水信息
     *
     * @param id 编号
     * @return 请假申请
     */
    CashTradeDO getCashTrade(Long id);

    /**
     * 获得财务流水信息分页
     *
     * @param userId 用户编号
     * @param pageReqVO 分页查询
     * @return 请假申请分页
     */
    PageResult<CashTradeDO> getCashTradePage(Long userId, CashTradeBpmPageReqVO pageReqVO);

}
