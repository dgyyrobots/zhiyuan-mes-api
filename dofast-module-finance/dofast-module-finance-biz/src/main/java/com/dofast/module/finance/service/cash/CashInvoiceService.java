package com.dofast.module.finance.service.cash;

import java.util.*;
import javax.validation.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 发票信息 Service 接口
 *
 * @author 惠智造
 */
public interface CashInvoiceService {

    /**
     * 创建发票信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCashInvoice(@Valid CashInvoiceCreateReqVO createReqVO);

    /**
     * 更新发票信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCashInvoice(@Valid CashInvoiceUpdateReqVO updateReqVO);

    /**
     * 删除发票信息
     *
     * @param id 编号
     */
    void deleteCashInvoice(Long id);

    /**
     * 获得发票信息
     *
     * @param id 编号
     * @return 发票信息
     */
    CashInvoiceDO getCashInvoice(Long id);

    /**
     * 获得发票信息列表
     *
     * @param ids 编号
     * @return 发票信息列表
     */
    List<CashInvoiceDO> getCashInvoiceList(Collection<Long> ids);

    /**
     * 获得发票信息分页
     *
     * @param pageReqVO 分页查询
     * @return 发票信息分页
     */
    PageResult<CashInvoiceDO> getCashInvoicePage(CashInvoicePageReqVO pageReqVO);

    /**
     * 获得发票信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 发票信息列表
     */
    List<CashInvoiceDO> getCashInvoiceList(CashInvoiceExportReqVO exportReqVO);

}
