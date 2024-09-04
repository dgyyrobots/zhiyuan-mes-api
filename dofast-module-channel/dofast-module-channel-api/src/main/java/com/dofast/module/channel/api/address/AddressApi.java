package com.dofast.module.channel.api.address;

import com.dofast.module.channel.api.address.dto.AddressGetDTO;
import com.dofast.module.channel.api.address.dto.AddressReceiveDTO;

/**
 * 渠道交易客户的 API 的接口
 *
 * @author 芋道源码
 */
public interface AddressApi {

    /**
     * 渠道订单创建事件的创建Address的接口
     * @param addressGetDTO    参数来自于获取的字符串的json解析转换的getDTO
     *
     */
    public Integer createAddress(AddressGetDTO addressGetDTO);

    public Integer createAddress(AddressReceiveDTO addressReceiveDTO);
}
