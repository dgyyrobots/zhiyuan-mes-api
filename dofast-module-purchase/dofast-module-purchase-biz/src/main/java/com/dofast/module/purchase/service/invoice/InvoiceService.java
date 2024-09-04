package com.dofast.module.purchase.service.invoice;

import java.util.*;
import javax.validation.*;
import com.dofast.module.purchase.controller.admin.invoice.vo.*;
import com.dofast.module.purchase.dal.dataobject.invoice.InvoiceDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 采购入库单 Service 接口
 *
 * @author 惠智造
 */
public interface InvoiceService {

    /**
     * 创建采购入库单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createInvoice(@Valid InvoiceCreateReqVO createReqVO);

    /**
     * 更新采购入库单
     *
     * @param updateReqVO 更新信息
     */
    void updateInvoice(@Valid InvoiceUpdateReqVO updateReqVO);

    /**
     * 删除采购入库单
     *
     * @param id 编号
     */
    void deleteInvoice(Integer id);

    /**
     * 获得采购入库单
     *
     * @param id 编号
     * @return 采购入库单
     */
    InvoiceDO getInvoice(Integer id);

    /**
     * 获得采购入库单列表
     *
     * @param ids 编号
     * @return 采购入库单列表
     */
    List<InvoiceDO> getInvoiceList(Collection<Integer> ids);

    /**
     * 获得采购入库单分页
     *
     * @param pageReqVO 分页查询
     * @return 采购入库单分页
     */
    PageResult<InvoiceDO> getInvoicePage(InvoicePageReqVO pageReqVO);

    /**
     * 获得采购入库单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 采购入库单列表
     */
    List<InvoiceDO> getInvoiceList(InvoiceExportReqVO exportReqVO);

}
