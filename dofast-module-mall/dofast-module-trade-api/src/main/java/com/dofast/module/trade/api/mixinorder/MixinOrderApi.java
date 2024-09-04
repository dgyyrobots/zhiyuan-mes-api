package com.dofast.module.trade.api.mixinorder;

import com.dofast.module.trade.api.mixinorder.dto.MixinOrderDTO;

public interface MixinOrderApi {
    MixinOrderDTO getMixinOrder(Long id);

    MixinOrderDTO getByNo(String no);
}
