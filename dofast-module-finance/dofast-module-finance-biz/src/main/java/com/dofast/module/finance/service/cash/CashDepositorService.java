package com.dofast.module.finance.service.cash;

import java.util.*;
import javax.validation.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashDepositorDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 资金账号 Service 接口
 *
 * @author 惠智造
 */
public interface CashDepositorService {

    /**
     * 创建资金账号
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCashDepositor(@Valid CashDepositorCreateReqVO createReqVO);

    /**
     * 更新资金账号
     *
     * @param updateReqVO 更新信息
     */
    void updateCashDepositor(@Valid CashDepositorUpdateReqVO updateReqVO);

    /**
     * 删除资金账号
     *
     * @param id 编号
     */
    void deleteCashDepositor(Long id);

    /**
     * 获得资金账号
     *
     * @param id 编号
     * @return 资金账号
     */
    CashDepositorDO getCashDepositor(Long id);

    /**
     * 获得资金账号列表
     *
     * @param ids 编号
     * @return 资金账号列表
     */
    List<CashDepositorDO> getCashDepositorList(Collection<Long> ids);

    /**
     * 获得资金账号分页
     *
     * @param pageReqVO 分页查询
     * @return 资金账号分页
     */
    PageResult<CashDepositorDO> getCashDepositorPage(CashDepositorPageReqVO pageReqVO);

    /**
     * 获得资金账号列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 资金账号列表
     */
    List<CashDepositorDO> getCashDepositorList(CashDepositorExportReqVO exportReqVO);

}
