package com.dofast.module.finance.service.bpm.cashInvoiceDetail;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail.vo.CashInvoiceDetailBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail.vo.CashInvoiceDetailBpmPageReqVO;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDetailDO;

import javax.validation.Valid;

public interface CashInvoiceDetailBpmService {

    /**
     * 创建财务发票明细信息
     *
     * @param userId 用户编号
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCashInvoiceDetail(Long userId, @Valid CashInvoiceDetailBpmCreateReqVO createReqVO);

    /**
     * 更新财务发票明细的状态
     *
     * @param id 编号
     * @param result 结果
     */
    void updateCashInvoiceDetailResult(Long id, Integer result);

    /**
     * 获得财务发票明细信息
     *
     * @param id 编号
     */
    CashInvoiceDetailDO getCashInvoiceDetail(Long id);

    /**
     * 获得财务发票明细信息分页
     *
     * @param userId 用户编号
     * @param pageReqVO 分页查询
     */
    PageResult<CashInvoiceDetailDO> getCashInvoiceDetailPage(Long userId, CashInvoiceDetailBpmPageReqVO pageReqVO);
}
