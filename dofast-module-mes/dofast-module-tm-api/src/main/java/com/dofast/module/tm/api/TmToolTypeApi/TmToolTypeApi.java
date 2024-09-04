package com.dofast.module.tm.api.TmToolTypeApi;

import com.dofast.module.tm.api.TmToolTypeApi.dto.TmToolTypeDTO;

public interface TmToolTypeApi {
    TmToolTypeDTO selectById(Long id);
}
