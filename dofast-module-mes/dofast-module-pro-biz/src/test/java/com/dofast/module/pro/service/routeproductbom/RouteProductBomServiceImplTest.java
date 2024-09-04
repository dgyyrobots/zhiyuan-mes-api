package com.dofast.module.pro.service.routeproductbom;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.routeproductbom.vo.*;
import com.dofast.module.pro.dal.dataobject.routeproductbom.RouteProductBomDO;
import com.dofast.module.pro.dal.mysql.routeproductbom.RouteProductBomMapper;
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
 * {@link RouteProductBomServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(RouteProductBomServiceImpl.class)
public class RouteProductBomServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RouteProductBomServiceImpl routeProductBomService;

    @Resource
    private RouteProductBomMapper routeProductBomMapper;

    @Test
    public void testCreateRouteProductBom_success() {
        // 准备参数
        RouteProductBomCreateReqVO reqVO = randomPojo(RouteProductBomCreateReqVO.class);

        // 调用
        Long routeProductBomId = routeProductBomService.createRouteProductBom(reqVO);
        // 断言
        assertNotNull(routeProductBomId);
        // 校验记录的属性是否正确
        RouteProductBomDO routeProductBom = routeProductBomMapper.selectById(routeProductBomId);
        assertPojoEquals(reqVO, routeProductBom);
    }

    @Test
    public void testUpdateRouteProductBom_success() {
        // mock 数据
        RouteProductBomDO dbRouteProductBom = randomPojo(RouteProductBomDO.class);
        routeProductBomMapper.insert(dbRouteProductBom);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RouteProductBomUpdateReqVO reqVO = randomPojo(RouteProductBomUpdateReqVO.class, o -> {
            o.setId(dbRouteProductBom.getId()); // 设置更新的 ID
        });

        // 调用
        routeProductBomService.updateRouteProductBom(reqVO);
        // 校验是否更新正确
        RouteProductBomDO routeProductBom = routeProductBomMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, routeProductBom);
    }

    @Test
    public void testUpdateRouteProductBom_notExists() {
        // 准备参数
        RouteProductBomUpdateReqVO reqVO = randomPojo(RouteProductBomUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> routeProductBomService.updateRouteProductBom(reqVO), ROUTE_PRODUCT_BOM_NOT_EXISTS);
    }

    @Test
    public void testDeleteRouteProductBom_success() {
        // mock 数据
        RouteProductBomDO dbRouteProductBom = randomPojo(RouteProductBomDO.class);
        routeProductBomMapper.insert(dbRouteProductBom);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRouteProductBom.getId();

        // 调用
        routeProductBomService.deleteRouteProductBom(id);
       // 校验数据不存在了
       assertNull(routeProductBomMapper.selectById(id));
    }

    @Test
    public void testDeleteRouteProductBom_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> routeProductBomService.deleteRouteProductBom(id), ROUTE_PRODUCT_BOM_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRouteProductBomPage() {
       // mock 数据
       RouteProductBomDO dbRouteProductBom = randomPojo(RouteProductBomDO.class, o -> { // 等会查询到
           o.setRouteId(null);
           o.setProcessId(null);
           o.setProductId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       routeProductBomMapper.insert(dbRouteProductBom);
       // 测试 routeId 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setRouteId(null)));
       // 测试 processId 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setProcessId(null)));
       // 测试 productId 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setProductId(null)));
       // 测试 itemId 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setUnitOfMeasure(null)));
       // 测试 quantity 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setQuantity(null)));
       // 测试 remark 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setCreateTime(null)));
       // 准备参数
       RouteProductBomPageReqVO reqVO = new RouteProductBomPageReqVO();
       reqVO.setRouteId(null);
       reqVO.setProcessId(null);
       reqVO.setProductId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<RouteProductBomDO> pageResult = routeProductBomService.getRouteProductBomPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRouteProductBom, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRouteProductBomList() {
       // mock 数据
       RouteProductBomDO dbRouteProductBom = randomPojo(RouteProductBomDO.class, o -> { // 等会查询到
           o.setRouteId(null);
           o.setProcessId(null);
           o.setProductId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       routeProductBomMapper.insert(dbRouteProductBom);
       // 测试 routeId 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setRouteId(null)));
       // 测试 processId 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setProcessId(null)));
       // 测试 productId 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setProductId(null)));
       // 测试 itemId 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setUnitOfMeasure(null)));
       // 测试 quantity 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setQuantity(null)));
       // 测试 remark 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       routeProductBomMapper.insert(cloneIgnoreId(dbRouteProductBom, o -> o.setCreateTime(null)));
       // 准备参数
       RouteProductBomExportReqVO reqVO = new RouteProductBomExportReqVO();
       reqVO.setRouteId(null);
       reqVO.setProcessId(null);
       reqVO.setProductId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<RouteProductBomDO> list = routeProductBomService.getRouteProductBomList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRouteProductBom, list.get(0));
    }

}
