package com.dofast.module.trade.api.mixinorder;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.trade.api.mixinorder.dto.MixinOrderDTO;
import com.dofast.module.trade.service.mixin.MixinOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MixinOrderApiImpl implements MixinOrderApi{
    @Resource
    MixinOrderService mixinOrderService;


    @Override
    public MixinOrderDTO getMixinOrder(Long id) {
        MixinOrderDTO mixinOrderDTO = BeanUtil.toBean(mixinOrderService.getMixinOrder(id), MixinOrderDTO.class);
        return mixinOrderDTO;
    }

    @Override
    public MixinOrderDTO getByNo(String no) {
        MixinOrderDTO mixinOrderDTO = BeanUtil.toBean(mixinOrderService.getByNo(no), MixinOrderDTO.class);
        return mixinOrderDTO;
    }
}
