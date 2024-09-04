package com.dofast.module.finance.service.cash;

import java.util.*;
import javax.validation.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashBalanceDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 现金余额 Service 接口
 *
 * @author 惠智造
 */
public interface CashBalanceService {

    /**
     * 创建现金余额
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCashBalance(@Valid CashBalanceCreateReqVO createReqVO);

    /**
     * 更新现金余额
     *
     * @param updateReqVO 更新信息
     */
    void updateCashBalance(@Valid CashBalanceUpdateReqVO updateReqVO);

    /**
     * 删除现金余额
     *
     * @param id 编号
     */
    void deleteCashBalance(Long id);

    /**
     * 获得现金余额
     *
     * @param id 编号
     * @return 现金余额
     */
    CashBalanceDO getCashBalance(Long id);

    /**
     * 获得现金余额列表
     *
     * @param ids 编号
     * @return 现金余额列表
     */
    List<CashBalanceDO> getCashBalanceList(Collection<Long> ids);

    /**
     * 获得现金余额分页
     *
     * @param pageReqVO 分页查询
     * @return 现金余额分页
     */
    PageResult<CashBalanceDO> getCashBalancePage(CashBalancePageReqVO pageReqVO);

    /**
     * 获得现金余额列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 现金余额列表
     */
    List<CashBalanceDO> getCashBalanceList(CashBalanceExportReqVO exportReqVO);

}
