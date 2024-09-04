package com.dofast.module.channel.service.order;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;
import com.dofast.module.channel.controller.admin.order.vo.OrderCreateReqVO;
import com.dofast.module.channel.controller.admin.order.vo.OrderExportReqVO;
import com.dofast.module.channel.controller.admin.order.vo.OrderUpdateReqVO;
import com.dofast.module.channel.dal.dataobject.order.OrderDO;
import com.dofast.module.channel.dal.mysql.order.ChannelOrderMapper;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static com.dofast.framework.common.util.date.LocalDateTimeUtils.buildBetweenTime;
import static com.dofast.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static com.dofast.framework.test.core.util.AssertUtils.assertPojoEquals;
import static com.dofast.framework.test.core.util.AssertUtils.assertServiceException;
import static com.dofast.framework.test.core.util.RandomUtils.randomInteger;
import static com.dofast.framework.test.core.util.RandomUtils.randomPojo;
import static com.dofast.module.channel.enums.ErrorCodeConstants.ORDER_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link ChannelOrderServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(ChannelOrderServiceImpl.class)
public class ChannelOrderServiceImplTest extends BaseDbUnitTest {

    @Autowired
    private ChannelOrderServiceImpl orderService;

    @Autowired
    private ChannelOrderMapper orderMapperx;

    @Test
    public void testCreateOrder_success() {
        // 准备参数
        OrderCreateReqVO reqVO = randomPojo(OrderCreateReqVO.class);

        // 调用
        Integer orderId = orderService.createOrder(reqVO);
        // 断言
        assertNotNull(orderId);
        // 校验记录的属性是否正确
        OrderDO order = orderMapperx.selectById(orderId);
        assertPojoEquals(reqVO, order);
    }

    @Test
    public void testUpdateOrder_success() {
        // mock 数据
        OrderDO dbOrder = randomPojo(OrderDO.class);
        orderMapperx.insert(dbOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        OrderUpdateReqVO reqVO = randomPojo(OrderUpdateReqVO.class, o -> {
            o.setId(dbOrder.getId()); // 设置更新的 ID
        });

        // 调用
        orderService.updateOrder(reqVO);
        // 校验是否更新正确
        OrderDO order = orderMapperx.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, order);
    }

    @Test
    public void testUpdateOrder_notExists() {
        // 准备参数
        OrderUpdateReqVO reqVO = randomPojo(OrderUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> orderService.updateOrder(reqVO), ORDER_NOT_EXISTS);
    }

    @Test
    public void testDeleteOrder_success() {
        // mock 数据
        OrderDO dbOrder = randomPojo(OrderDO.class);
        orderMapperx.insert(dbOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbOrder.getId();

        // 调用
        orderService.deleteOrder(id);
       // 校验数据不存在了
       assertNull(orderMapperx.selectById(id));
    }

    @Test
    public void testDeleteOrder_notExists() {
        // 准备参数
        Integer id = randomInteger();

        // 调用, 并断言异常
        assertServiceException(() -> orderService.deleteOrder(id), ORDER_NOT_EXISTS);
    }

    /*@Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderPage() {
       // mock 数据
       OrderDO dbOrder = randomPojo(OrderDO.class, o -> { // 等会查询到
           o.setRefOid(null);
           o.setPosCode(null);
           o.setRefType(null);
           o.setTotalFee(null);
           o.setPayment(null);
           o.setReceivedPayment(null);
           o.setTotalPrice(null);
           o.setTotalSellPrice(null);
           o.setPostFee(null);
           o.setServiceFee(null);
           o.setDiscountFee(null);
           o.setOrderTime(null);
           o.setModifyTime(null);
           o.setPayTime(null);
           o.setShippingTime(null);
           o.setFinishTime(null);
           o.setReceiverCountry(null);
           o.setReceiverState(null);
           o.setReceiverCity(null);
           o.setReceiverDistrict(null);
           o.setReceiverTown(null);
           o.setReceiverId(null);
           o.setStatus(null);
           o.setType(null);
           o.setRefundStatus(null);
           o.setFlag(null);
           o.setSellerMemo(null);
           o.setBuyerMemo(null);
           o.setOpenSellerNick(null);
           o.setOpenBuyerNick(null);
           o.setOpenBuyerId(null);
           o.setCreateTime(null);
           o.setLogisticsPartnerCode(null);
           o.setLogisticsOrderNo(null);
           o.setRefWhsCode(null);
           o.setDeliveryTime(null);
           o.setLatestDeliveryTime(null);
           o.setProps(null);
           o.setMark2(null);
           o.setBusinessType(null);
           o.setUserId(null);
           o.setAddressId(null);
       });
       orderMapperx.insert(dbOrder);
       // 测试 refOid 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setRefOid(null)));
       // 测试 posCode 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setPosCode(null)));
       // 测试 refType 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setRefType(null)));
       // 测试 totalFee 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setTotalFee(null)));
       // 测试 payment 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setPayment(null)));
       // 测试 receivedPayment 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceivedPayment(null)));
       // 测试 totalPrice 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setTotalPrice(null)));
       // 测试 totalSellPrice 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setTotalSellPrice(null)));
       // 测试 postFee 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setPostFee(null)));
       // 测试 serviceFee 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setServiceFee(null)));
       // 测试 discountFee 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setDiscountFee(null)));
       // 测试 orderTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setOrderTime(null)));
       // 测试 modifyTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setModifyTime(null)));
       // 测试 payTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setPayTime(null)));
       // 测试 shippingTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setShippingTime(null)));
       // 测试 finishTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setFinishTime(null)));
       // 测试 receiverCountry 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceiverCountry(null)));
       // 测试 receiverState 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceiverState(null)));
       // 测试 receiverCity 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceiverCity(null)));
       // 测试 receiverDistrict 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceiverDistrict(null)));
       // 测试 receiverTown 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceiverTown(null)));
       // 测试 receiverId 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceiverId(null)));
       // 测试 status 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setStatus(null)));
       // 测试 type 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setType(null)));
       // 测试 refundStatus 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setRefundStatus(null)));
       // 测试 flag 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setFlag(null)));
       // 测试 sellerMemo 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setSellerMemo(null)));
       // 测试 buyerMemo 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setBuyerMemo(null)));
       // 测试 openSellerNick 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setOpenSellerNick(null)));
       // 测试 openBuyerNick 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setOpenBuyerNick(null)));
       // 测试 openBuyerId 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setOpenBuyerId(null)));
       // 测试 createTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setCreateTime(null)));
       // 测试 logisticsPartnerCode 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setLogisticsPartnerCode(null)));
       // 测试 logisticsOrderNo 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setLogisticsOrderNo(null)));
       // 测试 refWhsCode 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setRefWhsCode(null)));
       // 测试 deliveryTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setDeliveryTime(null)));
       // 测试 latestDeliveryTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setLatestDeliveryTime(null)));
       // 测试 props 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setProps(null)));
       // 测试 mark2 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setMark2(null)));
       // 测试 businessType 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setBusinessType(null)));
       // 测试 userId 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setUserId(null)));
       // 测试 addressId 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setAddressId(null)));
       // 准备参数
       OrderPageReqVO reqVO = new OrderPageReqVO();
       reqVO.setRefOid(null);
       reqVO.setPosCode(null);
       reqVO.setRefType(null);
       reqVO.setTotalFee(null);
       reqVO.setPayment(null);
       reqVO.setReceivedPayment(null);
       reqVO.setTotalPrice(null);
       reqVO.setTotalSellPrice(null);
       reqVO.setPostFee(null);
       reqVO.setServiceFee(null);
       reqVO.setDiscountFee(null);
       reqVO.setOrderTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setModifyTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setPayTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setShippingTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setFinishTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setReceiverCountry(null);
       reqVO.setReceiverState(null);
       reqVO.setReceiverCity(null);
       reqVO.setReceiverDistrict(null);
       reqVO.setReceiverTown(null);
       reqVO.setReceiverId(null);
       reqVO.setStatus(null);
       reqVO.setType(null);
       reqVO.setRefundStatus(null);
       reqVO.setFlag(null);
       reqVO.setSellerMemo(null);
       reqVO.setBuyerMemo(null);
       reqVO.setOpenSellerNick(null);
       reqVO.setOpenBuyerNick(null);
       reqVO.setOpenBuyerId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setLogisticsPartnerCode(null);
       reqVO.setLogisticsOrderNo(null);
       reqVO.setRefWhsCode(null);
       reqVO.setDeliveryTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setLatestDeliveryTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setProps(null);
       reqVO.setMark2(null);
       reqVO.setBusinessType(null);
       reqVO.setUserId(null);
       reqVO.setAddressId(null);

       // 调用
       PageResult<OrderDO> pageResult = orderService.getOrderPage();
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbOrder, pageResult.getList().get(0));
    }*/

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderList() {
       // mock 数据
       OrderDO dbOrder = randomPojo(OrderDO.class, o -> { // 等会查询到
           o.setRefOid(null);
           o.setPosCode(null);
           o.setRefType(null);
           o.setTotalFee(null);
           o.setPayment(null);
           o.setReceivedPayment(null);
           o.setTotalPrice(null);
           o.setTotalSellPrice(null);
           o.setPostFee(null);
           o.setServiceFee(null);
           o.setDiscountFee(null);
           o.setOrderTime(null);
           o.setModifyTime(null);
           o.setPayTime(null);
           o.setShippingTime(null);
           o.setFinishTime(null);
           o.setReceiverCountry(null);
           o.setReceiverState(null);
           o.setReceiverCity(null);
           o.setReceiverDistrict(null);
           o.setReceiverTown(null);
           o.setReceiverId(null);
           o.setStatus(null);
           o.setType(null);
           o.setRefundStatus(null);
           o.setFlag(null);
           o.setSellerMemo(null);
           o.setBuyerMemo(null);
           o.setOpenSellerNick(null);
           o.setOpenBuyerNick(null);
           o.setOpenBuyerId(null);
           o.setCreateTime(null);
           o.setLogisticsPartnerCode(null);
           o.setLogisticsOrderNo(null);
           o.setRefWhsCode(null);
           o.setDeliveryTime(null);
           o.setLatestDeliveryTime(null);
           o.setProps(null);
           o.setMark2(null);
           o.setBusinessType(null);
           o.setUserId(null);
           o.setAddressId(null);
       });
       orderMapperx.insert(dbOrder);
       // 测试 refOid 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setRefOid(null)));
       // 测试 posCode 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setPosCode(null)));
       // 测试 refType 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setRefType(null)));
       // 测试 totalFee 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setTotalFee(null)));
       // 测试 payment 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setPayment(null)));
       // 测试 receivedPayment 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceivedPayment(null)));
       // 测试 totalPrice 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setTotalPrice(null)));
       // 测试 totalSellPrice 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setTotalSellPrice(null)));
       // 测试 postFee 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setPostFee(null)));
       // 测试 serviceFee 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setServiceFee(null)));
       // 测试 discountFee 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setDiscountFee(null)));
       // 测试 orderTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setOrderTime(null)));
       // 测试 modifyTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setModifyTime(null)));
       // 测试 payTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setPayTime(null)));
       // 测试 shippingTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setShippingTime(null)));
       // 测试 finishTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setFinishTime(null)));
       // 测试 receiverCountry 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceiverCountry(null)));
       // 测试 receiverState 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceiverState(null)));
       // 测试 receiverCity 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceiverCity(null)));
       // 测试 receiverDistrict 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceiverDistrict(null)));
       // 测试 receiverTown 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceiverTown(null)));
       // 测试 receiverId 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setReceiverId(null)));
       // 测试 status 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setStatus(null)));
       // 测试 type 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setType(null)));
       // 测试 refundStatus 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setRefundStatus(null)));
       // 测试 flag 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setFlag(null)));
       // 测试 sellerMemo 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setSellerMemo(null)));
       // 测试 buyerMemo 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setBuyerMemo(null)));
       // 测试 openSellerNick 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setOpenSellerNick(null)));
       // 测试 openBuyerNick 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setOpenBuyerNick(null)));
       // 测试 openBuyerId 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setOpenBuyerId(null)));
       // 测试 createTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setCreateTime(null)));
       // 测试 logisticsPartnerCode 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setLogisticsPartnerCode(null)));
       // 测试 logisticsOrderNo 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setLogisticsOrderNo(null)));
       // 测试 refWhsCode 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setRefWhsCode(null)));
       // 测试 deliveryTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setDeliveryTime(null)));
       // 测试 latestDeliveryTime 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setLatestDeliveryTime(null)));
       // 测试 props 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setProps(null)));
       // 测试 mark2 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setMark2(null)));
       // 测试 businessType 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setBusinessType(null)));
       // 测试 userId 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setUserId(null)));
       // 测试 addressId 不匹配
       orderMapperx.insert(cloneIgnoreId(dbOrder, o -> o.setAddressId(null)));
       // 准备参数
       OrderExportReqVO reqVO = new OrderExportReqVO();
       reqVO.setRefOid(null);
       reqVO.setPosCode(null);
       reqVO.setRefType(null);
       reqVO.setTotalFee(null);
       reqVO.setPayment(null);
       reqVO.setReceivedPayment(null);
       reqVO.setTotalPrice(null);
       reqVO.setTotalSellPrice(null);
       reqVO.setPostFee(null);
       reqVO.setServiceFee(null);
       reqVO.setDiscountFee(null);
       reqVO.setOrderTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setModifyTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setPayTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setShippingTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setFinishTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setReceiverCountry(null);
       reqVO.setReceiverState(null);
       reqVO.setReceiverCity(null);
       reqVO.setReceiverDistrict(null);
       reqVO.setReceiverTown(null);
       reqVO.setReceiverId(null);
       reqVO.setStatus(null);
       reqVO.setType(null);
       reqVO.setRefundStatus(null);
       reqVO.setFlag(null);
       reqVO.setSellerMemo(null);
       reqVO.setBuyerMemo(null);
       reqVO.setOpenSellerNick(null);
       reqVO.setOpenBuyerNick(null);
       reqVO.setOpenBuyerId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setLogisticsPartnerCode(null);
       reqVO.setLogisticsOrderNo(null);
       reqVO.setRefWhsCode(null);
       reqVO.setDeliveryTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setLatestDeliveryTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setProps(null);
       reqVO.setMark2(null);
       reqVO.setBusinessType(null);
       reqVO.setUserId(null);
       reqVO.setAddressId(null);

       // 调用
       List<OrderDO> list = orderService.getOrderList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbOrder, list.get(0));
    }

}
