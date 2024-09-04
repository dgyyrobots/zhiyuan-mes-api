package com.dofast.module.finance.service.cash;

import java.util.*;
import javax.validation.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashFundDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 财务退款 Service 接口
 *
 * @author 惠智造
 */
public interface CashFundService {

    /**
     * 创建财务退款
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCashFund(@Valid CashFundCreateReqVO createReqVO);

    /**
     * 更新财务退款
     *
     * @param updateReqVO 更新信息
     */
    void updateCashFund(@Valid CashFundUpdateReqVO updateReqVO);

    /**
     * 删除财务退款
     *
     * @param id 编号
     */
    void deleteCashFund(Long id);

    /**
     * 获得财务退款
     *
     * @param id 编号
     * @return 财务退款
     */
    CashFundDO getCashFund(Long id);

    /**
     * 获得财务退款列表
     *
     * @param ids 编号
     * @return 财务退款列表
     */
    List<CashFundDO> getCashFundList(Collection<Long> ids);

    /**
     * 获得财务退款分页
     *
     * @param pageReqVO 分页查询
     * @return 财务退款分页
     */
    PageResult<CashFundDO> getCashFundPage(CashFundPageReqVO pageReqVO);

    /**
     * 获得财务退款列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 财务退款列表
     */
    List<CashFundDO> getCashFundList(CashFundExportReqVO exportReqVO);

}
