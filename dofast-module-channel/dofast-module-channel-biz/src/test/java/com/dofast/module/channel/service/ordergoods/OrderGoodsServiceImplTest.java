package com.dofast.module.channel.service.ordergoods;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.test.core.ut.BaseDbUnitTest;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsCreateReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsExportReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsPageReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsUpdateReqVO;
import com.dofast.module.channel.dal.dataobject.ordergoods.OrderGoodsDO;
import com.dofast.module.channel.dal.mysql.ordergoods.OrderGoodsMapper;
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
import static com.dofast.module.channel.enums.ErrorCodeConstants.ORDER_GOODS_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;


/**
 * {@link OrderGoodsServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(OrderGoodsServiceImpl.class)
public class OrderGoodsServiceImplTest extends BaseDbUnitTest {

    @Autowired
    private OrderGoodsServiceImpl orderGoodsService;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Test
    public void testCreateOrderGoods_success() {
        // 准备参数
        OrderGoodsCreateReqVO reqVO = randomPojo(OrderGoodsCreateReqVO.class);

        // 调用
        Integer orderGoodsId = orderGoodsService.createOrderGoods(reqVO);
        // 断言
        assertNotNull(orderGoodsId);
        // 校验记录的属性是否正确
        OrderGoodsDO orderGoods = orderGoodsMapper.selectById(orderGoodsId);
        assertPojoEquals(reqVO, orderGoods);
    }

    @Test
    public void testUpdateOrderGoods_success() {
        // mock 数据
        OrderGoodsDO dbOrderGoods = randomPojo(OrderGoodsDO.class);
        orderGoodsMapper.insert(dbOrderGoods);// @Sql: 先插入出一条存在的数据
        // 准备参数
        OrderGoodsUpdateReqVO reqVO = randomPojo(OrderGoodsUpdateReqVO.class, o -> {
            o.setId(dbOrderGoods.getId()); // 设置更新的 ID
        });

        // 调用
        orderGoodsService.updateOrderGoods(reqVO);
        // 校验是否更新正确
        OrderGoodsDO orderGoods = orderGoodsMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, orderGoods);
    }

    @Test
    public void testUpdateOrderGoods_notExists() {
        // 准备参数
        OrderGoodsUpdateReqVO reqVO = randomPojo(OrderGoodsUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> orderGoodsService.updateOrderGoods(reqVO), ORDER_GOODS_NOT_EXISTS);
    }

    @Test
    public void testDeleteOrderGoods_success() {
        // mock 数据
        OrderGoodsDO dbOrderGoods = randomPojo(OrderGoodsDO.class);
        orderGoodsMapper.insert(dbOrderGoods);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbOrderGoods.getId();

        // 调用
        orderGoodsService.deleteOrderGoods(id);
       // 校验数据不存在了
       assertNull(orderGoodsMapper.selectById(id));
    }

    @Test
    public void testDeleteOrderGoods_notExists() {
        // 准备参数
        Integer id = randomInteger();

        // 调用, 并断言异常
        assertServiceException(() -> orderGoodsService.deleteOrderGoods(id), ORDER_GOODS_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderGoodsPage() {
       // mock 数据
       OrderGoodsDO dbOrderGoods = randomPojo(OrderGoodsDO.class, o -> { // 等会查询到
           o.setRefOid(null);
           o.setRefOlId(null);
           o.setRefSpuId(null);
           o.setRefSkuId(null);
           o.setOuterId(null);
           o.setTitle(null);
           o.setStandards(null);
           o.setTotalSellPrice(null);
           o.setTotalPrice(null);
           o.setPrice(null);
           o.setSellPrice(null);
           o.setTotalFee(null);
           o.setSingleFee(null);
           o.setNum(null);
           o.setRefundStatus(null);
           o.setStatus(null);
           o.setPicUrl(null);
           o.setIsFreeGift(null);
           o.setCreateTime(null);
           o.setUserId(null);
       });
       orderGoodsMapper.insert(dbOrderGoods);
       // 测试 refOid 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setRefOid(null)));
       // 测试 refOlId 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setRefOlId(null)));
       // 测试 refSpuId 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setRefSpuId(null)));
       // 测试 refSkuId 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setRefSkuId(null)));
       // 测试 outerId 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setOuterId(null)));
       // 测试 title 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setTitle(null)));
       // 测试 standards 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setStandards(null)));
       // 测试 totalSellPrice 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setTotalSellPrice(null)));
       // 测试 totalPrice 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setTotalPrice(null)));
       // 测试 price 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setPrice(null)));
       // 测试 sellPrice 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setSellPrice(null)));
       // 测试 totalFee 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setTotalFee(null)));
       // 测试 singleFee 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setSingleFee(null)));
       // 测试 num 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setNum(null)));
       // 测试 refundStatus 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setRefundStatus(null)));
       // 测试 status 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setStatus(null)));
       // 测试 picUrl 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setPicUrl(null)));
       // 测试 isFreeGift 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setIsFreeGift(null)));
       // 测试 createTime 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setCreateTime(null)));
       // 测试 userId 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setUserId(null)));
       // 准备参数
       OrderGoodsPageReqVO reqVO = new OrderGoodsPageReqVO();
       reqVO.setRefOid(null);
       reqVO.setRefOlId(null);
       reqVO.setRefSpuId(null);
       reqVO.setRefSkuId(null);
       reqVO.setOuterId(null);
       reqVO.setTitle(null);
       reqVO.setStandards(null);
       reqVO.setTotalSellPrice(null);
       reqVO.setTotalPrice(null);
       reqVO.setPrice(null);
       reqVO.setSellPrice(null);
       reqVO.setTotalFee(null);
       reqVO.setSingleFee(null);
       reqVO.setNum(null);
       reqVO.setRefundStatus(null);
       reqVO.setStatus(null);
       reqVO.setPicUrl(null);
       reqVO.setIsFreeGift(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setUserId(null);

       // 调用
       PageResult<OrderGoodsDO> pageResult = orderGoodsService.getOrderGoodsPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbOrderGoods, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderGoodsList() {
       // mock 数据
       OrderGoodsDO dbOrderGoods = randomPojo(OrderGoodsDO.class, o -> { // 等会查询到
           o.setRefOid(null);
           o.setRefOlId(null);
           o.setRefSpuId(null);
           o.setRefSkuId(null);
           o.setOuterId(null);
           o.setTitle(null);
           o.setStandards(null);
           o.setTotalSellPrice(null);
           o.setTotalPrice(null);
           o.setPrice(null);
           o.setSellPrice(null);
           o.setTotalFee(null);
           o.setSingleFee(null);
           o.setNum(null);
           o.setRefundStatus(null);
           o.setStatus(null);
           o.setPicUrl(null);
           o.setIsFreeGift(null);
           o.setCreateTime(null);
           o.setUserId(null);
       });
       orderGoodsMapper.insert(dbOrderGoods);
       // 测试 refOid 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setRefOid(null)));
       // 测试 refOlId 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setRefOlId(null)));
       // 测试 refSpuId 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setRefSpuId(null)));
       // 测试 refSkuId 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setRefSkuId(null)));
       // 测试 outerId 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setOuterId(null)));
       // 测试 title 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setTitle(null)));
       // 测试 standards 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setStandards(null)));
       // 测试 totalSellPrice 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setTotalSellPrice(null)));
       // 测试 totalPrice 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setTotalPrice(null)));
       // 测试 price 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setPrice(null)));
       // 测试 sellPrice 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setSellPrice(null)));
       // 测试 totalFee 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setTotalFee(null)));
       // 测试 singleFee 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setSingleFee(null)));
       // 测试 num 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setNum(null)));
       // 测试 refundStatus 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setRefundStatus(null)));
       // 测试 status 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setStatus(null)));
       // 测试 picUrl 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setPicUrl(null)));
       // 测试 isFreeGift 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setIsFreeGift(null)));
       // 测试 createTime 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setCreateTime(null)));
       // 测试 userId 不匹配
       orderGoodsMapper.insert(cloneIgnoreId(dbOrderGoods, o -> o.setUserId(null)));
       // 准备参数
       OrderGoodsExportReqVO reqVO = new OrderGoodsExportReqVO();
       reqVO.setRefOid(null);
       reqVO.setRefOlId(null);
       reqVO.setRefSpuId(null);
       reqVO.setRefSkuId(null);
       reqVO.setOuterId(null);
       reqVO.setTitle(null);
       reqVO.setStandards(null);
       reqVO.setTotalSellPrice(null);
       reqVO.setTotalPrice(null);
       reqVO.setPrice(null);
       reqVO.setSellPrice(null);
       reqVO.setTotalFee(null);
       reqVO.setSingleFee(null);
       reqVO.setNum(null);
       reqVO.setRefundStatus(null);
       reqVO.setStatus(null);
       reqVO.setPicUrl(null);
       reqVO.setIsFreeGift(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setUserId(null);

       // 调用
       List<OrderGoodsDO> list = orderGoodsService.getOrderGoodsList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbOrderGoods, list.get(0));
    }

}
