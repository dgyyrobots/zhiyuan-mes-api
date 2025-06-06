package com.dofast.module.trade.service.cart;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.module.product.api.sku.ProductSkuApi;
import com.dofast.module.product.api.sku.dto.ProductSkuRespDTO;
import com.dofast.module.product.api.spu.ProductSpuApi;
import com.dofast.module.product.api.spu.dto.ProductSpuRespDTO;
import com.dofast.module.promotion.api.price.PriceApi;
import com.dofast.module.promotion.api.price.dto.PriceCalculateRespDTO;
import com.dofast.module.promotion.enums.common.PromotionLevelEnum;
import com.dofast.module.trade.controller.app.cart.vo.*;
import com.dofast.module.trade.convert.cart.TradeCartConvert;
import com.dofast.module.trade.dal.dataobject.cart.CartDO;
import com.dofast.module.trade.dal.dataobject.cart.TradeCartItemDO;
import com.dofast.module.trade.dal.mysql.cart.CartMapper;
import com.dofast.module.trade.dal.mysql.cart.TradeCartItemMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.*;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertMap;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertSet;
import static com.dofast.module.product.enums.ErrorCodeConstants.SKU_NOT_EXISTS;
import static com.dofast.module.product.enums.ErrorCodeConstants.SKU_STOCK_NOT_ENOUGH;
import static com.dofast.module.trade.enums.ErrorCodeConstants.CARD_ITEM_NOT_FOUND;
import static java.util.Collections.emptyList;

/**
 * 购物车 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TradeCartServiceImpl implements TradeCartService {

    @Autowired
    private TradeCartItemMapper cartItemMapper;

    @Autowired
    private ProductSkuApi productSkuApi;
    @Autowired
    private PriceApi priceApi;

    @Autowired
    private CartMapper cartMapper;

    @Resource
    private ProductSpuApi productSpuApi;

    @Override
    public void addCartItemCount(Long userId, AppTradeCartItemAddCountReqVO addCountReqVO) {
        Long skuId = addCountReqVO.getSkuId();
        Integer count = addCountReqVO.getCount();
        // 查询 CartItemDO
        TradeCartItemDO tradeItem = cartItemMapper.selectByUserIdAndSkuId(userId, addCountReqVO.getSkuId());

        // 存在，则进行数量更新
        if (tradeItem != null) {
            checkProductSku(skuId, tradeItem.getCount() + count);
            cartItemMapper.updateById(new TradeCartItemDO().setId(tradeItem.getId())
                    .setSelected(true).setCount(tradeItem.getCount() + count));
            return;
        }

        // 不存在，则进行插入
        ProductSkuRespDTO sku = checkProductSku(skuId, count);
        cartItemMapper.insert(new TradeCartItemDO().setUserId(userId).setSpuId(sku.getSpuId()).setSkuId(sku.getId())
                .setSelected(true).setCount(count));
    }

    @Override
    public void updateCartItemCount(Long userId, AppTradeCartItemUpdateCountReqVO updateCountReqVO) {
        // 校验 TradeCartItemDO 存在
        TradeCartItemDO tradeItem = cartItemMapper.selectByUserIdAndSkuId(userId, updateCountReqVO.getSkuId());
        if (tradeItem == null) {
            throw exception(CARD_ITEM_NOT_FOUND);
        }
        // 校验商品 SKU
        checkProductSku(updateCountReqVO.getSkuId(), updateCountReqVO.getCount());

        // 更新数量
        cartItemMapper.updateById(new TradeCartItemDO().setId(tradeItem.getId()).setCount(updateCountReqVO.getCount()));
    }

    @Override
    public void updateCartItemSelected(Long userId, AppTradeCartItemUpdateSelectedReqVO updateSelectedReqVO) {
        // 查询 CartItemDO 列表
        List<TradeCartItemDO> cartItems = cartItemMapper.selectListByUserIdAndSkuIds(userId, updateSelectedReqVO.getSkuIds());
        if (CollUtil.isEmpty(cartItems)) {
            return;
        }

        // 更新选中
        cartItemMapper.updateByIds(CollectionUtils.convertList(cartItems, TradeCartItemDO::getId),
                new TradeCartItemDO().setSelected(updateSelectedReqVO.getSelected()));
    }

    /**
     * 购物车删除商品
     *
     * @param userId 用户编号
     * @param skuIds 商品 SKU 编号的数组
     */
    @Override
    public void deleteCartItems(Long userId, Collection<Long> skuIds) {
        // 查询 CartItemDO 列表
        List<TradeCartItemDO> cartItems = cartItemMapper.selectListByUserIdAndSkuIds(userId, skuIds);
        if (CollUtil.isEmpty(cartItems)) {
            return;
        }

        // 批量标记删除
        cartItemMapper.deleteBatchIds(convertSet(cartItems, TradeCartItemDO::getId));
    }

    @Override
    public Integer getCartCount(Long userId) {
        return cartItemMapper.selectSumByUserId(userId);
    }

    @Override
    public AppTradeCartDetailRespVO getCartDetail(Long userId) {
        // 获得购物车的商品
        List<TradeCartItemDO> cartItems = cartItemMapper.selectListByUserId(userId, null);
        // 如果未空，则返回空结果
        if (CollUtil.isEmpty(cartItems)) {
            return TradeCartConvert.INSTANCE.buildEmptyAppTradeCartDetailRespVO();
        }

        // 调用价格服务，计算价格
        PriceCalculateRespDTO priceCalculate = priceApi.calculatePrice(TradeCartConvert.INSTANCE.convert(userId, cartItems));

        // 转换返回
        Map<Long, TradeCartItemDO> cartItemMap = convertMap(cartItems, TradeCartItemDO::getSkuId);
        Map<Long, PriceCalculateRespDTO.OrderItem> orderItemMap = convertMap(priceCalculate.getOrder().getItems(),
                PriceCalculateRespDTO.OrderItem::getSkuId);
        List<AppTradeCartDetailRespVO.ItemGroup> itemGroups = new ArrayList<>(cartItems.size());
        // ① 场景一，营销活动，订单级别 TODO 芋艿：待测试
        priceCalculate.getPromotions().stream().filter(promotion -> PromotionLevelEnum.ORDER.getLevel().equals(promotion.getLevel()))
                .forEach(promotion -> {
                    AppTradeCartDetailRespVO.ItemGroup itemGroup = new AppTradeCartDetailRespVO.ItemGroup().setItems(new ArrayList<>())
                            .setPromotion(TradeCartConvert.INSTANCE.convert(promotion));
                    itemGroups.add(itemGroup);
                    promotion.getItems().forEach(promotionItem -> {
                        PriceCalculateRespDTO.OrderItem orderItem = orderItemMap.remove(promotionItem.getSkuId());
                        Assert.notNull(orderItem, "商品 SKU({}) 对应的订单项不能为空", promotionItem.getSkuId());
                        TradeCartItemDO cartItem = cartItemMap.get(orderItem.getSkuId());
                        itemGroup.getItems().add(TradeCartConvert.INSTANCE.convert(orderItem, cartItem)); // TODO spu
                    });
                });
        // ② 场景二，营销活动，商品级别
        orderItemMap.values().forEach(orderItem -> {
            AppTradeCartDetailRespVO.ItemGroup itemGroup = new AppTradeCartDetailRespVO.ItemGroup().setItems(new ArrayList<>(1)).setPromotion(null);
            itemGroups.add(itemGroup);
            TradeCartItemDO cartItem = cartItemMap.get(orderItem.getSkuId());
            itemGroup.getItems().add(TradeCartConvert.INSTANCE.convert(orderItem, cartItem)); // TODO spu
        });
        AppTradeCartDetailRespVO appTradeCartDetailRespVO = new AppTradeCartDetailRespVO().setItemGroups(itemGroups)
                .setOrder(TradeCartConvert.INSTANCE.convert(priceCalculate.getOrder()));
        return appTradeCartDetailRespVO;
    }





    private void deleteCartIfSpuDeleted(List<CartDO> carts, List<ProductSpuRespDTO> spus) {
        // 如果 SPU 被删除，则删除购物车对应的商品。延迟删除
        carts.removeIf(cart -> {
            if (spus.stream().noneMatch(spu -> spu.getId().equals(cart.getSpuId()))) {
                cartMapper.deleteById(cart.getId());
                return true;
            }
            return false;
        });
    }

    /**
     * 校验商品 SKU 是否合法
     * 1. 是否存在
     * 2. 是否下架
     * 3. 库存不足
     *
     * @param skuId 商品 SKU 编号
     * @param count 商品数量
     * @return 商品 SKU
     */
    private ProductSkuRespDTO checkProductSku(Long skuId, Integer count) {
        ProductSkuRespDTO sku = productSkuApi.getSku(skuId);
        if (sku == null || CommonStatusEnum.DISABLE.getStatus().equals(sku.getStatus())) {
            throw exception(SKU_NOT_EXISTS);
        }
        if (count > sku.getStock()) {
            throw exception(SKU_STOCK_NOT_ENOUGH);
        }
        return sku;
    }

}
