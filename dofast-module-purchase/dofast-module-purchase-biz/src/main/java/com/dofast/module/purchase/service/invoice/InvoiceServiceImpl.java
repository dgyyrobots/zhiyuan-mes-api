package com.dofast.module.purchase.service.invoice;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.purchase.controller.admin.invoice.vo.*;
import com.dofast.module.purchase.dal.dataobject.invoice.InvoiceDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.purchase.convert.invoice.InvoiceConvert;
import com.dofast.module.purchase.dal.mysql.invoice.InvoiceMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.purchase.enums.ErrorCodeConstants.*;

/**
 * 采购入库单 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class InvoiceServiceImpl implements InvoiceService {

    @Resource
    private InvoiceMapper invoiceMapper;

    @Override
    public Integer createInvoice(InvoiceCreateReqVO createReqVO) {
        // 插入
        InvoiceDO invoice = InvoiceConvert.INSTANCE.convert(createReqVO);
        invoiceMapper.insert(invoice);
        // 返回
        return invoice.getId();
    }

    @Override
    public void updateInvoice(InvoiceUpdateReqVO updateReqVO) {
        // 校验存在
        validateInvoiceExists(updateReqVO.getId());
        // 更新
        InvoiceDO updateObj = InvoiceConvert.INSTANCE.convert(updateReqVO);
        invoiceMapper.updateById(updateObj);
    }

    @Override
    public void deleteInvoice(Integer id) {
        // 校验存在
        validateInvoiceExists(id);
        // 删除
        invoiceMapper.deleteById(id);
    }

    private void validateInvoiceExists(Integer id) {
        if (invoiceMapper.selectById(id) == null) {
            throw exception(INVOICE_NOT_EXISTS);
        }
    }

    @Override
    public InvoiceDO getInvoice(Integer id) {
        return invoiceMapper.selectById(id);
    }

    @Override
    public List<InvoiceDO> getInvoiceList(Collection<Integer> ids) {
        return invoiceMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<InvoiceDO> getInvoicePage(InvoicePageReqVO pageReqVO) {
        return invoiceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<InvoiceDO> getInvoiceList(InvoiceExportReqVO exportReqVO) {
        return invoiceMapper.selectList(exportReqVO);
    }

}
