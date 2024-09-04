package com.dofast.module.trade.convert.order;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.framework.ip.core.utils.AreaUtils;
import com.dofast.module.channel.api.ordergoods.dto.OrderGoodsGetDTO;
import com.dofast.module.member.api.address.dto.AddressRespDTO;
import com.dofast.module.member.api.user.dto.MemberUserRespDTO;
import com.dofast.module.pay.api.order.dto.PayOrderCreateReqDTO;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.framework.dict.core.util.DictFrameworkUtils;
import com.dofast.module.pay.enums.DictTypeConstants;
import com.dofast.module.product.api.comment.dto.ProductCommentCreateReqDTO;
import com.dofast.module.product.api.property.dto.ProductPropertyValueDetailRespDTO;
import com.dofast.module.product.api.sku.dto.ProductSkuRespDTO;
import com.dofast.module.product.api.sku.dto.ProductSkuUpdateStockReqDTO;
import com.dofast.module.product.api.spu.dto.ProductSpuRespDTO;
import com.dofast.module.promotion.api.price.dto.PriceCalculateReqDTO;
import com.dofast.module.promotion.api.price.dto.PriceCalculateRespDTO;
import com.dofast.module.trade.controller.admin.base.member.user.MemberUserRespVO;
import com.dofast.module.trade.controller.admin.base.product.property.ProductPropertyValueDetailRespVO;
import com.dofast.module.trade.controller.admin.order.vo.TradeOrderCreateReqVO;
import com.dofast.module.trade.controller.admin.order.vo.TradeOrderDetailRespVO;
import com.dofast.module.trade.controller.admin.order.vo.TradeOrderPageItemRespVO;
import com.dofast.module.trade.controller.app.base.property.AppProductPropertyValueDetailRespVO;
import com.dofast.module.trade.controller.app.order.vo.AppTradeOrderCreateReqVO;
import com.dofast.module.trade.controller.app.order.vo.AppTradeOrderDetailRespVO;
import com.dofast.module.trade.controller.app.order.vo.AppTradeOrderPageItemRespVO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderItemDO;
import com.dofast.module.trade.enums.order.TradeOrderItemAfterSaleStatusEnum;
import com.dofast.module.trade.framework.config.TradeOrderProperties;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.dofast.module.promotion.api.combination.dto.CombinationRecordCreateReqDTO;
import com.dofast.module.trade.api.order.dto.TradeOrderRespDTO;
import com.dofast.module.trade.controller.admin.order.vo.*;
import com.dofast.module.trade.controller.app.order.vo.*;
import com.dofast.module.trade.controller.app.order.vo.item.AppTradeOrderItemCommentCreateReqVO;
import com.dofast.module.trade.controller.app.order.vo.item.AppTradeOrderItemRespVO;
import com.dofast.module.trade.dal.dataobject.cart.CartDO;
import com.dofast.module.trade.dal.dataobject.delivery.DeliveryExpressDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderLogDO;
import com.dofast.module.trade.framework.delivery.core.client.dto.ExpressTrackRespDTO;
import com.dofast.module.trade.service.brokerage.bo.BrokerageAddReqBO;
import com.dofast.module.trade.service.price.bo.TradePriceCalculateReqBO;
import com.dofast.module.trade.service.price.bo.TradePriceCalculateRespBO;
import org.mapstruct.Named;

import java.util.*;
import java.util.stream.Collectors;

import static com.dofast.framework.common.util.collection.CollectionUtils.convertMap;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertMultiMap;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.addTime;

@Mapper
public interface TradeOrderConvert {


    TradeOrderConvert INSTANCE = Mappers.getMapper(TradeOrderConvert.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "createReqVO.couponId", target = "couponId"),
            @Mapping(target = "remark", ignore = true),
            @Mapping(source = "createReqVO.remark", target = "userRemark"),
            @Mapping(source = "calculateRespBO.price.totalPrice", target = "totalPrice"),
            @Mapping(source = "calculateRespBO.price.discountPrice", target = "discountPrice"),
            @Mapping(source = "calculateRespBO.price.deliveryPrice", target = "deliveryPrice"),
            @Mapping(source = "calculateRespBO.price.couponPrice", target = "couponPrice"),
            @Mapping(source = "calculateRespBO.price.pointPrice", target = "pointPrice"),
            @Mapping(source = "calculateRespBO.price.vipPrice", target = "vipPrice"),
            @Mapping(source = "calculateRespBO.price.payPrice", target = "payPrice")
    })
    TradeOrderDO convert(Long userId, String userIp, AppTradeOrderCreateReqVO createReqVO,
                         TradePriceCalculateRespBO calculateRespBO);

    TradeOrderRespDTO convert(TradeOrderDO orderDO);

    default List<TradeOrderItemDO> convertList(TradeOrderDO tradeOrderDO, TradePriceCalculateRespBO calculateRespBO) {
        return CollectionUtils.convertList(calculateRespBO.getItems(), item -> {
            TradeOrderItemDO orderItem = convert(item);
            orderItem.setOrderId(tradeOrderDO.getId());
            orderItem.setUserId(tradeOrderDO.getUserId());
            orderItem.setAfterSaleStatus(TradeOrderItemAfterSaleStatusEnum.NONE.getStatus());
            orderItem.setCommentStatus(false);
            return orderItem;
        });
    }

    TradeOrderItemDO convert(TradePriceCalculateRespBO.OrderItem item);

    default ProductSkuUpdateStockReqDTO convert(List<TradeOrderItemDO> list) {
        List<ProductSkuUpdateStockReqDTO.Item> items = CollectionUtils.convertList(list, item ->
                new ProductSkuUpdateStockReqDTO.Item().setId(item.getSkuId()).setIncrCount(item.getCount()));
        return new ProductSkuUpdateStockReqDTO(items);
    }

    default ProductSkuUpdateStockReqDTO convertNegative(List<TradeOrderItemDO> list) {
        List<ProductSkuUpdateStockReqDTO.Item> items = CollectionUtils.convertList(list, item ->
                new ProductSkuUpdateStockReqDTO.Item().setId(item.getSkuId()).setIncrCount(-item.getCount()));
        return new ProductSkuUpdateStockReqDTO(items);
    }

    default PayOrderCreateReqDTO convert(TradeOrderDO order, List<TradeOrderItemDO> orderItems,
                                         TradeOrderProperties orderProperties) {
        PayOrderCreateReqDTO createReqDTO = new PayOrderCreateReqDTO()
                .setAppId(orderProperties.getAppId()).setUserIp(order.getUserIp());
        // 商户相关字段
        createReqDTO.setMerchantOrderId(String.valueOf(order.getId()));
        String subject = orderItems.get(0).getSpuName();
        subject = StrUtils.maxLength(subject, PayOrderCreateReqDTO.SUBJECT_MAX_LENGTH); // 避免超过 32 位
        createReqDTO.setSubject(subject);
        createReqDTO.setBody(subject); // TODO 芋艿：临时写死
        // 订单相关字段
        createReqDTO.setPrice(order.getPayPrice()).setExpireTime(addTime(orderProperties.getExpireTime()));
        return createReqDTO;
    }

    default PageResult<TradeOrderPageItemRespVO> convertPage(PageResult<TradeOrderDO> pageResult,
                                                             List<TradeOrderItemDO> orderItems,
                                                             Map<Long, MemberUserRespDTO> memberUserMap) {
        Map<Long, List<TradeOrderItemDO>> orderItemMap = convertMultiMap(orderItems, TradeOrderItemDO::getOrderId);
        // 转化 List
        List<TradeOrderPageItemRespVO> orderVOs = CollectionUtils.convertList(pageResult.getList(), order -> {
            List<TradeOrderItemDO> xOrderItems = orderItemMap.get(order.getId());
            TradeOrderPageItemRespVO orderVO = convert(order, xOrderItems);
            // 处理收货地址
            orderVO.setReceiverAreaName(AreaUtils.format(order.getReceiverAreaId()));
            // 增加用户信息
            orderVO.setUser(convertUser(memberUserMap.get(orderVO.getUserId())));
            // 增加推广人信息
            orderVO.setBrokerageUser(convertUser(memberUserMap.get(orderVO.getBrokerageUserId())));
            return orderVO;
        });
        return new PageResult<>(orderVOs, pageResult.getTotal());
    }

    MemberUserRespVO convertUser(MemberUserRespDTO memberUserRespDTO);

    TradeOrderPageItemRespVO convert(TradeOrderDO order, List<TradeOrderItemDO> items);

    ProductPropertyValueDetailRespVO convert(ProductPropertyValueDetailRespDTO bean);

    default TradeOrderDetailRespVO convert(TradeOrderDO order, List<TradeOrderItemDO> orderItems,
                                           List<TradeOrderLogDO> orderLogs,
                                           MemberUserRespDTO user, MemberUserRespDTO brokerageUser) {
        TradeOrderDetailRespVO orderVO = convert2(order, orderItems);
        // 处理收货地址
        orderVO.setReceiverAreaName(AreaUtils.format(order.getReceiverAreaId()));
        // 处理用户信息
        orderVO.setUser(convert(user));
        orderVO.setBrokerageUser(convert(brokerageUser));
        // 处理日志
        orderVO.setLogs(convertList03(orderLogs));
        return orderVO;
    }
    List<TradeOrderDetailRespVO.OrderLog> convertList03(List<TradeOrderLogDO> orderLogs);

    TradeOrderDetailRespVO convert2(TradeOrderDO order, List<TradeOrderItemDO> items);

    MemberUserRespVO convert(MemberUserRespDTO bean);

    default PageResult<AppTradeOrderPageItemRespVO> convertPage02(PageResult<TradeOrderDO> pageResult,
                                                                  List<TradeOrderItemDO> orderItems) {
        Map<Long, List<TradeOrderItemDO>> orderItemMap = convertMultiMap(orderItems, TradeOrderItemDO::getOrderId);
        // 转化 List
        List<AppTradeOrderPageItemRespVO> orderVOs = CollectionUtils.convertList(pageResult.getList(), order -> {
            List<TradeOrderItemDO> xOrderItems = orderItemMap.get(order.getId());
            return convert02(order, xOrderItems);
        });
        return new PageResult<>(orderVOs, pageResult.getTotal());
    }

    AppTradeOrderPageItemRespVO convert02(TradeOrderDO order, List<TradeOrderItemDO> items);

    AppProductPropertyValueDetailRespVO convert02(ProductPropertyValueDetailRespDTO bean);

    default AppTradeOrderDetailRespVO convert02(TradeOrderDO order, List<TradeOrderItemDO> orderItems,
                                                TradeOrderProperties tradeOrderProperties,
                                                DeliveryExpressDO express) {
        AppTradeOrderDetailRespVO orderVO = convert3(order, orderItems);
        orderVO.setPayExpireTime(addTime(tradeOrderProperties.getExpireTime()));
        if (StrUtil.isNotEmpty(order.getPayChannelCode())) {
            orderVO.setPayChannelName(DictFrameworkUtils.getDictDataLabel(DictTypeConstants.CHANNEL_CODE, order.getPayChannelCode()));
        }
        // 处理收货地址
        orderVO.setReceiverAreaName(AreaUtils.format(order.getReceiverAreaId()));
        if (express != null) {
            orderVO.setLogisticsId(express.getCode()).setLogisticsName(express.getName());
        }
        return orderVO;
    }

    AppTradeOrderDetailRespVO convert3(TradeOrderDO order, List<TradeOrderItemDO> items);

    AppTradeOrderItemRespVO convert03(TradeOrderItemDO bean);

    @Mappings({
            @Mapping(target = "skuId", source = "tradeOrderItemDO.skuId"),
            @Mapping(target = "orderId", source = "tradeOrderItemDO.orderId"),
            @Mapping(target = "orderItemId", source = "tradeOrderItemDO.id"),
            @Mapping(target = "descriptionScores", source = "createReqVO.descriptionScores"),
            @Mapping(target = "benefitScores", source = "createReqVO.benefitScores"),
            @Mapping(target = "content", source = "createReqVO.content"),
            @Mapping(target = "picUrls", source = "createReqVO.picUrls"),
            @Mapping(target = "anonymous", source = "createReqVO.anonymous"),
            @Mapping(target = "userId", source = "tradeOrderItemDO.userId")
    })
    ProductCommentCreateReqDTO convert04(AppTradeOrderItemCommentCreateReqVO createReqVO, TradeOrderItemDO tradeOrderItemDO);

    TradePriceCalculateReqBO convert(AppTradeOrderSettlementReqVO settlementReqVO);

    default TradePriceCalculateReqBO convert(Long userId, AppTradeOrderSettlementReqVO settlementReqVO,
                                             List<CartDO> cartList) {
        TradePriceCalculateReqBO reqBO = new TradePriceCalculateReqBO().setUserId(userId)
                .setItems(new ArrayList<>(settlementReqVO.getItems().size()))
                .setCouponId(settlementReqVO.getCouponId()).setPointStatus(settlementReqVO.getPointStatus())
                // 物流信息
                .setDeliveryType(settlementReqVO.getDeliveryType()).setAddressId(settlementReqVO.getAddressId())
                .setPickUpStoreId(settlementReqVO.getPickUpStoreId())
                // 各种活动
                .setSeckillActivityId(settlementReqVO.getSeckillActivityId())
                .setBargainRecordId(settlementReqVO.getBargainRecordId())
                .setCombinationActivityId(settlementReqVO.getCombinationActivityId())
                .setCombinationHeadId(settlementReqVO.getCombinationHeadId());
        // 商品项的构建
        Map<Long, CartDO> cartMap = convertMap(cartList, CartDO::getId);
        for (AppTradeOrderSettlementReqVO.Item item : settlementReqVO.getItems()) {
            // 情况一：skuId + count
            if (item.getSkuId() != null) {
                reqBO.getItems().add(new TradePriceCalculateReqBO.Item().setSkuId(item.getSkuId()).setCount(item.getCount())
                        .setSelected(true)); // true 的原因，下单一定选中
                continue;
            }
            // 情况二：cartId
            CartDO cart = cartMap.get(item.getCartId());
            if (cart == null) {
                continue;
            }
            reqBO.getItems().add(new TradePriceCalculateReqBO.Item().setSkuId(cart.getSkuId()).setCount(cart.getCount())
                    .setCartId(item.getCartId()).setSelected(true)); // true 的原因，下单一定选中
        }
        return reqBO;
    }

    default AppTradeOrderSettlementRespVO convert(TradePriceCalculateRespBO calculate, AddressRespDTO address) {
        AppTradeOrderSettlementRespVO respVO = convert0(calculate, address);
        if (address != null) {
            respVO.getAddress().setAreaName(AreaUtils.format(address.getAreaId()));
        }
        return respVO;
    }

    AppTradeOrderSettlementRespVO convert0(TradePriceCalculateRespBO calculate, AddressRespDTO address);

    List<AppOrderExpressTrackRespDTO> convertList02(List<ExpressTrackRespDTO> list);

    TradeOrderDO convert(TradeOrderUpdateAddressReqVO reqVO);

    TradeOrderDO convert(TradeOrderUpdatePriceReqVO reqVO);

    TradeOrderDO convert(TradeOrderRemarkReqVO reqVO);

    default BrokerageAddReqBO convert(MemberUserRespDTO user, TradeOrderItemDO item,
                                      ProductSpuRespDTO spu, ProductSkuRespDTO sku) {
        BrokerageAddReqBO bo = new BrokerageAddReqBO().setBizId(String.valueOf(item.getId())).setSourceUserId(item.getUserId())
                .setBasePrice(item.getPayPrice() * item.getCount())
                .setTitle(StrUtil.format("{}成功购买{}", user.getNickname(), item.getSpuName()))
                .setFirstFixedPrice(0).setSecondFixedPrice(0);
        if (spu.getSubCommissionType()==0) {
            bo.setFirstFixedPrice(sku.getFirstBrokeragePrice()).setSecondFixedPrice(sku.getSecondBrokeragePrice());
        }
        return bo;
    }

    @Named("convertList04")
    List<TradeOrderRespDTO> convertList04(List<TradeOrderDO> list);

    @Mappings({
            @Mapping(target = "activityId", source = "order.combinationActivityId"),
            @Mapping(target = "spuId", source = "item.spuId"),
            @Mapping(target = "skuId", source = "item.skuId"),
            @Mapping(target = "count", source = "item.count"),
            @Mapping(target = "orderId", source = "order.id"),
            @Mapping(target = "userId", source = "order.userId"),
            @Mapping(target = "headId", source = "order.combinationHeadId"),
            @Mapping(target = "combinationPrice", source = "item.payPrice"),
    })
    CombinationRecordCreateReqDTO convert(TradeOrderDO order, TradeOrderItemDO item);


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "createReqVO.couponId", target = "couponId"),
            @Mapping(target = "remark", ignore = true),
            @Mapping(source = "createReqVO.remark", target = "userRemark"),
            @Mapping(source = "address.name", target = "receiverName"),
            @Mapping(source = "address.mobile", target = "receiverMobile"),
            @Mapping(source = "address.areaId", target = "receiverAreaId"),
            @Mapping(source = "address.postCode", target = "receiverPostCode"),
            @Mapping(source = "address.detailAddress", target = "receiverDetailAddress"),
    })
    TradeOrderDO convert(Long userId, String userIp, AppTradeOrderCreateReqVO createReqVO,
                         PriceCalculateRespDTO.Order order, AddressRespDTO address);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "sku.spuId", target = "spuId"),
    })
    TradeOrderItemDO convert(PriceCalculateRespDTO.OrderItem orderItem, ProductSkuRespDTO sku);

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    TradeOrderDO convert(TradeOrderReceiverAddress address);

    AppTradeOrderCreateReqVO convert(TradeOrderCreateReqVO address);

    TradeOrderCreateReqVO convert(AppTradeOrderCreateReqVO address);

    default List<TradeOrderItemDO> convertList(TradeOrderDO tradeOrderDO,
                                               List<PriceCalculateRespDTO.OrderItem> orderItems, List<ProductSkuRespDTO> skus) {
        Map<Long, ProductSkuRespDTO> skuMap = convertMap(skus, ProductSkuRespDTO::getId);
        return CollectionUtils.convertList(orderItems, orderItem -> {
            TradeOrderItemDO tradeOrderItemDO = convert(orderItem, skuMap.get(orderItem.getSkuId()));
            tradeOrderItemDO.setOrderId(tradeOrderDO.getId());
            tradeOrderItemDO.setUserId(tradeOrderDO.getUserId());
            tradeOrderItemDO.setAfterSaleStatus(TradeOrderItemAfterSaleStatusEnum.NONE.getStatus()); // 退款信息
//            tradeOrderItemDO.setCommented(false);
            return tradeOrderItemDO;
        });
    }

    List<AppTradeOrderCreateReqVO.Item> convertItemList(List<TradeOrderCreateReqVO.Item> list);

    @Mapping(source = "userId" , target = "userId")
    PriceCalculateReqDTO convert(AppTradeOrderCreateReqVO createReqVO, Long userId);

    @Mappings({
            @Mapping(source = "skuId", target = "id"),
            @Mapping(source = "count", target = "incrCount"),
    })
    ProductSkuUpdateStockReqDTO.Item convert(TradeOrderItemDO bean);
    List<ProductSkuUpdateStockReqDTO.Item> convertList(List<TradeOrderItemDO> list);

    default PayOrderCreateReqDTO convert(TradeOrderDO tradeOrderDO, List<TradeOrderItemDO> tradeOrderItemDOs,
                                         List<ProductSpuRespDTO> spus, TradeOrderProperties tradeOrderProperties) {
        PayOrderCreateReqDTO createReqDTO = new PayOrderCreateReqDTO()
                .setBody("")
                .setAppId(tradeOrderProperties.getAppId()).setUserIp(tradeOrderDO.getUserIp());
        // 商户相关字段
        createReqDTO.setMerchantOrderId(String.valueOf(tradeOrderDO.getId()));
        String subject = spus.get(0).getName();
        if (spus.size() > 1) {
            subject += " 等多件";
        }
        createReqDTO.setSubject(subject);
        // 订单相关字段
        createReqDTO.setPrice(tradeOrderDO.getPayPrice()).setAmount(tradeOrderDO.getPayPrice()).setExpireTime(addTime(tradeOrderProperties.getExpireTime()));
        return createReqDTO;
    }

    default Set<Long> convertPropertyValueIds(List<TradeOrderItemDO> list) {
        if (CollUtil.isEmpty(list)) {
            return new HashSet<>();
        }
        return list.stream().filter(item -> item.getProperties() != null)
                .flatMap(p -> p.getProperties().stream()) // 遍历多个 Property 属性
                .map(TradeOrderItemDO.Property::getValueId) // 将每个 Property 转换成对应的 propertyId，最后形成集合
                .collect(Collectors.toSet());
    }

    default PageResult<TradeOrderPageItemRespVO> convertPage(PageResult<TradeOrderDO> pageResult, List<TradeOrderItemDO> orderItems,
                                                             List<ProductPropertyValueDetailRespDTO> propertyValueDetails) {
        Map<Long, List<TradeOrderItemDO>> orderItemMap = convertMultiMap(orderItems, TradeOrderItemDO::getOrderId);
        Map<Long, ProductPropertyValueDetailRespDTO> propertyValueDetailMap = convertMap(propertyValueDetails, ProductPropertyValueDetailRespDTO::getValueId);
        // 转化 List
        List<TradeOrderPageItemRespVO> orderVOs = CollectionUtils.convertList(pageResult.getList(), order -> {
            List<TradeOrderItemDO> xOrderItems = orderItemMap.get(order.getId());
            TradeOrderPageItemRespVO orderVO = convert(order, xOrderItems);
            if (CollUtil.isNotEmpty(xOrderItems)) {
                // 处理商品属性
                for (int i = 0; i < xOrderItems.size(); i++) {
                    List<TradeOrderItemDO.Property> properties = xOrderItems.get(i).getProperties();
                    if (CollUtil.isEmpty(properties)) {
                        continue;
                    }
                    TradeOrderPageItemRespVO.Item item = orderVO.getItems().get(i);
                    item.setProperties(new ArrayList<>(properties.size()));
                    // 遍历每个 properties，设置到 TradeOrderPageItemRespVO.Item 中
                    properties.forEach(property -> {
                        ProductPropertyValueDetailRespDTO propertyValueDetail = propertyValueDetailMap.get(property.getValueId());
                        if (propertyValueDetail == null) {
                            return;
                        }
                        item.getProperties().add(convert(propertyValueDetail));
                    });
                }
            }
            // 处理收货地址
            orderVO.setReceiverAreaName(AreaUtils.format(order.getReceiverAreaId()));
            return orderVO;
        });
        return new PageResult<>(orderVOs, pageResult.getTotal());
    }
    default List<TradeOrderPageItemRespVO> convertList(List<TradeOrderDO> list, List<TradeOrderItemDO> orderItems,
                                                       List<ProductPropertyValueDetailRespDTO> propertyValueDetails) {
        Map<Long, List<TradeOrderItemDO>> orderItemMap = convertMultiMap(orderItems, TradeOrderItemDO::getOrderId);
        Map<Long, ProductPropertyValueDetailRespDTO> propertyValueDetailMap = convertMap(propertyValueDetails, ProductPropertyValueDetailRespDTO::getValueId);
        // 转化 List
        List<TradeOrderPageItemRespVO> orderVOs = CollectionUtils.convertList(list, order -> {
            List<TradeOrderItemDO> xOrderItems = orderItemMap.get(order.getId());
            TradeOrderPageItemRespVO orderVO = convert(order, xOrderItems);
            if (CollUtil.isNotEmpty(xOrderItems)) {
                // 处理商品属性
                for (int i = 0; i < xOrderItems.size(); i++) {
                    List<TradeOrderItemDO.Property> properties = xOrderItems.get(i).getProperties();
                    if (CollUtil.isEmpty(properties)) {
                        continue;
                    }
                    TradeOrderPageItemRespVO.Item item = orderVO.getItems().get(i);
                    item.setProperties(new ArrayList<>(properties.size()));
                    // 遍历每个 properties，设置到 TradeOrderPageItemRespVO.Item 中
                    properties.forEach(property -> {
                        ProductPropertyValueDetailRespDTO propertyValueDetail = propertyValueDetailMap.get(property.getValueId());
                        if (propertyValueDetail == null) {
                            return;
                        }
                        item.getProperties().add(convert(propertyValueDetail));
                    });
                }
            }
            // 处理收货地址
            orderVO.setReceiverAreaName(AreaUtils.format(order.getReceiverAreaId()));
            return orderVO;
        });
        return orderVOs;
    }

    default TradeOrderDetailRespVO convert(TradeOrderDO order, List<TradeOrderItemDO> orderItems,
                                           List<ProductPropertyValueDetailRespDTO> propertyValueDetails, MemberUserRespDTO user, List<OrderGoodsGetDTO> orderGoodsList) {
        TradeOrderDetailRespVO orderVO = convert2(order, orderItems);
        // 处理商品属性
        Map<Long, ProductPropertyValueDetailRespDTO> propertyValueDetailMap = convertMap(propertyValueDetails, ProductPropertyValueDetailRespDTO::getValueId);
        for (int i = 0; i < orderItems.size(); i++) {
            TradeOrderDetailRespVO.Item item = orderVO.getItems().get(i);
            if (orderGoodsList != null) {
                Optional<OrderGoodsGetDTO> og = orderGoodsList.stream().filter((OrderGoodsGetDTO orderGoods) -> {
                    Long ogId = orderGoods.getId();
                    String coiId = item.getChannelOrderItemId();
                    return coiId.equals(ogId.toString());
                }).findFirst();
                if (og.isPresent()) {
                    item.setChannelOrderGoodsId(og.get().getRefOlId());
                    item.setChannelOrderGoodsSkuId(og.get().getRefSpuId());
                }
            }
            List<TradeOrderItemDO.Property> properties = orderItems.get(i).getProperties();
            if (CollUtil.isEmpty(properties)) {
                continue;
            }
            item.setProperties(new ArrayList<>(properties.size()));
            // 遍历每个 properties，设置到 TradeOrderPageItemRespVO.Item 中
            properties.forEach(property -> {
                ProductPropertyValueDetailRespDTO propertyValueDetail = propertyValueDetailMap.get(property.getValueId());
                if (propertyValueDetail == null) {
                    return;
                }
                item.getProperties().add(convert(propertyValueDetail));
            });
        }
        // 处理收货地址
        orderVO.setReceiverAreaName(AreaUtils.format(order.getReceiverAreaId()));
        // 处理用户信息
        orderVO.setUser(convert(user));
        return orderVO;
    }


    default PageResult<AppTradeOrderPageItemRespVO> convertPage02(PageResult<TradeOrderDO> pageResult, List<TradeOrderItemDO> orderItems,
                                                                  List<ProductPropertyValueDetailRespDTO> propertyValueDetails) {
        Map<Long, List<TradeOrderItemDO>> orderItemMap = convertMultiMap(orderItems, TradeOrderItemDO::getOrderId);
        Map<Long, ProductPropertyValueDetailRespDTO> propertyValueDetailMap = convertMap(propertyValueDetails, ProductPropertyValueDetailRespDTO::getValueId);
        // 转化 List
        List<AppTradeOrderPageItemRespVO> orderVOs = CollectionUtils.convertList(pageResult.getList(), order -> {
            List<TradeOrderItemDO> xOrderItems = orderItemMap.get(order.getId());
            AppTradeOrderPageItemRespVO orderVO = convert02(order, xOrderItems);
            if (CollUtil.isNotEmpty(xOrderItems)) {
                // 处理商品属性
                for (int i = 0; i < xOrderItems.size(); i++) {
                    List<TradeOrderItemDO.Property> properties = xOrderItems.get(i).getProperties();
                    if (CollUtil.isEmpty(properties)) {
                        continue;
                    }
                    AppTradeOrderPageItemRespVO.Item item = orderVO.getItems().get(i);
                    item.setProperties(new ArrayList<>(properties.size()));
                    // 遍历每个 properties，设置到 TradeOrderPageItemRespVO.Item 中
                    properties.forEach(property -> {
                        ProductPropertyValueDetailRespDTO propertyValueDetail = propertyValueDetailMap.get(property.getValueId());
                        if (propertyValueDetail == null) {
                            return;
                        }
                        item.getProperties().add(convert02(propertyValueDetail));
                    });
                }
            }
            return orderVO;
        });
        return new PageResult<>(orderVOs, pageResult.getTotal());
    }

    default AppTradeOrderDetailRespVO convert02(TradeOrderDO order, List<TradeOrderItemDO> orderItems,
                                                List<ProductPropertyValueDetailRespDTO> propertyValueDetails) {
        AppTradeOrderDetailRespVO orderVO = convert3(order, orderItems);
        // 处理商品属性
        Map<Long, ProductPropertyValueDetailRespDTO> propertyValueDetailMap = convertMap(propertyValueDetails, ProductPropertyValueDetailRespDTO::getValueId);
        for (int i = 0; i < orderItems.size(); i++) {
            List<TradeOrderItemDO.Property> properties = orderItems.get(i).getProperties();
            if (CollUtil.isEmpty(properties)) {
                continue;
            }
            AppTradeOrderDetailRespVO.Item item = orderVO.getItems().get(i);
            item.setProperties(new ArrayList<>(properties.size()));
            // 遍历每个 properties，设置到 TradeOrderPageItemRespVO.Item 中
            properties.forEach(property -> {
                ProductPropertyValueDetailRespDTO propertyValueDetail = propertyValueDetailMap.get(property.getValueId());
                if (propertyValueDetail == null) {
                    return;
                }
                item.getProperties().add(convert02(propertyValueDetail));
            });
        }
        // 处理收货地址
        orderVO.setReceiverAreaName(AreaUtils.format(order.getReceiverAreaId()));
        return orderVO;
    }

    default PayOrderCreateReqDTO convert1(TradeOrderDO tradeOrderDO, List<TradeOrderItemDO> tradeOrderItemDOs,
                                         List<ProductSpuRespDTO> spus, TradeOrderProperties tradeOrderProperties) {
        PayOrderCreateReqDTO createReqDTO = new PayOrderCreateReqDTO()
                .setBody("")
                .setAppId(tradeOrderProperties.getAppId()).setUserIp(tradeOrderDO.getUserIp());
        // 商户相关字段
        createReqDTO.setMerchantOrderId(String.valueOf(tradeOrderDO.getId()));
        String subject = spus.get(0).getName();
        if (spus.size() > 1) {
            subject += " 等多件";
        }
        createReqDTO.setSubject(subject);
        // 订单相关字段
        createReqDTO.setAmount(tradeOrderDO.getPayPrice()).setExpireTime(addTime(tradeOrderProperties.getExpireTime()));
        return createReqDTO;
    }

    List<TradeOrderExcelVO> convertList05(List<TradeOrderDO> list);

    TradeOrderDO convert(TradeOrderUpdateDeliveryTypeReqVO reqVO);
}
