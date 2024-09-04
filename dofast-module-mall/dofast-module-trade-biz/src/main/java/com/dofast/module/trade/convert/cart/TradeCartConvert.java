package com.dofast.module.trade.convert.cart;





import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.product.api.sku.dto.ProductSkuRespDTO;
import com.dofast.module.product.api.spu.dto.ProductSpuRespDTO;
import com.dofast.module.product.enums.spu.ProductSpuStatusEnum;
import com.dofast.module.promotion.api.price.dto.PriceCalculateReqDTO;
import com.dofast.module.promotion.api.price.dto.PriceCalculateRespDTO;
import com.dofast.module.trade.controller.app.base.sku.AppProductSkuBaseRespVO;
import com.dofast.module.trade.controller.app.base.spu.AppProductSpuBaseRespVO;
import com.dofast.module.trade.controller.app.cart.vo.AppCartListRespVO;
import com.dofast.module.trade.controller.app.cart.vo.AppTradeCartDetailRespVO;
import com.dofast.module.trade.dal.dataobject.cart.CartDO;
import com.dofast.module.trade.dal.dataobject.cart.TradeCartItemDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.dofast.framework.common.util.collection.CollectionUtils.convertList1;





import static com.dofast.framework.common.util.collection.CollectionUtils.convertMap;

@Mapper
public interface TradeCartConvert {

    TradeCartConvert INSTANCE = Mappers.getMapper(TradeCartConvert.class);


    default AppTradeCartDetailRespVO buildEmptyAppTradeCartDetailRespVO() {
        return new AppTradeCartDetailRespVO().setItemGroups(Collections.emptyList())
                .setOrder(new AppTradeCartDetailRespVO.Order().setSkuOriginalPrice(0).setSkuPromotionPrice(0)
                        .setOrderPromotionPrice(0).setDeliveryPrice(0).setPayPrice(0));
    }

    default PriceCalculateReqDTO convert(Long userId, List<TradeCartItemDO> cartItems) {
        return new PriceCalculateReqDTO().setUserId(userId)
                .setItems(convertList1(cartItems, cartItem -> new PriceCalculateReqDTO.Item().setSkuId(cartItem.getSkuId())
                        .setCount(cartItem.getSelected() ? cartItem.getCount() : 0)));
    }

    // === AppTradeCartDetailRespVO 相关 ===

    AppTradeCartDetailRespVO.Promotion convert(PriceCalculateRespDTO.Promotion bean);

    @Mappings({
            @Mapping(source = "cartItem.count", target = "count")
    })
    AppTradeCartDetailRespVO.Sku convert(PriceCalculateRespDTO.OrderItem orderItem, TradeCartItemDO cartItem);

    AppTradeCartDetailRespVO.Order convert(PriceCalculateRespDTO.Order bean);





    default AppCartListRespVO convertList(List<CartDO> carts,
                                          List<ProductSpuRespDTO> spus, List<ProductSkuRespDTO> skus) {
        Map<Long, ProductSpuRespDTO> spuMap = convertMap(spus, ProductSpuRespDTO::getId);
        Map<Long, ProductSkuRespDTO> skuMap = convertMap(skus, ProductSkuRespDTO::getId);
        // 遍历，开始转换
        List<AppCartListRespVO.Cart> validList = new ArrayList<>(carts.size());
        List<AppCartListRespVO.Cart> invalidList = new ArrayList<>();
        carts.forEach(cart -> {
            AppCartListRespVO.Cart cartVO = new AppCartListRespVO.Cart();
            cartVO.setId(cart.getId()).setCount(cart.getCount()).setSelected(cart.getSelected());
            ProductSpuRespDTO spu = spuMap.get(cart.getSpuId());
            ProductSkuRespDTO sku = skuMap.get(cart.getSkuId());

            cartVO.setSpu(convert(spu)).setSku(convert(sku));

            cartVO.setSpu(BeanUtil.toBean(spu,AppProductSpuBaseRespVO.class)).setSku(BeanUtil.toBean(sku,AppProductSkuBaseRespVO.class));

            // 如果 SPU 不存在，或者下架，或者库存不足，说明是无效的
            if (spu == null
                    || !ProductSpuStatusEnum.isEnable(spu.getStatus())
                    || spu.getStock() <= 0) {
                cartVO.setSelected(false); // 强制设置成不可选中
                invalidList.add(cartVO);
            } else {
                // 虽然 SKU 可能也会不存在，但是可以通过购物车重新选择
                validList.add(cartVO);
            }
        });
        return new AppCartListRespVO().setValidList(validList).setInvalidList(invalidList);
    }


    AppProductSpuBaseRespVO convert(ProductSpuRespDTO spu);
    AppProductSkuBaseRespVO convert(ProductSkuRespDTO sku);




}
