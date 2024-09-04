package com.dofast.module.finance.service.bpm.invoice;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.bpm.api.task.BpmProcessInstanceApi;
import com.dofast.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import com.dofast.module.finance.controller.admin.bpm.cashInvoice.vo.CashInvoiceBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashInvoice.vo.CashInvoiceBpmPageReqVO;
import com.dofast.module.finance.convert.cash.CashFundConvert;
import com.dofast.module.finance.convert.cash.CashInvoiceConvert;
import com.dofast.module.finance.dal.dataobject.cash.CashFundDO;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDO;
import com.dofast.module.finance.dal.mysql.cash.CashFundMapper;
import com.dofast.module.finance.dal.mysql.cash.CashInvoiceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pad.util.PadSecurityUtils.getUserId;
import static com.dofast.module.finance.enums.ErrorCodeConstants.CASH_TRADE_INVOICE_NOT_EXISTS;

@Service
public class CashInvoiceBpmServiceImpl implements CashInvoiceBpmService{

    /**
     * 财务退款对应的流程定义 KEY
     */
    public static final String CASHFUND_KEY = "cash_invoice";

    @Resource
    private CashInvoiceMapper cashInvoiceMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;

    @Override
    public Long createCashInvoice(Long userId, CashInvoiceBpmCreateReqVO createReqVO) {
        // 插入 财务发票信息
        CashInvoiceDO convert = CashInvoiceConvert.INSTANCE.convert(createReqVO);
        convert.setCreator(String.valueOf(getUserId()));
        cashInvoiceMapper.insert(convert);

        // 发起 BPM 流程
        Map<String, Object> processInstanceVariables = new HashMap<>();
        processInstanceVariables.put("after-order",createReqVO);
        String processInstanceId = processInstanceApi.createProcessInstance(userId,
                new BpmProcessInstanceCreateReqDTO().setProcessDefinitionKey(CASHFUND_KEY)
                        .setVariables(processInstanceVariables).setBusinessKey(String.valueOf(convert.getId())));

        // 将工作流的编号，更新到 采购入库单中
        cashInvoiceMapper.updateById(CashInvoiceConvert.INSTANCE.convert(createReqVO).setId(convert.getId()).setProcessInstanceId(processInstanceId));
        return Long.valueOf(convert.getId());
    }

    @Override
    public void updateCashInvoiceResult(Long id, Integer result) {
        validateLeaveExists(id);
        cashInvoiceMapper.updateById(new CashInvoiceDO().setStatus(String.valueOf(result)));
    }

    private void validateLeaveExists(Long id) {
        if (cashInvoiceMapper.selectById(id) == null) {
            throw exception(CASH_TRADE_INVOICE_NOT_EXISTS);
        }
    }

    @Override
    public CashInvoiceDO getCashInvoice(Long id) {
        return cashInvoiceMapper.selectById(id);
    }

    @Override
    public PageResult<CashInvoiceDO> getCashInvoicePage(Long userId, CashInvoiceBpmPageReqVO pageReqVO) {
        return cashInvoiceMapper.selectPage(String.valueOf(userId),pageReqVO);
    }
}
