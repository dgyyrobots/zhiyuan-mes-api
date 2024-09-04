package com.dofast.module.finance.service.bpm.cashTrade;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.bpm.api.task.BpmProcessInstanceApi;
import com.dofast.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import com.dofast.module.finance.controller.admin.bpm.cashTrade.vo.CashTradeBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashTrade.vo.CashTradeBpmPageReqVO;
import com.dofast.module.finance.convert.cash.CashTradeConvert;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeDO;
import com.dofast.module.finance.dal.mysql.cash.CashTradeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pad.util.PadSecurityUtils.getUserId;
import static com.dofast.module.finance.enums.ErrorCodeConstants.CASH_TRADE_NOT_EXISTS;

@Service
public class CashTradeBpmServiceImpl implements CashTradeBpmService{


    /**
     * 售后对应的流程定义 KEY
     */
    public static final String CASHTRADE_KEY = "cash_trade";

    @Resource
    private CashTradeMapper cashTradeMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;


    @Override
    public Long createCashTrade(Long userId, CashTradeBpmCreateReqVO createReqVO) {
        // 插入 售后信息
        createReqVO.setTrader(getUserId());
        CashTradeDO cashTradeDO = CashTradeConvert.INSTANCE.convert(createReqVO);
        cashTradeMapper.insert(cashTradeDO);



        // 发起 BPM 流程
        Map<String, Object> processInstanceVariables = new HashMap<>();
        processInstanceVariables.put("after-order",createReqVO);
        String processInstanceId = processInstanceApi.createProcessInstance(userId,
                new BpmProcessInstanceCreateReqDTO().setProcessDefinitionKey(CASHTRADE_KEY)
                        .setVariables(processInstanceVariables).setBusinessKey(String.valueOf(cashTradeDO.getId())));

        // 将工作流的编号，更新到售后单中
        cashTradeMapper.updateById(CashTradeConvert.INSTANCE.convert(createReqVO).setProcessInstanceId(processInstanceId));
        return cashTradeDO.getId();
    }

    @Override
    public void updateCashTradeResult(Long id, Integer result) {
        validateLeaveExists(id);
        cashTradeMapper.updateById(new CashTradeDO().setId(id).setIsReturn(result));
    }

    private void validateLeaveExists(Long id) {
        if (cashTradeMapper.selectById(id) == null) {
            throw exception(CASH_TRADE_NOT_EXISTS);
        }
    }

    @Override
    public CashTradeDO getCashTrade(Long id) {
        return cashTradeMapper.selectById(id);
    }

    @Override
    public PageResult<CashTradeDO> getCashTradePage(Long userId, CashTradeBpmPageReqVO pageReqVO) {
        return cashTradeMapper.selectPage(pageReqVO.setTrader(userId));
    }
}
