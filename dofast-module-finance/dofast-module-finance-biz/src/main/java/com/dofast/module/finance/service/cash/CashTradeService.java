package com.dofast.module.finance.service.cash;

import java.math.BigDecimal;
import java.util.*;
import javax.validation.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 财务流水 Service 接口
 *
 * @author 惠智造
 */
public interface CashTradeService {

    /**
     * 创建财务流水
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCashTrade(@Valid CashTradeCreateReqVO createReqVO);

    /**
     * 更新财务流水
     *
     * @param updateReqVO 更新信息
     */
    void updateCashTrade(@Valid CashTradeUpdateReqVO updateReqVO);

    /**
     * 删除财务流水
     *
     * @param id 编号
     */
    void deleteCashTrade(Long id);

    /**
     * 获得财务流水
     *
     * @param id 编号
     * @return 财务流水
     */
    CashTradeDO getCashTrade(Long id);

    /**
     * 获得财务流水列表
     *
     * @param ids 编号
     * @return 财务流水列表
     */
    List<CashTradeDO> getCashTradeList(Collection<Long> ids);

    /**
     * 获得财务流水分页
     *
     * @param pageReqVO 分页查询
     * @return 财务流水分页
     */
    PageResult<CashTradeDO> getCashTradePage(CashTradePageReqVO pageReqVO);

    /**
     * 获得财务流水列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 财务流水列表
     */
    List<CashTradeDO> getCashTradeList(CashTradeExportReqVO exportReqVO);

    /**
     * 获取店铺订单日销售金额
     *
     * @return
     */
    BigDecimal getDayOrderMoney(Integer type);

}
