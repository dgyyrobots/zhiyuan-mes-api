package com.dofast.module.purchase.service.bpm.invoice;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.bpm.api.task.BpmProcessInstanceApi;
import com.dofast.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import com.dofast.module.purchase.controller.admin.bpm.invoice.vo.InvoiceBpmCreateReqVO;
import com.dofast.module.purchase.controller.admin.bpm.invoice.vo.InvoiceBpmPageReqVO;
import com.dofast.module.purchase.convert.invoice.InvoiceConvert;
import com.dofast.module.purchase.dal.dataobject.invoice.InvoiceDO;
import com.dofast.module.purchase.dal.mysql.invoice.InvoiceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pad.util.PadSecurityUtils.getUserId;
import static com.dofast.module.purchase.enums.ErrorCodeConstants.INVOICE_NOT_EXISTS;

@Service
public class InvoiceBpmServiceImpl implements InvoiceBpmService{

    /**
     * 采购入库单对应的流程定义 KEY
     */
    public static final String INVOICE_KEY = "purchase_invoice";

    @Resource
    private InvoiceMapper invoiceMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;
    @Override
    public Long createInvoice(Long userId, InvoiceBpmCreateReqVO createReqVO) {
        // 插入 售后信息
        InvoiceDO convert = InvoiceConvert.INSTANCE.convert(createReqVO);
        convert.setCreator(String.valueOf(getUserId()));
        invoiceMapper.insert(convert);

        // 发起 BPM 流程
        Map<String, Object> processInstanceVariables = new HashMap<>();
        processInstanceVariables.put("after-order",createReqVO);
        String processInstanceId = processInstanceApi.createProcessInstance(userId,
                new BpmProcessInstanceCreateReqDTO().setProcessDefinitionKey(INVOICE_KEY)
                        .setVariables(processInstanceVariables).setBusinessKey(String.valueOf(convert.getId())));

        // 将工作流的编号，更新到 采购入库单中
        invoiceMapper.updateById(InvoiceConvert.INSTANCE.convert(createReqVO).setId(convert.getId()).setProcessInstanceId(processInstanceId));
        return Long.valueOf(convert.getId());
    }

    @Override
    public void updateInvoiceResult(Long id, Integer result) {
        validateLeaveExists(id);
        invoiceMapper.updateById(new InvoiceDO().setIsWarehousing(result));
    }

    private void validateLeaveExists(Long id) {
        if (invoiceMapper.selectById(id) == null) {
            throw exception(INVOICE_NOT_EXISTS);
        }
    }

    @Override
    public InvoiceDO getInvoice(Long id) {
        return invoiceMapper.selectById(id);
    }

    @Override
    public PageResult<InvoiceDO> getInvoicePage(Long userId, InvoiceBpmPageReqVO pageReqVO) {
        return invoiceMapper.selectPage(String.valueOf(userId),pageReqVO);
    }
}
