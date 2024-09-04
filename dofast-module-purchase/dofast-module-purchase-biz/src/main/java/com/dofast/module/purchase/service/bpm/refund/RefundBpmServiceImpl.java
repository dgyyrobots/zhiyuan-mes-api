package com.dofast.module.purchase.service.bpm.refund;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.bpm.api.task.BpmProcessInstanceApi;
import com.dofast.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import com.dofast.module.purchase.controller.admin.bpm.refund.vo.RefundBpmCreateReqVO;
import com.dofast.module.purchase.controller.admin.bpm.refund.vo.RefundBpmPageReqVO;
import com.dofast.module.purchase.convert.refund.RefundConvert;
import com.dofast.module.purchase.dal.dataobject.refund.RefundDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pad.util.PadSecurityUtils.getUserId;
import static com.dofast.module.purchase.enums.ErrorCodeConstants.REFUND_NOT_EXISTS;

@Service
public class RefundBpmServiceImpl implements RefundBpmService{

    /**
     * 采购入库单对应的流程定义 KEY
     */
    public static final String REFUND_KEY = "purchase_refund";

    @Resource
    private com.dofast.module.purchase.dal.mysql.refund.PurchaseRefundMapper purchaseRefundMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;
    @Override
    public Long createRefund(Long userId, RefundBpmCreateReqVO createReqVO) {
        // 插入 售后信息
        RefundDO refundDO = RefundConvert.INSTANCE.convert(createReqVO);
        refundDO.setCreator(String.valueOf(getUserId()));
        purchaseRefundMapper.insert(refundDO);

        // 发起 BPM 流程
        Map<String, Object> processInstanceVariables = new HashMap<>();
        processInstanceVariables.put("after-order",createReqVO);
        String processInstanceId = processInstanceApi.createProcessInstance(userId,
                new BpmProcessInstanceCreateReqDTO().setProcessDefinitionKey(REFUND_KEY)
                        .setVariables(processInstanceVariables).setBusinessKey(String.valueOf(refundDO.getId())));

        // 将工作流的编号，更新到 采购订单中
        purchaseRefundMapper.updateById(RefundConvert.INSTANCE.convert(createReqVO).setId(refundDO.getId()).setProcessInstanceId(processInstanceId));
        return Long.valueOf(refundDO.getId());
    }

    @Override
    public void updateRefundResult(Long id, Integer result) {
        validateLeaveExists(id);
        purchaseRefundMapper.updateById(new RefundDO().setIsReturn(result));
    }

    private void validateLeaveExists(Long id) {
        if (purchaseRefundMapper.selectById(id) == null) {
            throw exception(REFUND_NOT_EXISTS);
        }
    }

    @Override
    public RefundDO getRefundDO(Long id) {
        return purchaseRefundMapper.selectById(id);
    }

    @Override
    public PageResult<RefundDO> getRefundDOPage(Long userId, RefundBpmPageReqVO pageReqVO) {
        return purchaseRefundMapper.selectPage(String.valueOf(userId),pageReqVO);
    }
}
