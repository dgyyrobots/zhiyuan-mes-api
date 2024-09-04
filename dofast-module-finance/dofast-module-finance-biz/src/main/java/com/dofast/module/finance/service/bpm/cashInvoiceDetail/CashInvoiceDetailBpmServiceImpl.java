package com.dofast.module.finance.service.bpm.cashInvoiceDetail;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.bpm.api.task.BpmProcessInstanceApi;
import com.dofast.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail.vo.CashInvoiceDetailBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail.vo.CashInvoiceDetailBpmPageReqVO;
import com.dofast.module.finance.convert.cash.CashInvoiceDetailConvert;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDetailDO;
import com.dofast.module.finance.dal.mysql.cash.CashInvoiceDetailMapper;
import liquibase.pro.packaged.S;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pad.util.PadSecurityUtils.getUserId;
import static com.dofast.module.finance.enums.ErrorCodeConstants.CASH_INVOICE_DETAIL_NOT_EXISTS;

@Service
public class CashInvoiceDetailBpmServiceImpl implements CashInvoiceDetailBpmService{

    /**
     * 采购入库单对应的流程定义 KEY
     */
    public static final String CASHINVOICDETAIL_KEY = "cashInvoiceDetail_invoice";

    @Resource
    private CashInvoiceDetailMapper cashInvoiceDetailMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;
    @Override
    public Long createCashInvoiceDetail(Long userId, CashInvoiceDetailBpmCreateReqVO createReqVO) {
        // 插入 售后信息
        CashInvoiceDetailDO convert = CashInvoiceDetailConvert.INSTANCE.convert(createReqVO);
        convert.setCreator(String.valueOf(getUserId()));
        cashInvoiceDetailMapper.insert(convert);

        // 发起 BPM 流程
        Map<String, Object> processInstanceVariables = new HashMap<>();
        processInstanceVariables.put("after-order",createReqVO);
        String processInstanceId = processInstanceApi.createProcessInstance(userId,
                new BpmProcessInstanceCreateReqDTO().setProcessDefinitionKey(CASHINVOICDETAIL_KEY)
                        .setVariables(processInstanceVariables).setBusinessKey(String.valueOf(convert.getId())));

        // 将工作流的编号，更新到 采购入库单中
        cashInvoiceDetailMapper.updateById(CashInvoiceDetailConvert.INSTANCE.convert(createReqVO).setId(convert.getId()).setProcessInstanceId(processInstanceId));
        return Long.valueOf(convert.getId());
    }

    @Override
    public void updateCashInvoiceDetailResult(Long id, Integer result) {
        validateLeaveExists(id);
        cashInvoiceDetailMapper.updateById(new CashInvoiceDetailDO().setStatus(String.valueOf(result)));
    }

    private void validateLeaveExists(Long id) {
        if (cashInvoiceDetailMapper.selectById(id) == null) {
            throw exception(CASH_INVOICE_DETAIL_NOT_EXISTS);
        }
    }

    @Override
    public CashInvoiceDetailDO getCashInvoiceDetail(Long id) {
        return cashInvoiceDetailMapper.selectById(id);
    }

    @Override
    public PageResult<CashInvoiceDetailDO> getCashInvoiceDetailPage(Long userId, CashInvoiceDetailBpmPageReqVO pageReqVO) {
        return cashInvoiceDetailMapper.selectPage(String.valueOf(userId),pageReqVO);
    }
}
