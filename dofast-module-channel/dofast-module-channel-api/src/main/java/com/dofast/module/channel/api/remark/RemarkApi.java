package com.dofast.module.channel.api.remark;

import com.dofast.module.channel.api.remark.dto.RemarkDto;

public interface RemarkApi {

    public RemarkDto getRemarkByOrderId(Long tradeOrderId);
}
