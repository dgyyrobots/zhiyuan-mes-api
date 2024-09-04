package com.dofast.module.pro.api.ProcessApi;

import com.dofast.module.pro.api.ProcessApi.dto.ProcessDTO;

public interface ProcessApi {
    /**
     * 获得生产工序
     *
     * @param id 编号
     * @return 生产工序
     */
    ProcessDTO getcess(Long id);
}
