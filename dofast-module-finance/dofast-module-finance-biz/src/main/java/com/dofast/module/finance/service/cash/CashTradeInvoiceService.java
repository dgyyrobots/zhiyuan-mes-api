package com.dofast.module.finance.service.cash;

import java.util.*;
import javax.validation.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeInvoiceDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 业务发票关联 Service 接口
 *
 * @author 惠智造
 */
public interface CashTradeInvoiceService {

    /**
     * 创建业务发票关联
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCashTradeInvoice(@Valid CashTradeInvoiceCreateReqVO createReqVO);

    /**
     * 更新业务发票关联
     *
     * @param updateReqVO 更新信息
     */
    void updateCashTradeInvoice(@Valid CashTradeInvoiceUpdateReqVO updateReqVO);

    /**
     * 删除业务发票关联
     *
     * @param id 编号
     */
    void deleteCashTradeInvoice(Long id);

    /**
     * 获得业务发票关联
     *
     * @param id 编号
     * @return 业务发票关联
     */
    CashTradeInvoiceDO getCashTradeInvoice(Long id);

    /**
     * 获得业务发票关联列表
     *
     * @param ids 编号
     * @return 业务发票关联列表
     */
    List<CashTradeInvoiceDO> getCashTradeInvoiceList(Collection<Long> ids);

    /**
     * 获得业务发票关联分页
     *
     * @param pageReqVO 分页查询
     * @return 业务发票关联分页
     */
    PageResult<CashTradeInvoiceDO> getCashTradeInvoicePage(CashTradeInvoicePageReqVO pageReqVO);

    /**
     * 获得业务发票关联列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 业务发票关联列表
     */
    List<CashTradeInvoiceDO> getCashTradeInvoiceList(CashTradeInvoiceExportReqVO exportReqVO);

}
