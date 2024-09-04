package com.dofast.module.finance.service.bpm.invoice;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.finance.controller.admin.bpm.cashInvoice.vo.CashInvoiceBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashInvoice.vo.CashInvoiceBpmPageReqVO;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDO;

import javax.validation.Valid;

public interface CashInvoiceBpmService {

    /**
     * 创建财务发票信息
     *
     * @param userId 用户编号
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCashInvoice(Long userId, @Valid CashInvoiceBpmCreateReqVO createReqVO);

    /**
     * 更新财务发票信息的状态
     *
     * @param id 编号
     * @param result 结果
     */
    void updateCashInvoiceResult(Long id, Integer result);

    /**
     * 获得财务发票信息
     *
     * @param id 编号
     * @return 请假申请
     */
    CashInvoiceDO getCashInvoice(Long id);

    /**
     * 获得财务发票信息分页
     *
     * @param userId 用户编号
     * @param pageReqVO 分页查询
     */
    PageResult<CashInvoiceDO> getCashInvoicePage(Long userId, CashInvoiceBpmPageReqVO pageReqVO);
}
