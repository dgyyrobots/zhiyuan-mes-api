package com.dofast.module.pro.service.routeproduct;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.routeproduct.vo.*;
import com.dofast.module.pro.dal.dataobject.routeproduct.RouteProductDO;
import com.dofast.module.pro.dal.mysql.routeproduct.RouteProductMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link RouteProductServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(RouteProductServiceImpl.class)
public class RouteProductServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RouteProductServiceImpl routeProductService;

    @Resource
    private RouteProductMapper routeProductMapper;


    @Test
    public void testCreateRouteProduct_success() {
        // 准备参数
        RouteProductCreateReqVO reqVO = randomPojo(RouteProductCreateReqVO.class);

        // 调用
        Long routeProductId = routeProductService.createRouteProduct(reqVO);
        // 断言
        assertNotNull(routeProductId);
        // 校验记录的属性是否正确
        RouteProductDO routeProduct = routeProductMapper.selectById(routeProductId);
        assertPojoEquals(reqVO, routeProduct);
    }

    @Test
    public void testUpdateRouteProduct_success() {
        // mock 数据
        RouteProductDO dbRouteProduct = randomPojo(RouteProductDO.class);
        routeProductMapper.insert(dbRouteProduct);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RouteProductUpdateReqVO reqVO = randomPojo(RouteProductUpdateReqVO.class, o -> {
            o.setId(dbRouteProduct.getId()); // 设置更新的 ID
        });

        // 调用
        routeProductService.updateRouteProduct(reqVO);
        // 校验是否更新正确
        RouteProductDO routeProduct = routeProductMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, routeProduct);
    }

    @Test
    public void testUpdateRouteProduct_notExists() {
        // 准备参数
        RouteProductUpdateReqVO reqVO = randomPojo(RouteProductUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> routeProductService.updateRouteProduct(reqVO), ROUTE_PRODUCT_NOT_EXISTS);
    }

    @Test
    public void testDeleteRouteProduct_success() {
        // mock 数据
        RouteProductDO dbRouteProduct = randomPojo(RouteProductDO.class);
        routeProductMapper.insert(dbRouteProduct);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRouteProduct.getId();

        // 调用
        routeProductService.deleteRouteProduct(id);
       // 校验数据不存在了
       assertNull(routeProductMapper.selectById(id));
    }

    @Test
    public void testDeleteRouteProduct_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> routeProductService.deleteRouteProduct(id), ROUTE_PRODUCT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRouteProductPage() {
       // mock 数据
       RouteProductDO dbRouteProduct = randomPojo(RouteProductDO.class, o -> { // 等会查询到
           o.setRouteId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantity(null);
           o.setProductionTime(null);
           o.setTimeUnitType(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       routeProductMapper.insert(dbRouteProduct);
       // 测试 routeId 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setRouteId(null)));
       // 测试 itemId 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setUnitOfMeasure(null)));
       // 测试 quantity 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setQuantity(null)));
       // 测试 productionTime 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setProductionTime(null)));
       // 测试 timeUnitType 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setTimeUnitType(null)));
       // 测试 remark 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setCreateTime(null)));
       // 准备参数
       RouteProductPageReqVO reqVO = new RouteProductPageReqVO();
       reqVO.setRouteId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantity(null);
       reqVO.setTimeUnitType(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);

       // 调用
       PageResult<RouteProductDO> pageResult = routeProductService.getRouteProductPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRouteProduct, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRouteProductList() {
       // mock 数据
       RouteProductDO dbRouteProduct = randomPojo(RouteProductDO.class, o -> { // 等会查询到
           o.setRouteId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantity(null);
           o.setProductionTime(null);
           o.setTimeUnitType(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       routeProductMapper.insert(dbRouteProduct);
       // 测试 routeId 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setRouteId(null)));
       // 测试 itemId 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setUnitOfMeasure(null)));
       // 测试 quantity 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setQuantity(null)));
       // 测试 productionTime 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setProductionTime(null)));
       // 测试 timeUnitType 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setTimeUnitType(null)));
       // 测试 remark 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       routeProductMapper.insert(cloneIgnoreId(dbRouteProduct, o -> o.setCreateTime(null)));
       // 准备参数
       RouteProductExportReqVO reqVO = new RouteProductExportReqVO();
       reqVO.setRouteId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantity(null);
       reqVO.setTimeUnitType(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);

       // 调用
       List<RouteProductDO> list = routeProductService.getRouteProductList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRouteProduct, list.get(0));
    }



}
