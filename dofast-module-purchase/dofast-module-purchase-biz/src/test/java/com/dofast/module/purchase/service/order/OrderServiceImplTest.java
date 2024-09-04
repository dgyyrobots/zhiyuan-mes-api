package com.dofast.module.purchase.service.order;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.purchase.controller.admin.order.vo.*;
import com.dofast.module.purchase.dal.dataobject.order.OrderDO;
import com.dofast.module.purchase.dal.mysql.order.PurchaseOrderMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.purchase.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link OrderServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(OrderServiceImpl.class)
public class OrderServiceImplTest extends BaseDbUnitTest {

    @Resource
    private OrderServiceImpl orderService;

    @Resource
    private PurchaseOrderMapper orderMapper;

    @Test
    public void testCreateOrder_success() {
        // 准备参数
        OrderCreateReqVO reqVO = randomPojo(OrderCreateReqVO.class);

        // 调用
        Integer orderId = orderService.createOrder(reqVO);
        // 断言
        assertNotNull(orderId);
        // 校验记录的属性是否正确
        OrderDO order = orderMapper.selectById(orderId);
        assertPojoEquals(reqVO, order);
    }

    @Test
    public void testUpdateOrder_success() {
        // mock 数据
        OrderDO dbOrder = randomPojo(OrderDO.class);
        orderMapper.insert(dbOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        OrderUpdateReqVO reqVO = randomPojo(OrderUpdateReqVO.class, o -> {
            o.setId(dbOrder.getId()); // 设置更新的 ID
        });

        // 调用
        orderService.updateOrder(reqVO);
        // 校验是否更新正确
        OrderDO order = orderMapper.selectById(reqVO.getId()); // 获取最新的
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
        orderMapper.insert(dbOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbOrder.getId();

        // 调用
        orderService.deleteOrder(id);
       // 校验数据不存在了
       assertNull(orderMapper.selectById(id));
    }

    /*@Test
    public void testDeleteOrder_notExists() {
        // 准备参数
        Integer id = randomIntegerId();

        // 调用, 并断言异常
        assertServiceException(() -> orderService.deleteOrder(id), ORDER_NOT_EXISTS);
    }*/

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderPage() {
       // mock 数据
       OrderDO dbOrder = randomPojo(OrderDO.class, o -> { // 等会查询到
           o.setSupplierId(null);
           o.setSupplierContact(null);
           o.setSupplierPhone(null);
           o.setPurchaseAmount(null);
           o.setPoNo(null);
           o.setPaymentType(null);
           o.setReturnGoods(null);
           o.setProcessType(null);
           o.setRemarks(null);
           o.setCreateTime(null);
       });
       orderMapper.insert(dbOrder);
       // 测试 supplierId 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setSupplierId(null)));
       // 测试 supplierContact 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setSupplierContact(null)));
       // 测试 supplierPhone 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setSupplierPhone(null)));
       // 测试 purchaseAmount 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setPurchaseAmount(null)));
       // 测试 poNo 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setPoNo(null)));
       // 测试 paymentType 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setPaymentType(null)));
       // 测试 returnGoods 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setReturnGoods(null)));
       // 测试 processType 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setProcessType(null)));
       // 测试 remarks 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setRemarks(null)));
       // 测试 createTime 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setCreateTime(null)));
       // 准备参数
       OrderPageReqVO reqVO = new OrderPageReqVO();
       reqVO.setSupplierId(null);
       reqVO.setSupplierContact(null);
       reqVO.setSupplierPhone(null);
       reqVO.setPurchaseAmount(null);
       reqVO.setPoNo(null);
       reqVO.setPaymentType(null);
       reqVO.setReturnGoods(null);
       reqVO.setProcessType(null);
       reqVO.setRemarks(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<OrderDO> pageResult = orderService.getOrderPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbOrder, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderList() {
       // mock 数据
       OrderDO dbOrder = randomPojo(OrderDO.class, o -> { // 等会查询到
           o.setSupplierId(null);
           o.setSupplierContact(null);
           o.setSupplierPhone(null);
           o.setPurchaseAmount(null);
           o.setPoNo(null);
           o.setPaymentType(null);
           o.setReturnGoods(null);
           o.setProcessType(null);
           o.setRemarks(null);
           o.setCreateTime(null);
       });
       orderMapper.insert(dbOrder);
       // 测试 supplierId 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setSupplierId(null)));
       // 测试 supplierContact 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setSupplierContact(null)));
       // 测试 supplierPhone 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setSupplierPhone(null)));
       // 测试 purchaseAmount 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setPurchaseAmount(null)));
       // 测试 poNo 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setPoNo(null)));
       // 测试 paymentType 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setPaymentType(null)));
       // 测试 returnGoods 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setReturnGoods(null)));
       // 测试 processType 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setProcessType(null)));
       // 测试 remarks 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setRemarks(null)));
       // 测试 createTime 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setCreateTime(null)));
       // 准备参数
       OrderExportReqVO reqVO = new OrderExportReqVO();
       reqVO.setSupplierId(null);
       reqVO.setSupplierContact(null);
       reqVO.setSupplierPhone(null);
       reqVO.setPurchaseAmount(null);
       reqVO.setPoNo(null);
       reqVO.setPaymentType(null);
       reqVO.setReturnGoods(null);
       reqVO.setProcessType(null);
       reqVO.setRemarks(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<OrderDO> list = orderService.getOrderList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbOrder, list.get(0));
    }

}
