package com.dofast.module.product.api.spu;

import cn.hutool.core.collection.CollectionUtil;
import com.dofast.module.product.api.spu.dto.ProductSpuRespDTO;
import com.dofast.module.product.convert.spu.ProductSpuConvert;
import com.dofast.module.product.service.spu.ProductSpuService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 商品 SPU API 接口实现类
 *
 * @author LeeYan9
 * @since 2022-09-06
 */
@Service
@Validated
public class ProductSpuApiImpl implements ProductSpuApi {

    @Resource
    private ProductSpuService spuService;

    @Override
    public List<ProductSpuRespDTO> getSpuList(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return ProductSpuConvert.INSTANCE.convertList2(spuService.getSpuList(ids));
    }

    @Override
    public ProductSpuRespDTO getSpu(Long id) {
        return ProductSpuConvert.INSTANCE.convert02(spuService.getSpu(id));
    }

    @Override
    public List<ProductSpuRespDTO> validateSpuList(Collection<Long> ids) {
        return ProductSpuConvert.INSTANCE.convertList2(spuService.validateSpuList(ids));
    }






}
