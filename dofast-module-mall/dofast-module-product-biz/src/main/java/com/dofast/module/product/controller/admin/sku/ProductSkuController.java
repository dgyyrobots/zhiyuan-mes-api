package com.dofast.module.product.controller.admin.sku;



import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.util.collection.MapUtils;
import com.dofast.module.product.controller.admin.sku.vo.ProductSkuOptionRespVO;
import com.dofast.module.product.convert.sku.ProductSkuConvert;
import com.dofast.module.product.dal.dataobject.property.ProductPropertyValueDO;
import com.dofast.module.product.dal.dataobject.sku.ProductSkuDO;
import com.dofast.module.product.dal.dataobject.spu.ProductSpuDO;
import com.dofast.module.product.service.property.ProductPropertyValueService;
import com.dofast.module.product.service.sku.ProductSkuService;
import com.dofast.module.product.service.spu.ProductSpuService;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import javax.annotation.security.PermitAll;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertSet;


@Tag(name = "管理后台 - 商品 SKU")
@RestController
@RequestMapping("/product/sku")
@Validated
public class ProductSkuController {



    @Autowired
    private ProductSkuService productSkuService;
    @Autowired
    private ProductSpuService productSpuService;

    @Autowired
    private ProductPropertyValueService productPropertyValueService;


    @GetMapping("/get-option-list")
    @Operation(summary = "获得商品 SKU 选项的列表")
    @PreAuthorize("@ss.hasPermission('product:sku:query')")
    public CommonResult<List<ProductSkuOptionRespVO>> getSkuOptionList() {
        // 获得 SKU 列表
        List<ProductSkuDO> skus = productSkuService.getSkuList();
        if (CollUtil.isEmpty(skus)) {
            return success(Collections.emptyList());
        }
        // 获得对应的 SPU 映射
        Map<Long, ProductSpuDO> spuMap = productSpuService.getSpuMap(convertSet(skus, ProductSkuDO::getSpuId));
        // 转换为返回结果
        List<ProductSkuOptionRespVO> skuVOs = ProductSkuConvert.INSTANCE.convertList05(skus);
        skuVOs.forEach(sku -> MapUtils.findAndThen(spuMap, sku.getSpuId(),
                spu -> sku.setSpuId(spu.getId()).setSpuName(spu.getName())));
        skuVOs.stream().forEach(skuVO -> {
            skus.stream()
                    .filter(sku -> skuVO.getId().equals(sku.getId()))
                    .findFirst()
                    .ifPresent(sku -> {
                        // 根据条件查询某个值
                        if(sku.getProperties()!=null){
                            ProductSkuDO.Property property = sku.getProperties().get(0);
                            ProductPropertyValueDO productPropertyValueDO = productPropertyValueService.getPropertyValue(property.getValueId());
                            // 将查询到的值赋值到 skuVO 中
                            skuVO.setName(productPropertyValueDO.getName());
                        }
                    });
        });
        return success(skuVOs);
    }

}
