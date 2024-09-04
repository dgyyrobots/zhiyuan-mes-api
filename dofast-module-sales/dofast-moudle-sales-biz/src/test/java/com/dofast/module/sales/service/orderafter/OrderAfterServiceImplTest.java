package com.dofast.module.sales.service.orderafter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.sales.controller.admin.orderafter.vo.*;
import com.dofast.module.sales.dal.dataobject.orderafter.OrderAfterDO;
import com.dofast.module.sales.dal.mysql.orderafter.OrderAfterMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.sales.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link OrderAfterServiceImpl} 的单元测试类
 *
 * @author a1
 */
@Import(OrderAfterServiceImpl.class)
public class OrderAfterServiceImplTest extends BaseDbUnitTest {

    @Resource
    private OrderAfterServiceImpl orderAfterService;

    @Resource
    private OrderAfterMapper orderAfterMapper;

    @Test
    public void testCreateOrderAfter_success() {
        // 准备参数
        OrderAfterCreateReqVO reqVO = randomPojo(OrderAfterCreateReqVO.class);

        // 调用
        Long orderAfterId = orderAfterService.createOrderAfter(reqVO);
        // 断言
        assertNotNull(orderAfterId);
        // 校验记录的属性是否正确
        OrderAfterDO orderAfter = orderAfterMapper.selectById(orderAfterId);
        assertPojoEquals(reqVO, orderAfter);
    }

    @Test
    public void testUpdateOrderAfter_success() {
        // mock 数据
        OrderAfterDO dbOrderAfter = randomPojo(OrderAfterDO.class);
        orderAfterMapper.insert(dbOrderAfter);// @Sql: 先插入出一条存在的数据
        // 准备参数
        OrderAfterUpdateReqVO reqVO = randomPojo(OrderAfterUpdateReqVO.class, o -> {
            o.setId(dbOrderAfter.getId()); // 设置更新的 ID
        });

        // 调用
        orderAfterService.updateOrderAfter(reqVO);
        // 校验是否更新正确
        OrderAfterDO orderAfter = orderAfterMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, orderAfter);
    }

    @Test
    public void testUpdateOrderAfter_notExists() {
        // 准备参数
        OrderAfterUpdateReqVO reqVO = randomPojo(OrderAfterUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> orderAfterService.updateOrderAfter(reqVO), ORDER_AFTER_NOT_EXISTS);
    }

    @Test
    public void testDeleteOrderAfter_success() {
        // mock 数据
        OrderAfterDO dbOrderAfter = randomPojo(OrderAfterDO.class);
        orderAfterMapper.insert(dbOrderAfter);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbOrderAfter.getId();

        // 调用
        orderAfterService.deleteOrderAfter(id);
       // 校验数据不存在了
       assertNull(orderAfterMapper.selectById(id));
    }

    @Test
    public void testDeleteOrderAfter_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> orderAfterService.deleteOrderAfter(id), ORDER_AFTER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderAfterPage() {
       // mock 数据
       OrderAfterDO dbOrderAfter = randomPojo(OrderAfterDO.class, o -> { // 等会查询到
           o.setAssociatedBusiness(null);
           o.setBusinessData(null);
           o.setAssociatedObjects(null);
           o.setObjectName(null);
           o.setAssociatedStores(null);
           o.setAssociatedRepository(null);
           o.setAssociatedAmounts(null);
           o.setTransactionCategory(null);
           o.setPriority(null);
           o.setAddCopy(null);
           o.setMainPersonResponsible(null);
           o.setDeadline(null);
           o.setRemark(null);
           o.setTransactionTitle(null);
           o.setTransactionContent(null);
           o.setPicture(null);
           o.setProcessInstanceId(null);
           o.setCreateTime(null);
       });
       orderAfterMapper.insert(dbOrderAfter);
       // 测试 associatedBusiness 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setAssociatedBusiness(null)));
       // 测试 businessData 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setBusinessData(null)));
       // 测试 associatedObjects 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setAssociatedObjects(null)));
       // 测试 objectName 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setObjectName(null)));
       // 测试 associatedStores 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setAssociatedStores(null)));
       // 测试 associatedRepository 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setAssociatedRepository(null)));
       // 测试 associatedAmounts 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setAssociatedAmounts(null)));
       // 测试 transactionCategory 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setTransactionCategory(null)));
       // 测试 priority 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setPriority(null)));
       // 测试 addCopy 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setAddCopy(null)));
       // 测试 mainPersonResponsible 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setMainPersonResponsible(null)));
       // 测试 deadline 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setDeadline(null)));
       // 测试 remark 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setRemark(null)));
       // 测试 transactionTitle 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setTransactionTitle(null)));
       // 测试 transactionContent 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setTransactionContent(null)));
       // 测试 picture 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setPicture(null)));
       // 测试 result 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setStatus(null)));
       // 测试 processInstanceId 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setProcessInstanceId(null)));
       // 测试 createTime 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setCreateTime(null)));
       // 准备参数
       OrderAfterPageReqVO reqVO = new OrderAfterPageReqVO();
       reqVO.setAssociatedBusiness(null);
       reqVO.setBusinessData(null);
       reqVO.setAssociatedObjects(null);
       reqVO.setObjectName(null);
       reqVO.setAssociatedStores(null);
       reqVO.setAssociatedRepository(null);
       reqVO.setAssociatedAmounts(null);
       reqVO.setTransactionCategory(null);
       reqVO.setPriority(null);
       reqVO.setAddCopy(null);
       reqVO.setMainPersonResponsible(null);
       reqVO.setDeadline(null);
       reqVO.setRemark(null);
       reqVO.setTransactionTitle(null);
       reqVO.setTransactionContent(null);
       reqVO.setPicture(null);
       reqVO.setStatus(null);
       reqVO.setProcessInstanceId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<OrderAfterDO> pageResult = orderAfterService.getOrderAfterPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbOrderAfter, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderAfterList() {
       // mock 数据
       OrderAfterDO dbOrderAfter = randomPojo(OrderAfterDO.class, o -> { // 等会查询到
           o.setAssociatedBusiness(null);
           o.setBusinessData(null);
           o.setAssociatedObjects(null);
           o.setObjectName(null);
           o.setAssociatedStores(null);
           o.setAssociatedRepository(null);
           o.setAssociatedAmounts(null);
           o.setTransactionCategory(null);
           o.setPriority(null);
           o.setAddCopy(null);
           o.setMainPersonResponsible(null);
           o.setDeadline(null);
           o.setRemark(null);
           o.setTransactionTitle(null);
           o.setTransactionContent(null);
           o.setPicture(null);
           o.setStatus(null);
           o.setProcessInstanceId(null);
           o.setCreateTime(null);
       });
       orderAfterMapper.insert(dbOrderAfter);
       // 测试 associatedBusiness 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setAssociatedBusiness(null)));
       // 测试 businessData 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setBusinessData(null)));
       // 测试 associatedObjects 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setAssociatedObjects(null)));
       // 测试 objectName 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setObjectName(null)));
       // 测试 associatedStores 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setAssociatedStores(null)));
       // 测试 associatedRepository 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setAssociatedRepository(null)));
       // 测试 associatedAmounts 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setAssociatedAmounts(null)));
       // 测试 transactionCategory 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setTransactionCategory(null)));
       // 测试 priority 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setPriority(null)));
       // 测试 addCopy 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setAddCopy(null)));
       // 测试 mainPersonResponsible 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setMainPersonResponsible(null)));
       // 测试 deadline 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setDeadline(null)));
       // 测试 remark 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setRemark(null)));
       // 测试 transactionTitle 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setTransactionTitle(null)));
       // 测试 transactionContent 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setTransactionContent(null)));
       // 测试 picture 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setPicture(null)));
       // 测试 result 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setStatus(null)));
       // 测试 processInstanceId 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setProcessInstanceId(null)));
       // 测试 createTime 不匹配
       orderAfterMapper.insert(cloneIgnoreId(dbOrderAfter, o -> o.setCreateTime(null)));
       // 准备参数
       OrderAfterExportReqVO reqVO = new OrderAfterExportReqVO();
       reqVO.setAssociatedBusiness(null);
       reqVO.setBusinessData(null);
       reqVO.setAssociatedObjects(null);
       reqVO.setObjectName(null);
       reqVO.setAssociatedStores(null);
       reqVO.setAssociatedRepository(null);
       reqVO.setAssociatedAmounts(null);
       reqVO.setTransactionCategory(null);
       reqVO.setPriority(null);
       reqVO.setAddCopy(null);
       reqVO.setMainPersonResponsible(null);
       reqVO.setDeadline(null);
       reqVO.setRemark(null);
       reqVO.setTransactionTitle(null);
       reqVO.setTransactionContent(null);
       reqVO.setPicture(null);
       reqVO.setStatus(null);
       reqVO.setProcessInstanceId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<OrderAfterDO> list = orderAfterService.getOrderAfterList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbOrderAfter, list.get(0));
    }

}
