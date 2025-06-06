package com.dofast.module.trade.service.order;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.dofast.framework.common.core.KeyValue;
import com.dofast.framework.common.enums.UserTypeEnum;
import com.dofast.framework.common.util.date.DateUtils;
import com.dofast.framework.common.util.json.JsonUtils;
import com.dofast.framework.common.util.number.MoneyUtils;
import com.dofast.module.finance.controller.admin.cash.vo.CashTradeCreateReqVO;
import com.dofast.module.finance.service.cash.CashTradeService;
import com.dofast.module.member.api.address.AddressApi;
import com.dofast.module.member.api.address.dto.AddressRespDTO;
import com.dofast.module.pay.api.order.PayOrderApi;
import com.dofast.module.pay.api.order.dto.PayOrderCreateReqDTO;
import com.dofast.module.pay.api.order.dto.PayOrderRespDTO;
import com.dofast.module.pay.enums.order.PayOrderStatusEnum;
import com.dofast.module.product.api.comment.ProductCommentApi;
import com.dofast.module.product.api.comment.dto.ProductCommentCreateReqDTO;
import com.dofast.module.system.api.user.AdminUserApi;
import com.dofast.module.trade.controller.admin.order.vo.TradeOrderDeliveryReqVO;
import com.dofast.module.trade.controller.admin.order.vo.TradeOrderRemarkReqVO;
import com.dofast.module.trade.controller.admin.order.vo.TradeOrderUpdateAddressReqVO;
import com.dofast.module.trade.controller.admin.order.vo.TradeOrderUpdatePriceReqVO;
import com.dofast.module.trade.controller.app.order.vo.AppTradeOrderCreateReqVO;
import com.dofast.module.trade.controller.app.order.vo.AppTradeOrderSettlementReqVO;
import com.dofast.module.trade.controller.app.order.vo.AppTradeOrderSettlementRespVO;
import com.dofast.module.trade.controller.app.order.vo.item.AppTradeOrderItemCommentCreateReqVO;
import com.dofast.module.trade.convert.order.TradeOrderConvert;
import com.dofast.module.trade.dal.dataobject.cart.CartDO;
import com.dofast.module.trade.dal.dataobject.delivery.DeliveryExpressDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderItemDO;
import com.dofast.module.trade.dal.mysql.order.TradeOrderItemMapper;
import com.dofast.module.trade.dal.mysql.order.TradeOrderMapper;
import com.dofast.module.trade.dal.redis.no.TradeNoRedisDAO;
import com.dofast.module.trade.enums.delivery.DeliveryTypeEnum;
import com.dofast.module.trade.enums.order.*;
import com.dofast.module.trade.framework.config.TradeOrderProperties;
import com.dofast.module.trade.framework.order.core.annotations.TradeOrderLog;
import com.dofast.module.trade.framework.order.core.utils.TradeOrderLogUtils;
import com.dofast.module.trade.service.cart.CartService;
import com.dofast.module.trade.service.delivery.DeliveryExpressService;
import com.dofast.module.trade.service.message.TradeMessageService;
import com.dofast.module.trade.service.message.bo.TradeOrderMessageWhenDeliveryOrderReqBO;
import com.dofast.module.trade.service.order.handler.TradeOrderHandler;
import com.dofast.module.trade.service.price.TradePriceService;
import com.dofast.module.trade.service.price.bo.TradePriceCalculateReqBO;
import com.dofast.module.trade.service.price.bo.TradePriceCalculateRespBO;
import com.dofast.module.trade.service.price.calculator.TradePriceCalculatorHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.util.collection.CollectionUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.minusTime;
import static com.dofast.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static com.dofast.module.trade.enums.ErrorCodeConstants.*;

/**
 * 交易订单【写】Service 实现类
 *
 * @author LeeYan9
 * @since 2022-08-26
 */
@Service
@Slf4j
public class TradeOrderUpdateServiceImpl implements TradeOrderUpdateService {

    @Resource
    private TradeOrderMapper tradeOrderMapper;
    @Resource
    private TradeOrderItemMapper tradeOrderItemMapper;
    @Resource
    private TradeNoRedisDAO tradeNoRedisDAO;

    @Resource
    private List<TradeOrderHandler> tradeOrderHandlers;

    @Resource
    private CartService cartService;
    @Resource
    private TradePriceService tradePriceService;
    @Resource
    private DeliveryExpressService deliveryExpressService;
    @Resource
    private TradeMessageService tradeMessageService;

    @Resource
    private PayOrderApi payOrderApi;
    @Resource
    private AddressApi addressApi;
    @Resource
    private ProductCommentApi productCommentApi;

    @Resource
    private TradeOrderProperties tradeOrderProperties;

    @Resource
    private CashTradeService cashTradeService;

    @Resource
    private AdminUserApi userApi;
    // ===== Order =====

    @Override
    public AppTradeOrderSettlementRespVO settlementOrder(Long userId, AppTradeOrderSettlementReqVO settlementReqVO) {
        // 1. 获得收货地址
        AddressRespDTO address = getAddress(userId, settlementReqVO.getAddressId());
        if (address != null) {
            settlementReqVO.setAddressId(address.getId());
        }

        // 2. 计算价格
        TradePriceCalculateRespBO calculateRespBO = calculatePrice(userId, settlementReqVO);

        // 3. 拼接返回
        return TradeOrderConvert.INSTANCE.convert(calculateRespBO, address);
    }

    /**
     * 获得用户地址
     *
     * @param userId    用户编号
     * @param addressId 地址编号
     * @return 地址
     */
    private AddressRespDTO getAddress(Long userId, Long addressId) {
        if (addressId != null) {
            return addressApi.getAddress(addressId, userId);
        }
        return addressApi.getDefaultAddress(userId);
    }

    /**
     * 计算订单价格
     *
     * @param userId          用户编号
     * @param settlementReqVO 结算信息
     * @return 订单价格
     */
    private TradePriceCalculateRespBO calculatePrice(Long userId, AppTradeOrderSettlementReqVO settlementReqVO) {
        // 1. 如果来自购物车，则获得购物车的商品
        List<CartDO> cartList = cartService.getCartList(userId,
                convertSet(settlementReqVO.getItems(), AppTradeOrderSettlementReqVO.Item::getCartId));

        // 2. 计算价格
        TradePriceCalculateReqBO calculateReqBO = TradeOrderConvert.INSTANCE.convert(userId, settlementReqVO, cartList);
        calculateReqBO.getItems().forEach(item -> Assert.isTrue(item.getSelected(), // 防御性编程，保证都是选中的
                "商品({}) 未设置为选中", item.getSkuId()));
        return tradePriceService.calculatePrice(calculateReqBO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.MEMBER_CREATE)
    public TradeOrderDO createOrder(Long userId, String userIp, AppTradeOrderCreateReqVO createReqVO, Integer terminal) {
        // 1.1 价格计算
        TradePriceCalculateRespBO calculateRespBO = calculatePrice(userId, createReqVO);
        // 1.2 构建订单
        TradeOrderDO order = buildTradeOrder(userId, userIp, createReqVO, calculateRespBO, terminal);
        List<TradeOrderItemDO> orderItems = buildTradeOrderItems(order, calculateRespBO);

        // 2. 订单创建前的逻辑
        tradeOrderHandlers.forEach(handler -> handler.beforeOrderCreate(order, orderItems));

        // 3. 保存订单
        tradeOrderMapper.insert(order);
        orderItems.forEach(orderItem -> orderItem.setOrderId(order.getId()));
        tradeOrderItemMapper.insertBatch(orderItems);

        // 4. 订单创建后的逻辑
        afterCreateTradeOrder(order, orderItems, createReqVO);
        return order;
    }

    private TradeOrderDO buildTradeOrder(Long userId, String clientIp, AppTradeOrderCreateReqVO createReqVO,
                                         TradePriceCalculateRespBO calculateRespBO, Integer terminal) {
        TradeOrderDO order = TradeOrderConvert.INSTANCE.convert(userId, clientIp, createReqVO, calculateRespBO);
        order.setType(calculateRespBO.getType());
        order.setNo(tradeNoRedisDAO.generate(TradeNoRedisDAO.TRADE_ORDER_NO_PREFIX));
        order.setStatus(TradeOrderStatusEnum.UNPAID.getStatus());
        order.setRefundStatus(TradeOrderRefundStatusEnum.NONE.getStatus());
        order.setProductCount(getSumValue(calculateRespBO.getItems(), TradePriceCalculateRespBO.OrderItem::getCount, Integer::sum));
        order.setTerminal(terminal);
        // 支付 + 退款信息
        order.setAdjustPrice(0).setPayStatus(false);
        order.setRefundStatus(TradeOrderRefundStatusEnum.NONE.getStatus()).setRefundPrice(0);
        // 物流信息
        order.setDeliveryType(createReqVO.getDeliveryType());
        if (Objects.equals(createReqVO.getDeliveryType(), DeliveryTypeEnum.EXPRESS.getType())) {
            AddressRespDTO address = addressApi.getAddress(createReqVO.getAddressId(), userId);
            Assert.notNull(address, "地址({}) 不能为空", createReqVO.getAddressId()); // 价格计算时，已经计算
            order.setReceiverName(address.getName()).setReceiverMobile(address.getMobile())
                    .setReceiverAreaId(address.getAreaId()).setReceiverDetailAddress(address.getDetailAddress());
        } else if (Objects.equals(createReqVO.getDeliveryType(), DeliveryTypeEnum.PICK_UP.getType())) {
            order.setReceiverName(createReqVO.getReceiverName()).setReceiverMobile(createReqVO.getReceiverMobile());
            order.setPickUpVerifyCode(RandomUtil.randomNumbers(8)); // 随机一个核销码，长度为 8 位
        }
        return order;
    }

    private List<TradeOrderItemDO> buildTradeOrderItems(TradeOrderDO tradeOrderDO,
                                                        TradePriceCalculateRespBO calculateRespBO) {
        return TradeOrderConvert.INSTANCE.convertList(tradeOrderDO, calculateRespBO);
    }

    /**
     * 订单创建后，执行后置逻辑
     * <p>
     * 例如说：优惠劵的扣减、积分的扣减、支付单的创建等等
     *
     * @param order       订单
     * @param orderItems  订单项
     * @param createReqVO 创建订单请求
     */
    private void afterCreateTradeOrder(TradeOrderDO order, List<TradeOrderItemDO> orderItems,
                                       AppTradeOrderCreateReqVO createReqVO) {
        // 1. 执行订单创建后置处理器
        tradeOrderHandlers.forEach(handler -> handler.afterOrderCreate(order, orderItems));
        List<AppTradeOrderCreateReqVO.Item> items = createReqVO.getItems();
        Set<Long> cartIds=new HashSet<>();
        for (AppTradeOrderCreateReqVO.Item item : items) {
            cartIds.add(item.getSkuId());
        }
        // 2. 删除购物车商品
        if (CollUtil.isNotEmpty(cartIds)) {
            cartService.deleteCart(order.getUserId(), cartIds);
        }

        // 3. 生成预支付
        createPayOrder(order, orderItems);

        // 4. 插入订单日志
        TradeOrderLogUtils.setOrderInfo(order.getId(), null, order.getStatus());
    }


    private void createPayOrder(TradeOrderDO order, List<TradeOrderItemDO> orderItems) {
        // 创建支付单，用于后续的支付
        PayOrderCreateReqDTO payOrderCreateReqDTO = TradeOrderConvert.INSTANCE.convert(
                order, orderItems, tradeOrderProperties);
        Long payOrderId = payOrderApi.createOrder(payOrderCreateReqDTO);

        // 更新到交易单上
        tradeOrderMapper.updateById(new TradeOrderDO().setId(order.getId()).setPayOrderId(payOrderId));
        order.setPayOrderId(payOrderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.MEMBER_PAY)
    public void updateOrderPaid(Long id, Long payOrderId) {
        // 1. 校验并获得交易订单（可支付）
        KeyValue<TradeOrderDO, PayOrderRespDTO> orderResult = validateOrderPayable(id, payOrderId);
        TradeOrderDO order = orderResult.getKey();
        PayOrderRespDTO payOrder = orderResult.getValue();

        // 2. 更新 TradeOrderDO 状态为已支付，等待发货
        int updateCount = tradeOrderMapper.updateByIdAndStatus(id, order.getStatus(),
                new TradeOrderDO().setStatus(TradeOrderStatusEnum.UNDELIVERED.getStatus()).setPayStatus(true)
                        .setPayTime(LocalDateTime.now()).setPayChannelCode(payOrder.getChannelCode()));
        if (updateCount == 0) {
            throw exception(ORDER_UPDATE_PAID_STATUS_NOT_UNPAID);
        }

        // 3. 执行 TradeOrderHandler 的后置处理
        List<TradeOrderItemDO> orderItems = tradeOrderItemMapper.selectListByOrderId(id);
        tradeOrderHandlers.forEach(handler -> handler.afterPayOrder(order, orderItems));

        // 4. 记录订单日志
        TradeOrderLogUtils.setOrderInfo(order.getId(), order.getStatus(), TradeOrderStatusEnum.UNDELIVERED.getStatus());
        TradeOrderLogUtils.setUserInfo(order.getUserId(), UserTypeEnum.MEMBER.getValue());
    }

    /**
     * 校验交易订单满足被支付的条件
     * <p>
     * 1. 交易订单未支付
     * 2. 支付单已支付
     *
     * @param id         交易订单编号
     * @param payOrderId 支付订单编号
     * @return 交易订单
     */
    private KeyValue<TradeOrderDO, PayOrderRespDTO> validateOrderPayable(Long id, Long payOrderId) {
        // 校验订单是否存在
        TradeOrderDO order = validateOrderExists(id);
        // 校验订单未支付
        if (!TradeOrderStatusEnum.isUnpaid(order.getStatus()) || order.getPayStatus()) {
            log.error("[validateOrderPaid][order({}) 不处于待支付状态，请进行处理！order 数据是：{}]",
                    id, JsonUtils.toJsonString(order));
            throw exception(ORDER_UPDATE_PAID_STATUS_NOT_UNPAID);
        }
        // 校验支付订单匹配
        if (ObjectUtil.notEqual(order.getPayOrderId(), payOrderId)) { // 支付单号
            log.error("[validateOrderPaid][order({}) 支付单不匹配({})，请进行处理！order 数据是：{}]",
                    id, payOrderId, JsonUtils.toJsonString(order));
            throw exception(ORDER_UPDATE_PAID_FAIL_PAY_ORDER_ID_ERROR);
        }

        // 校验支付单是否存在
        PayOrderRespDTO payOrder = payOrderApi.getOrder(payOrderId);
        if (payOrder == null) {
            log.error("[validateOrderPaid][order({}) payOrder({}) 不存在，请进行处理！]", id, payOrderId);
            throw exception(ORDER_NOT_FOUND);
        }
        // 校验支付单已支付
        if (!PayOrderStatusEnum.isSuccess(payOrder.getStatus())) {
            log.error("[validateOrderPaid][order({}) payOrder({}) 未支付，请进行处理！payOrder 数据是：{}]",
                    id, payOrderId, JsonUtils.toJsonString(payOrder));
            throw exception(ORDER_UPDATE_PAID_FAIL_PAY_ORDER_STATUS_NOT_SUCCESS);
        }
        // 校验支付金额一致
        if (ObjectUtil.notEqual(payOrder.getPrice(), order.getPayPrice())) {
            log.error("[validateOrderPaid][order({}) payOrder({}) 支付金额不匹配，请进行处理！order 数据是：{}，payOrder 数据是：{}]",
                    id, payOrderId, JsonUtils.toJsonString(order), JsonUtils.toJsonString(payOrder));
            throw exception(ORDER_UPDATE_PAID_FAIL_PAY_PRICE_NOT_MATCH);
        }
        // 校验支付订单匹配（二次）
        if (ObjectUtil.notEqual(payOrder.getMerchantOrderId(), id.toString())) {
            log.error("[validateOrderPaid][order({}) 支付单不匹配({})，请进行处理！payOrder 数据是：{}]",
                    id, payOrderId, JsonUtils.toJsonString(payOrder));
            throw exception(ORDER_UPDATE_PAID_FAIL_PAY_ORDER_ID_ERROR);
        }
        return new KeyValue<>(order, payOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.ADMIN_DELIVERY)
    public void deliveryOrder(TradeOrderDeliveryReqVO deliveryReqVO) {
        // 1.1 校验并获得交易订单（可发货）
        TradeOrderDO order = validateOrderDeliverable(deliveryReqVO.getId());
        // 1.2 校验 deliveryType 是否为快递，是快递才可以发货
        /*if (ObjectUtil.notEqual(order.getDeliveryType(), DeliveryTypeEnum.EXPRESS.getType())) {
            throw exception(ORDER_DELIVERY_FAIL_DELIVERY_TYPE_NOT_EXPRESS);
        }*/

        // 2. 更新订单为已发货
        TradeOrderDO updateOrderObj = new TradeOrderDO();
        // 2.1 快递发货
        DeliveryExpressDO express = null;
        if (ObjectUtil.notEqual(deliveryReqVO.getLogisticsId(), TradeOrderDO.LOGISTICS_ID_NULL)) {
            express = deliveryExpressService.validateDeliveryExpress(deliveryReqVO.getLogisticsId());
            updateOrderObj.setLogisticsId(deliveryReqVO.getLogisticsId()).setLogisticsNo(deliveryReqVO.getLogisticsNo());
        } else {
            // 2.2 无需发货
            updateOrderObj.setLogisticsId("").setLogisticsNo("");
        }
        // 执行更新
        updateOrderObj.setStatus(TradeOrderStatusEnum.DELIVERED.getStatus()).setDeliveryTime(LocalDateTime.now());
        int updateCount = tradeOrderMapper.updateByIdAndStatus(order.getId(), order.getStatus(), updateOrderObj.setDeliveryTime(LocalDateTime.now()).setDeliveryUser(Integer.valueOf(String.valueOf(getLoginUserId()))));
        if (updateCount == 0) {
            throw exception(ORDER_DELIVERY_FAIL_STATUS_NOT_UNDELIVERED);
        }

        //创建应收款入账记录
        CashTradeCreateReqVO cashTradeCreateReqVO = new CashTradeCreateReqVO();
        cashTradeCreateReqVO.setDepositor(order.getDepositor());
        cashTradeCreateReqVO.setParent(0L);
//            cashTradeCreateReqVO.setProduct() 商品ID
        cashTradeCreateReqVO.setTrader(Long.valueOf(order.getSystemUserId())); //交易人
        cashTradeCreateReqVO.setOrderId(order.getId());//订单ID
        cashTradeCreateReqVO.setContract(Long.valueOf(order.getNo())); //合同编号
//            cashTradeCreateReqVO.setRelatedType() //关联类型
//            cashTradeCreateReqVO.setRelatedId()  //关联ID
//            cashTradeCreateReqVO.setProject()  //项目
        cashTradeCreateReqVO.setDept(userApi.getUser(Long.valueOf(order.getCreator())).getDeptId());//部门ID
        cashTradeCreateReqVO.setType("in");
        cashTradeCreateReqVO.setMoney(BigDecimal.valueOf(order.getPayPrice()));
        cashTradeCreateReqVO.setCurrency(String.valueOf(0));
        cashTradeCreateReqVO.setDate(DateUtils.of(new Date()));
        cashTradeCreateReqVO.setTime(LocalTime.now());
        cashTradeCreateReqVO.setDeadline(DateUtils.of(new Date(System.currentTimeMillis()+1000*60*60*12*30)));
        cashTradeCreateReqVO.setHandlers(order.getSystemUserName());
        cashTradeCreateReqVO.setCategory(String.valueOf(0));
        cashTradeService.createCashTrade(cashTradeCreateReqVO);


        // 3. 记录订单日志
        TradeOrderLogUtils.setOrderInfo(order.getId(), order.getStatus(), TradeOrderStatusEnum.DELIVERED.getStatus(),
                MapUtil.<String, Object>builder().put("expressName", express != null ? express.getName() : "")
                        .put("logisticsNo", express != null ? deliveryReqVO.getLogisticsNo() : "").build());

        // 4. 发送站内信
        tradeMessageService.sendMessageWhenDeliveryOrder(new TradeOrderMessageWhenDeliveryOrderReqBO()
                .setOrderId(order.getId()).setUserId(order.getUserId()).setMessage(null));
    }

    /**
     * 校验交易订单满足被发货的条件
     * <p>
     * 1. 交易订单未发货
     *
     * @param id 交易订单编号
     * @return 交易订单
     */
    private TradeOrderDO validateOrderDeliverable(Long id) {
        TradeOrderDO order = validateOrderExists(id);
        // 1. 校验订单是否未发货
        if (ObjectUtil.notEqual(TradeOrderRefundStatusEnum.NONE.getStatus(), order.getRefundStatus())) {
            throw exception(ORDER_DELIVERY_FAIL_REFUND_STATUS_NOT_NONE);
        }

        // 2. 执行 TradeOrderHandler 前置处理
        tradeOrderHandlers.forEach(handler -> handler.beforeDeliveryOrder(order));
        return order;
    }

    @NotNull
    private TradeOrderDO validateOrderExists(Long id) {
        // 校验订单是否存在
        TradeOrderDO order = tradeOrderMapper.selectById(id);
        if (order == null) {
            throw exception(ORDER_NOT_FOUND);
        }
        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.MEMBER_RECEIVE)
    public void receiveOrderByMember(Long userId, Long id) {
        // 校验并获得交易订单（可收货）
        TradeOrderDO order = validateOrderReceivable(userId, id);

        // 收货订单
        receiveOrder0(order);
    }

    @Override
    public int receiveOrderBySystem() {
        // 1. 查询过期的待支付订单
        LocalDateTime expireTime = minusTime(tradeOrderProperties.getReceiveExpireTime());
        List<TradeOrderDO> orders = tradeOrderMapper.selectListByStatusAndDeliveryTimeLt(
                TradeOrderStatusEnum.DELIVERED.getStatus(), expireTime);
        if (CollUtil.isEmpty(orders)) {
            return 0;
        }

        // 2. 遍历执行，逐个取消
        int count = 0;
        for (TradeOrderDO order : orders) {
            try {
                getSelf().receiveOrderBySystem(order);
                count++;
            } catch (Throwable e) {
                log.error("[receiveOrderBySystem][order({}) 自动收货订单异常]", order.getId(), e);
            }
        }
        return count;
    }

    /**
     * 自动收货单个订单
     *
     * @param order 订单
     */
    @Transactional(rollbackFor = Exception.class)
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.SYSTEM_RECEIVE)
    public void receiveOrderBySystem(TradeOrderDO order) {
        receiveOrder0(order);
    }

    /**
     * 收货订单的核心实现
     *
     * @param order 订单
     */
    private void receiveOrder0(TradeOrderDO order) {
        // 更新 TradeOrderDO 状态为已完成
        int updateCount = tradeOrderMapper.updateByIdAndStatus(order.getId(), order.getStatus(),
                new TradeOrderDO().setStatus(TradeOrderStatusEnum.COMPLETED.getStatus()).setReceiveTime(LocalDateTime.now()));
        if (updateCount == 0) {
            throw exception(ORDER_RECEIVE_FAIL_STATUS_NOT_DELIVERED);
        }

        // 插入订单日志
        TradeOrderLogUtils.setOrderInfo(order.getId(), order.getStatus(), TradeOrderStatusEnum.COMPLETED.getStatus());
    }

    /**
     * 校验交易订单满足可售货的条件
     * <p>
     * 1. 交易订单待收货
     *
     * @param userId 用户编号
     * @param id     交易订单编号
     * @return 交易订单
     */
    private TradeOrderDO validateOrderReceivable(Long userId, Long id) {
        // 校验订单是否存在
        TradeOrderDO order = tradeOrderMapper.selectByIdAndUserId(id, userId);
        if (order == null) {
            throw exception(ORDER_NOT_FOUND);
        }
        // 校验订单是否是待收货状态
        if (!TradeOrderStatusEnum.isDelivered(order.getStatus())) {
            throw exception(ORDER_RECEIVE_FAIL_STATUS_NOT_DELIVERED);
        }
        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.MEMBER_CANCEL)
    public void cancelOrderByMember(Long userId, Long id) {
        // 1.1 校验存在
        TradeOrderDO order = tradeOrderMapper.selectOrderByIdAndUserId(id, userId);
        if (order == null) {
            throw exception(ORDER_NOT_FOUND);
        }
        // 1.2 校验状态
        if (ObjectUtil.notEqual(order.getStatus(), TradeOrderStatusEnum.UNPAID.getStatus())) {
            throw exception(ORDER_CANCEL_FAIL_STATUS_NOT_UNPAID);
        }

        // 2. 取消订单
        cancelOrder0(order, TradeOrderCancelTypeEnum.MEMBER_CANCEL);
    }

    @Override
    public int cancelOrderBySystem() {
        // 1. 查询过期的待支付订单
        LocalDateTime expireTime = minusTime(tradeOrderProperties.getExpireTime());
        List<TradeOrderDO> orders = tradeOrderMapper.selectListByStatusAndCreateTimeLt(
                TradeOrderStatusEnum.UNPAID.getStatus(), expireTime);
        if (CollUtil.isEmpty(orders)) {
            return 0;
        }

        // 2. 遍历执行，逐个取消
        int count = 0;
        for (TradeOrderDO order : orders) {
            try {
                getSelf().cancelOrderBySystem(order);
                count++;
            } catch (Throwable e) {
                log.error("[cancelOrderBySystem][order({}) 过期订单异常]", order.getId(), e);
            }
        }
        return count;
    }

    /**
     * 自动取消单个订单
     *
     * @param order 订单
     */
    @Transactional(rollbackFor = Exception.class)
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.SYSTEM_CANCEL)
    public void cancelOrderBySystem(TradeOrderDO order) {
        cancelOrder0(order, TradeOrderCancelTypeEnum.PAY_TIMEOUT);
    }

    /**
     * 取消订单的核心实现
     *
     * @param order      订单
     * @param cancelType 取消类型
     */
    private void cancelOrder0(TradeOrderDO order, TradeOrderCancelTypeEnum cancelType) {
        // 1. 更新 TradeOrderDO 状态为已取消
        int updateCount = tradeOrderMapper.updateByIdAndStatus(order.getId(), order.getStatus(),
                new TradeOrderDO().setStatus(TradeOrderStatusEnum.CANCELED.getStatus())
                        .setCancelType(cancelType.getType()).setCancelTime(LocalDateTime.now()));
        if (updateCount == 0) {
            throw exception(ORDER_CANCEL_FAIL_STATUS_NOT_UNPAID);
        }

        // 2. 执行 TradeOrderHandler 的后置处理
        List<TradeOrderItemDO> orderItems = tradeOrderItemMapper.selectListByOrderId(order.getId());
        tradeOrderHandlers.forEach(handler -> handler.afterCancelOrder(order, orderItems));

        // 3. 增加订单日志
        TradeOrderLogUtils.setOrderInfo(order.getId(), order.getStatus(), TradeOrderStatusEnum.CANCELED.getStatus());
    }

    /**
     * 如果金额全部被退款，则取消订单
     * 如果还有未被退款的金额，则无需取消订单
     *
     * @param order       订单
     * @param refundPrice 退款金额
     */
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.ADMIN_CANCEL_AFTER_SALE)
    public void cancelOrderByAfterSale(TradeOrderDO order, Integer refundPrice) {
        // 1. 更新订单
        if (refundPrice < order.getPayPrice()) {
            return;
        }
        tradeOrderMapper.updateById(new TradeOrderDO().setId(order.getId())
                .setStatus(TradeOrderStatusEnum.CANCELED.getStatus())
                .setCancelType(TradeOrderCancelTypeEnum.AFTER_SALE_CLOSE.getType()).setCancelTime(LocalDateTime.now()));

        // 2. 执行 TradeOrderHandler 的后置处理
        List<TradeOrderItemDO> orderItems = tradeOrderItemMapper.selectListByOrderId(order.getId());
        tradeOrderHandlers.forEach(handler -> handler.afterCancelOrder(order, orderItems));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.MEMBER_DELETE)
    public void deleteOrder(Long userId, Long id) {
        // 1.1 校验存在
        TradeOrderDO order = tradeOrderMapper.selectOrderByIdAndUserId(id, userId);
        if (order == null) {
            throw exception(ORDER_NOT_FOUND);
        }
        // 1.2 校验状态
        if (ObjectUtil.notEqual(order.getStatus(), TradeOrderStatusEnum.CANCELED.getStatus())) {
            throw exception(ORDER_DELETE_FAIL_STATUS_NOT_CANCEL);
        }
        // 2. 删除订单
        tradeOrderMapper.deleteById(id);

        // 3. 记录日志
        TradeOrderLogUtils.setOrderInfo(order.getId(), order.getStatus(), order.getStatus());
    }



    @Override
    public void updateOrderRemark(TradeOrderRemarkReqVO reqVO) {
        // 校验并获得交易订单
        validateOrderExists(reqVO.getId());

        // 更新
        TradeOrderDO order = TradeOrderConvert.INSTANCE.convert(reqVO);
        tradeOrderMapper.updateById(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.ADMIN_UPDATE_PRICE)
    public void updateOrderPrice(TradeOrderUpdatePriceReqVO reqVO) {
        // 1.1 校验交易订单
        TradeOrderDO order = validateOrderExists(reqVO.getId());
        if (order.getPayStatus()) {
            throw exception(ORDER_UPDATE_PRICE_FAIL_PAID);
        }
        // 1.2 校验调价金额是否变化
        if (order.getAdjustPrice() > 0) {
            throw exception(ORDER_UPDATE_PRICE_FAIL_ALREADY);
        }
        // 1.3 支付价格不能为 0
        int newPayPrice = order.getPayPrice() + order.getAdjustPrice();
        if (newPayPrice <= 0) {
            throw exception(ORDER_UPDATE_PRICE_FAIL_PRICE_ERROR);
        }

        // 2. 更新订单
        tradeOrderMapper.updateById(new TradeOrderDO().setId(order.getId())
                .setAdjustPrice(reqVO.getAdjustPrice()).setPayPrice(newPayPrice));

        // 3. 更新 TradeOrderItem，需要做 adjustPrice 的分摊
        List<TradeOrderItemDO> orderOrderItems = tradeOrderItemMapper.selectListByOrderId(order.getId());
        List<Integer> dividePrices = TradePriceCalculatorHelper.dividePrice2(orderOrderItems, newPayPrice);
        List<TradeOrderItemDO> updateItems = new ArrayList<>();
        for (int i = 0; i < orderOrderItems.size(); i++) {
            TradeOrderItemDO item = orderOrderItems.get(i);
            updateItems.add(new TradeOrderItemDO().setId(item.getId()).setAdjustPrice(dividePrices.get(i))
                    .setPayPrice(item.getPayPrice() + dividePrices.get(i)));
        }
        tradeOrderItemMapper.updateBatch(updateItems);

        // 4. 更新支付订单
        payOrderApi.updatePayOrderPrice(order.getPayOrderId(), newPayPrice,newPayPrice);

        // 5. 记录订单日志
        TradeOrderLogUtils.setOrderInfo(order.getId(), order.getStatus(), order.getStatus(),
                MapUtil.<String, Object>builder().put("oldPayPrice", MoneyUtils.fenToYuanStr(order.getPayPrice()))
                        .put("newPayPrice", MoneyUtils.fenToYuanStr(newPayPrice)).build());
    }

    @Override
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.ADMIN_UPDATE_ADDRESS)
    public void updateOrderAddress(TradeOrderUpdateAddressReqVO reqVO) {
        // 校验交易订单
        TradeOrderDO order = validateOrderExists(reqVO.getId());
        // 只有待发货状态，才可以修改订单收货地址；
        if (!TradeOrderStatusEnum.isUndelivered(order.getStatus())) {
            throw exception(ORDER_UPDATE_ADDRESS_FAIL_STATUS_NOT_DELIVERED);
        }

        // 更新
        tradeOrderMapper.updateById(TradeOrderConvert.INSTANCE.convert(reqVO));

        // 记录订单日志
        TradeOrderLogUtils.setOrderInfo(order.getId(), order.getStatus(), order.getStatus());
    }

    @Override
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.ADMIN_PICK_UP_RECEIVE)
    public void pickUpOrderByAdmin(Long id) {
        getSelf().pickUpOrder(tradeOrderMapper.selectById(id));
    }

    @Override
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.ADMIN_PICK_UP_RECEIVE)
    public void pickUpOrderByAdmin(String pickUpVerifyCode) {
        getSelf().pickUpOrder(tradeOrderMapper.selectOneByPickUpVerifyCode(pickUpVerifyCode));
    }

    @Override
    public TradeOrderDO getByPickUpVerifyCode(String pickUpVerifyCode) {
        return tradeOrderMapper.selectOneByPickUpVerifyCode(pickUpVerifyCode);
    }

    @Transactional(rollbackFor = Exception.class)
    public void pickUpOrder(TradeOrderDO order) {
        if (order == null) {
            throw exception(ORDER_NOT_FOUND);
        }
        if (ObjUtil.notEqual(DeliveryTypeEnum.PICK_UP.getType(), order.getDeliveryType())) {
            throw exception(ORDER_RECEIVE_FAIL_DELIVERY_TYPE_NOT_PICK_UP);
        }
        receiveOrder0(order);
    }

    // ===== Order Item =====

    @Override
    public void updateOrderItemWhenAfterSaleCreate(Long id, Long afterSaleId) {
        // 更新订单项
        updateOrderItemAfterSaleStatus(id, TradeOrderItemAfterSaleStatusEnum.NONE.getStatus(),
                TradeOrderItemAfterSaleStatusEnum.APPLY.getStatus(), afterSaleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderItemWhenAfterSaleSuccess(Long id, Integer refundPrice) {
        // 1.1 更新订单项
        updateOrderItemAfterSaleStatus(id, TradeOrderItemAfterSaleStatusEnum.APPLY.getStatus(),
                TradeOrderItemAfterSaleStatusEnum.SUCCESS.getStatus(), null);
        // 1.2 执行 TradeOrderHandler 的后置处理
        TradeOrderItemDO orderItem = tradeOrderItemMapper.selectById(id);
        TradeOrderDO order = tradeOrderMapper.selectById(orderItem.getOrderId());
        tradeOrderHandlers.forEach(handler -> handler.afterCancelOrderItem(order, orderItem));

        // 2.1 更新订单的退款金额、积分
        Integer orderRefundPrice = order.getRefundPrice() + refundPrice;
        Integer orderRefundPoint = order.getRefundPoint() + orderItem.getUsePoint();
        Integer refundStatus = isAllOrderItemAfterSaleSuccess(order.getId()) ?
                TradeOrderRefundStatusEnum.ALL.getStatus() // 如果都售后成功，则需要取消订单
                : TradeOrderRefundStatusEnum.PART.getStatus();
        tradeOrderMapper.updateById(new TradeOrderDO().setId(order.getId())
                .setRefundStatus(refundStatus)
                .setRefundPrice(orderRefundPrice).setRefundPoint(orderRefundPoint));
        // 2.2 如果全部退款，则进行取消订单
        getSelf().cancelOrderByAfterSale(order, orderRefundPrice);
    }

    @Override
    public void updateOrderItemWhenAfterSaleCancel(Long id) {
        // 更新订单项
        updateOrderItemAfterSaleStatus(id, TradeOrderItemAfterSaleStatusEnum.APPLY.getStatus(),
                TradeOrderItemAfterSaleStatusEnum.NONE.getStatus(), null);
    }

    private void updateOrderItemAfterSaleStatus(Long id, Integer oldAfterSaleStatus, Integer newAfterSaleStatus,
                                                Long afterSaleId) {
        // 更新订单项
        int updateCount = tradeOrderItemMapper.updateAfterSaleStatus(id, oldAfterSaleStatus, newAfterSaleStatus, afterSaleId);
        if (updateCount <= 0) {
            throw exception(ORDER_ITEM_UPDATE_AFTER_SALE_STATUS_FAIL);
        }

    }

    /**
     * 判断指定订单的所有订单项，是不是都售后成功
     *
     * @param id 订单编号
     * @return 是否都售后成功
     */
    private boolean isAllOrderItemAfterSaleSuccess(Long id) {
        List<TradeOrderItemDO> orderItems = tradeOrderItemMapper.selectListByOrderId(id);
        return orderItems.stream().allMatch(orderItem -> Objects.equals(orderItem.getAfterSaleStatus(),
                TradeOrderItemAfterSaleStatusEnum.SUCCESS.getStatus()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.MEMBER_COMMENT)
    public Long createOrderItemCommentByMember(Long userId, AppTradeOrderItemCommentCreateReqVO createReqVO) {
        // 1.1 先通过订单项 ID，查询订单项是否存在
        TradeOrderItemDO orderItem = tradeOrderItemMapper.selectByIdAndUserId(createReqVO.getOrderItemId(), userId);
        if (orderItem == null) {
            throw exception(ORDER_ITEM_NOT_FOUND);
        }
        // 1.2 校验订单相关状态
        TradeOrderDO order = tradeOrderMapper.selectOrderByIdAndUserId(orderItem.getOrderId(), userId);
        if (order == null) {
            throw exception(ORDER_NOT_FOUND);
        }
        if (ObjectUtil.notEqual(order.getStatus(), TradeOrderStatusEnum.COMPLETED.getStatus())) {
            throw exception(ORDER_COMMENT_FAIL_STATUS_NOT_COMPLETED);
        }
        if (ObjectUtil.notEqual(order.getCommentStatus(), Boolean.FALSE)) {
            throw exception(ORDER_COMMENT_STATUS_NOT_FALSE);
        }

        // 2. 创建评价
        Long commentId = createOrderItemComment0(orderItem, createReqVO);

        // 3. 如果订单项都评论了，则更新订单评价状态
        List<TradeOrderItemDO> orderItems = tradeOrderItemMapper.selectListByOrderId(order.getId());
        if (!anyMatch(orderItems, item -> Objects.equals(item.getCommentStatus(), Boolean.FALSE))) {
            tradeOrderMapper.updateById(new TradeOrderDO().setId(order.getId()).setCommentStatus(Boolean.TRUE)
                    .setFinishTime(LocalDateTime.now()));
            // 增加订单日志。注意：只有在所有订单项都评价后，才会增加
            TradeOrderLogUtils.setOrderInfo(order.getId(), order.getStatus(), order.getStatus());
        }
        return commentId;
    }

    @Override
    public int createOrderItemCommentBySystem() {
        // 1. 查询过期的待支付订单
        LocalDateTime expireTime = minusTime(tradeOrderProperties.getCommentExpireTime());
        List<TradeOrderDO> orders = tradeOrderMapper.selectListByStatusAndReceiveTimeLt(
                TradeOrderStatusEnum.COMPLETED.getStatus(), expireTime, false);
        if (CollUtil.isEmpty(orders)) {
            return 0;
        }

        // 2. 遍历执行，逐个取消
        int count = 0;
        for (TradeOrderDO order : orders) {
            try {
                getSelf().createOrderItemCommentBySystemBySystem(order);
                count++;
            } catch (Throwable e) {
                log.error("[createOrderItemCommentBySystem][order({}) 过期订单异常]", order.getId(), e);
            }
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderCombinationInfo(Long orderId, Long activityId, Long combinationRecordId, Long headId) {
        tradeOrderMapper.updateById(
                new TradeOrderDO().setId(orderId).setCombinationActivityId(activityId)
                        .setCombinationRecordId(combinationRecordId).setCombinationHeadId(headId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelPaidOrder(Long userId, Long orderId) {
        // TODO 芋艿：这里实现要优化下；
        TradeOrderDO order = tradeOrderMapper.selectOrderByIdAndUserId(orderId, userId);
        if (order == null) {
            throw exception(ORDER_NOT_FOUND);
        }
        cancelOrder0(order, TradeOrderCancelTypeEnum.MEMBER_CANCEL);
    }

    /**
     * 创建单个订单的评论
     *
     * @param order 订单
     */
    @Transactional(rollbackFor = Exception.class)
    @TradeOrderLog(operateType = TradeOrderOperateTypeEnum.SYSTEM_COMMENT)
    public void createOrderItemCommentBySystemBySystem(TradeOrderDO order) {
        // 1. 查询未评论的订单项
        List<TradeOrderItemDO> orderItems = tradeOrderItemMapper.selectListByOrderIdAndCommentStatus(
                order.getId(), Boolean.FALSE);
        if (CollUtil.isEmpty(orderItems)) {
            return;
        }

        // 2. 逐个评论
        for (TradeOrderItemDO orderItem : orderItems) {
            // 2.1 创建评价
            AppTradeOrderItemCommentCreateReqVO commentCreateReqVO = new AppTradeOrderItemCommentCreateReqVO()
                    .setOrderItemId(orderItem.getId()).setAnonymous(false).setContent("")
                    .setBenefitScores(5).setDescriptionScores(5);
            createOrderItemComment0(orderItem, commentCreateReqVO);

            // 2.2 更新订单项评价状态
            tradeOrderItemMapper.updateById(new TradeOrderItemDO().setId(orderItem.getId()).setCommentStatus(Boolean.TRUE));
        }

        // 3. 所有订单项都评论了，则更新订单评价状态
        tradeOrderMapper.updateById(new TradeOrderDO().setId(order.getId()).setCommentStatus(Boolean.TRUE)
                .setFinishTime(LocalDateTime.now()));
        // 增加订单日志。注意：只有在所有订单项都评价后，才会增加
        TradeOrderLogUtils.setOrderInfo(order.getId(), order.getStatus(), order.getStatus());
    }

    /**
     * 创建订单项的评论的核心实现
     *
     * @param orderItem   订单项
     * @param createReqVO 评论内容
     * @return 评论编号
     */
    private Long createOrderItemComment0(TradeOrderItemDO orderItem, AppTradeOrderItemCommentCreateReqVO createReqVO) {
        // 1. 创建评价
        ProductCommentCreateReqDTO productCommentCreateReqDTO = TradeOrderConvert.INSTANCE.convert04(createReqVO, orderItem);
        Long commentId = productCommentApi.createComment(productCommentCreateReqDTO);

        // 2. 更新订单项评价状态
        tradeOrderItemMapper.updateById(new TradeOrderItemDO().setId(orderItem.getId()).setCommentStatus(Boolean.TRUE));
        return commentId;
    }

    // ===== 营销相关的操作 =====

    /**
     * 获得自身的代理对象，解决 AOP 生效问题
     *
     * @return 自己
     */
    private TradeOrderUpdateServiceImpl getSelf() {
        return SpringUtil.getBean(getClass());
    }

}
