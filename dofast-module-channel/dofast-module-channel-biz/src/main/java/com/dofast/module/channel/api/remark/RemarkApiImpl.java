package com.dofast.module.channel.api.remark;

import com.dofast.module.channel.api.remark.dto.RemarkDto;
import com.dofast.module.channel.convert.remark.RemarkConvert;
import com.dofast.module.channel.dal.dataobject.remark.RemarkDO;
import com.dofast.module.channel.service.remark.RemarkService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
@Service
@Validated
public class RemarkApiImpl implements RemarkApi{

    @Resource
    private RemarkService remarkService;
    @Override
    public RemarkDto getRemarkByOrderId(Long tradeOrderId) {
        if (remarkService.getRemarkListByTradeOrder(tradeOrderId).size()>0){
            RemarkDO remarkDO = remarkService.getRemarkListByTradeOrder(tradeOrderId).get(0);
            return RemarkConvert.INSTANCE.convert01(remarkDO);
        }
        return null;
    }
}
