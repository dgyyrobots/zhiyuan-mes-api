package com.dofast.module.finance.service.cash;

import java.util.*;
import javax.validation.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDetailDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 财务发票明细 Service 接口
 *
 * @author 惠智造
 */
public interface CashInvoiceDetailService {

    /**
     * 创建财务发票明细
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCashInvoiceDetail(@Valid CashInvoiceDetailCreateReqVO createReqVO);

    /**
     * 更新财务发票明细
     *
     * @param updateReqVO 更新信息
     */
    void updateCashInvoiceDetail(@Valid CashInvoiceDetailUpdateReqVO updateReqVO);

    /**
     * 删除财务发票明细
     *
     * @param id 编号
     */
    void deleteCashInvoiceDetail(Long id);

    /**
     * 获得财务发票明细
     *
     * @param id 编号
     * @return 财务发票明细
     */
    CashInvoiceDetailDO getCashInvoiceDetail(Long id);

    /**
     * 获得财务发票明细列表
     *
     * @param ids 编号
     * @return 财务发票明细列表
     */
    List<CashInvoiceDetailDO> getCashInvoiceDetailList(Collection<Long> ids);

    /**
     * 获得财务发票明细分页
     *
     * @param pageReqVO 分页查询
     * @return 财务发票明细分页
     */
    PageResult<CashInvoiceDetailDO> getCashInvoiceDetailPage(CashInvoiceDetailPageReqVO pageReqVO);

    /**
     * 获得财务发票明细列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 财务发票明细列表
     */
    List<CashInvoiceDetailDO> getCashInvoiceDetailList(CashInvoiceDetailExportReqVO exportReqVO);

}
