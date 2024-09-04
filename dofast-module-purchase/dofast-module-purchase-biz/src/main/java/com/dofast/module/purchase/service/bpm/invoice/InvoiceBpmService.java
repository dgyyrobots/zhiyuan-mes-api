package com.dofast.module.purchase.service.bpm.invoice;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.purchase.controller.admin.bpm.invoice.vo.InvoiceBpmCreateReqVO;
import com.dofast.module.purchase.controller.admin.bpm.invoice.vo.InvoiceBpmPageReqVO;
import com.dofast.module.purchase.dal.dataobject.invoice.InvoiceDO;

import javax.validation.Valid;

public interface InvoiceBpmService {

    /**
     * 创建采购入库单信息
     *
     * @param userId 用户编号
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInvoice(Long userId, @Valid InvoiceBpmCreateReqVO createReqVO);

    /**
     * 更新采购入库单的状态
     *
     * @param id 编号
     * @param result 结果
     */
    void updateInvoiceResult(Long id, Integer result);

    /**
     * 获得采购入库单信息
     *
     * @param id 编号
     * @return 请假申请
     */
    InvoiceDO getInvoice(Long id);

    /**
     * 获得采购入库单信息分页
     *
     * @param userId 用户编号
     * @param pageReqVO 分页查询
     */
    PageResult<InvoiceDO> getInvoicePage(Long userId, InvoiceBpmPageReqVO pageReqVO);
}
