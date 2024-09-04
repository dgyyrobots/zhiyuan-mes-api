package com.dofast.module.channel.service.shop;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.test.core.ut.BaseDbUnitTest;
import com.dofast.module.channel.controller.admin.shop.vo.ShopCreateReqVO;
import com.dofast.module.channel.controller.admin.shop.vo.ShopExportReqVO;
import com.dofast.module.channel.controller.admin.shop.vo.ShopPageReqVO;
import com.dofast.module.channel.controller.admin.shop.vo.ShopUpdateReqVO;
import com.dofast.module.channel.dal.dataobject.shop.ShopDO;
import com.dofast.module.channel.dal.mysql.shop.ShopMapper;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static com.dofast.framework.common.util.date.LocalDateTimeUtils.buildBetweenTime;
import static com.dofast.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static com.dofast.framework.test.core.util.AssertUtils.assertPojoEquals;
import static com.dofast.framework.test.core.util.AssertUtils.assertServiceException;
import static com.dofast.framework.test.core.util.RandomUtils.randomPojo;
import static com.dofast.framework.test.core.util.RandomUtils.randomString;
import static com.dofast.module.channel.enums.ErrorCodeConstants.SHOP_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link ShopServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(ShopServiceImpl.class)
public class ShopServiceImplTest extends BaseDbUnitTest {

    @Autowired
    private ShopServiceImpl shopService;

    @Autowired
    private ShopMapper shopMapper;

    @Test
    public void testCreateShop_success() {
        // 准备参数
        ShopCreateReqVO reqVO = randomPojo(ShopCreateReqVO.class);

        // 调用
        String shopId = shopService.createShop(reqVO);
        // 断言
        assertNotNull(shopId);
        // 校验记录的属性是否正确
        ShopDO shop = shopMapper.selectById(shopId);
        assertPojoEquals(reqVO, shop);
    }

    @Test
    public void testUpdateShop_success() {
        // mock 数据
        ShopDO dbShop = randomPojo(ShopDO.class);
        shopMapper.insert(dbShop);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ShopUpdateReqVO reqVO = randomPojo(ShopUpdateReqVO.class, o -> {
            o.setId(dbShop.getId()); // 设置更新的 ID
        });

        // 调用
        shopService.updateShop(reqVO);
        // 校验是否更新正确
        ShopDO shop = shopMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, shop);
    }

    @Test
    public void testUpdateShop_notExists() {
        // 准备参数
        ShopUpdateReqVO reqVO = randomPojo(ShopUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> shopService.updateShop(reqVO), SHOP_NOT_EXISTS);
    }

    @Test
    public void testDeleteShop_success() {
        // mock 数据
        ShopDO dbShop = randomPojo(ShopDO.class);
        shopMapper.insert(dbShop);// @Sql: 先插入出一条存在的数据
        // 准备参数
        String id = dbShop.getId();

        // 调用
        shopService.deleteShop(id);
       // 校验数据不存在了
       assertNull(shopMapper.selectById(id));
    }

    @Test
    public void testDeleteShop_notExists() {
        // 准备参数
        String id = randomString();

        // 调用, 并断言异常
        assertServiceException(() -> shopService.deleteShop(id), SHOP_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetShopPage() {
       // mock 数据
       ShopDO dbShop = randomPojo(ShopDO.class, o -> { // 等会查询到
           o.setDisplayName(null);
           o.setName(null);
           o.setShopNick(null);
           o.setShopUrl(null);
           o.setShopId(null);
           o.setShopCode(null);
           o.setChannel(null);
           o.setCreateTime(null);
           o.setLastTime(null);
           o.setRemark(null);
       });
       shopMapper.insert(dbShop);
       // 测试 displayName 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setDisplayName(null)));
       // 测试 name 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setName(null)));
       // 测试 shopNick 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setShopNick(null)));
       // 测试 shopUrl 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setShopUrl(null)));
       // 测试 shopId 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setShopId(null)));
       // 测试 shopCode 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setShopCode(null)));
       // 测试 channel 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setChannel(null)));
       // 测试 createTime 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setCreateTime(null)));
       // 测试 lastTime 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setLastTime(null)));
       // 测试 remark 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setRemark(null)));
       // 准备参数
       ShopPageReqVO reqVO = new ShopPageReqVO();
       reqVO.setDisplayName(null);
       reqVO.setName(null);
       reqVO.setShopNick(null);
       reqVO.setShopUrl(null);
       reqVO.setShopId(null);
       reqVO.setShopCode(null);
       reqVO.setChannel(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setLastTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);

       // 调用
       PageResult<ShopDO> pageResult = shopService.getShopPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbShop, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetShopList() {
       // mock 数据
       ShopDO dbShop = randomPojo(ShopDO.class, o -> { // 等会查询到
           o.setDisplayName(null);
           o.setName(null);
           o.setShopNick(null);
           o.setShopUrl(null);
           o.setShopId(null);
           o.setShopCode(null);
           o.setChannel(null);
           o.setCreateTime(null);
           o.setLastTime(null);
           o.setRemark(null);
       });
       shopMapper.insert(dbShop);
       // 测试 displayName 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setDisplayName(null)));
       // 测试 name 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setName(null)));
       // 测试 shopNick 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setShopNick(null)));
       // 测试 shopUrl 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setShopUrl(null)));
       // 测试 shopId 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setShopId(null)));
       // 测试 shopCode 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setShopCode(null)));
       // 测试 channel 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setChannel(null)));
       // 测试 createTime 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setCreateTime(null)));
       // 测试 lastTime 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setLastTime(null)));
       // 测试 remark 不匹配
       shopMapper.insert(cloneIgnoreId(dbShop, o -> o.setRemark(null)));
       // 准备参数
       ShopExportReqVO reqVO = new ShopExportReqVO();
       reqVO.setDisplayName(null);
       reqVO.setName(null);
       reqVO.setShopNick(null);
       reqVO.setShopUrl(null);
       reqVO.setShopId(null);
       reqVO.setShopCode(null);
       reqVO.setChannel(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setLastTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);

       // 调用
       List<ShopDO> list = shopService.getShopList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbShop, list.get(0));
    }

}
