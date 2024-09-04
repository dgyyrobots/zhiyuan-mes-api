package com.dofast.module.channel.service.salesorder;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.channel.controller.admin.salesorder.vo.*;
import com.dofast.module.channel.dal.dataobject.salesorder.SalesOrderDO;
import com.dofast.module.channel.dal.mysql.salesorder.SalesOrderMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.channel.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link SalesOrderServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(SalesOrderServiceImpl.class)
public class SalesChannelOrderServiceImplTest extends BaseDbUnitTest {

    @Resource
    private SalesOrderServiceImpl salesOrderService;

    @Resource
    private SalesOrderMapper salesOrderMapper;

    @Test
    public void testCreateSalesOrder_success() {
        // 准备参数
        SalesOrderCreateReqVO reqVO = randomPojo(SalesOrderCreateReqVO.class);

        // 调用
        Long salesOrderId = salesOrderService.createSalesOrder(reqVO);
        // 断言
        assertNotNull(salesOrderId);
        // 校验记录的属性是否正确
        SalesOrderDO salesOrder = salesOrderMapper.selectById(salesOrderId);
        assertPojoEquals(reqVO, salesOrder);
    }

    @Test
    public void testUpdateSalesOrder_success() {
        // mock 数据
        SalesOrderDO dbSalesOrder = randomPojo(SalesOrderDO.class);
        salesOrderMapper.insert(dbSalesOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SalesOrderUpdateReqVO reqVO = randomPojo(SalesOrderUpdateReqVO.class, o -> {
            o.setId(dbSalesOrder.getId()); // 设置更新的 ID
        });

        // 调用
        salesOrderService.updateSalesOrder(reqVO);
        // 校验是否更新正确
        SalesOrderDO salesOrder = salesOrderMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, salesOrder);
    }

    @Test
    public void testUpdateSalesOrder_notExists() {
        // 准备参数
        SalesOrderUpdateReqVO reqVO = randomPojo(SalesOrderUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> salesOrderService.updateSalesOrder(reqVO), SALES_ORDER_NOT_EXISTS);
    }

    @Test
    public void testDeleteSalesOrder_success() {
        // mock 数据
        SalesOrderDO dbSalesOrder = randomPojo(SalesOrderDO.class);
        salesOrderMapper.insert(dbSalesOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbSalesOrder.getId();

        // 调用
        salesOrderService.deleteSalesOrder(id);
       // 校验数据不存在了
       assertNull(salesOrderMapper.selectById(id));
    }

    @Test
    public void testDeleteSalesOrder_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> salesOrderService.deleteSalesOrder(id), SALES_ORDER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSalesOrderPage() {
       // mock 数据
       SalesOrderDO dbSalesOrder = randomPojo(SalesOrderDO.class, o -> { // 等会查询到
           o.setSaleNo(null);
           o.setTitle(null);
           o.setSettlementMethod(null);
           o.setPrice(null);
           o.setSaler(null);
           o.setCreateTime(null);
       });
       salesOrderMapper.insert(dbSalesOrder);
       // 测试 saleNo 不匹配
       salesOrderMapper.insert(cloneIgnoreId(dbSalesOrder, o -> o.setSaleNo(null)));
       // 测试 title 不匹配
       salesOrderMapper.insert(cloneIgnoreId(dbSalesOrder, o -> o.setTitle(null)));
       // 测试 settlement 不匹配
       salesOrderMapper.insert(cloneIgnoreId(dbSalesOrder, o -> o.setSettlementMethod(null)));
       // 测试 price 不匹配
       salesOrderMapper.insert(cloneIgnoreId(dbSalesOrder, o -> o.setPrice(null)));
       // 测试 saler 不匹配
       salesOrderMapper.insert(cloneIgnoreId(dbSalesOrder, o -> o.setSaler(null)));
       // 测试 createTime 不匹配
       salesOrderMapper.insert(cloneIgnoreId(dbSalesOrder, o -> o.setCreateTime(null)));
       // 准备参数
       SalesOrderPageReqVO reqVO = new SalesOrderPageReqVO();
       reqVO.setSaleNo(null);
       reqVO.setTitle(null);
       reqVO.setSettlementMethod(null);
       reqVO.setPrice(null);
       reqVO.setSaler(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<SalesOrderDO> pageResult = salesOrderService.getSalesOrderPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbSalesOrder, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSalesOrderList() {
       // mock 数据
       SalesOrderDO dbSalesOrder = randomPojo(SalesOrderDO.class, o -> { // 等会查询到
           o.setSaleNo(null);
           o.setTitle(null);
           o.setSettlementMethod(null);
           o.setPrice(null);
           o.setSaler(null);
           o.setCreateTime(null);
       });
       salesOrderMapper.insert(dbSalesOrder);
       // 测试 saleNo 不匹配
       salesOrderMapper.insert(cloneIgnoreId(dbSalesOrder, o -> o.setSaleNo(null)));
       // 测试 title 不匹配
       salesOrderMapper.insert(cloneIgnoreId(dbSalesOrder, o -> o.setTitle(null)));
       // 测试 settlement 不匹配
       salesOrderMapper.insert(cloneIgnoreId(dbSalesOrder, o -> o.setSettlementMethod(null)));
       // 测试 price 不匹配
       salesOrderMapper.insert(cloneIgnoreId(dbSalesOrder, o -> o.setPrice(null)));
       // 测试 saler 不匹配
       salesOrderMapper.insert(cloneIgnoreId(dbSalesOrder, o -> o.setSaler(null)));
       // 测试 createTime 不匹配
       salesOrderMapper.insert(cloneIgnoreId(dbSalesOrder, o -> o.setCreateTime(null)));
       // 准备参数
       SalesOrderExportReqVO reqVO = new SalesOrderExportReqVO();
       reqVO.setSaleNo(null);
       reqVO.setTitle(null);
       reqVO.setSettlementMethod(null);
       reqVO.setPrice(null);
       reqVO.setSaler(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<SalesOrderDO> list = salesOrderService.getSalesOrderList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbSalesOrder, list.get(0));
    }

}
