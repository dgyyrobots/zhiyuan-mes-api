package com.dofast.module.product.api.sku;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.dofast.module.product.api.sku.dto.ProductSkuRespDTO;
import com.dofast.module.product.api.sku.dto.ProductSkuUpdateStockReqDTO;
import com.dofast.module.product.convert.sku.ProductSkuConvert;
import com.dofast.module.product.dal.dataobject.sku.ProductSkuDO;
import com.dofast.module.product.service.sku.ProductSkuService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;


import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 商品 SKU API 实现类
 *
 * @author LeeYan9
 * @since 2022-09-06
 */
@Service
@Validated
public class ProductSkuApiImpl implements ProductSkuApi {

    @Resource
    private ProductSkuService productSkuService;

    @Override
    public ProductSkuRespDTO getSku(Long id) {
        ProductSkuDO sku = productSkuService.getSku(id);
        return ProductSkuConvert.INSTANCE.convert02(sku);
    }

    /*@Override
    public List<ProductSkuRespDTO> getSkuList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<ProductSkuDO> skus = productSkuService.getSkuList(ids);
        List<ProductSkuRespDTO> list=new ArrayList<>();
        for (ProductSkuDO productSkuDO : skus) {
            list.add(BeanUtil.toBean(productSkuDO,ProductSkuRespDTO.class));
        }
        return list;
    }*/

    @Override
    public List<ProductSkuRespDTO> getSkuList1(Collection<Long> spuIds) {
        if (CollUtil.isEmpty(spuIds)) {
            return Collections.emptyList();
        }
        List<ProductSkuDO> skus = productSkuService.getSkuListBySpuId(spuIds);
        return ProductSkuConvert.INSTANCE.convertList04(skus);
    }

    @Override
    public List<ProductSkuRespDTO> getSkuListBySpuId(Collection<Long> spuIds) {
        if (CollUtil.isEmpty(spuIds)) {
            return Collections.emptyList();
        }
        List<ProductSkuDO> skus = productSkuService.getSkuListBySpuId(spuIds);
        return ProductSkuConvert.INSTANCE.convertList04(skus);
    }



    @Override
    public void updateSkuStock(ProductSkuUpdateStockReqDTO updateStockReqDTO) {
        productSkuService.updateSkuStock(updateStockReqDTO);
    }

    @Override
    public List<ProductSkuRespDTO> getSkuList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<ProductSkuDO> skus = productSkuService.getSkuList(ids);
        return ProductSkuConvert.INSTANCE.convertList04(skus);
    }

}
