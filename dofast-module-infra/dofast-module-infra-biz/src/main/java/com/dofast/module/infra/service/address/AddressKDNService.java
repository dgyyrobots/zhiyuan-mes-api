package com.dofast.module.infra.service.address;

import com.dofast.module.infra.controller.admin.address.vo.AddressData;

import java.io.IOException;

public interface AddressKDNService {

    /**
     * 进行地址解析返回完整的地址信息
     *
     * @param content 不完整的地址信息
     * @return 完整的地址信息
     */
    AddressData getAddressData(String content) throws IOException;
}
