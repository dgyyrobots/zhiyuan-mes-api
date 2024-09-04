package com.dofast.module.pro.service.route;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.route.vo.*;
import com.dofast.module.pro.dal.dataobject.route.RouteDO;
import com.dofast.module.pro.dal.mysql.route.RouteMapper;
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
 * {@link RouteServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(RouteServiceImpl.class)
public class RouteServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RouteServiceImpl routeService;

    @Resource
    private RouteMapper routeMapper;

    @Test
    public void testCreateRoute_success() {
        // 准备参数
        RouteCreateReqVO reqVO = randomPojo(RouteCreateReqVO.class);

        // 调用
        Long routeId = routeService.createRoute(reqVO);
        // 断言
        assertNotNull(routeId);
        // 校验记录的属性是否正确
        RouteDO route = routeMapper.selectById(routeId);
        assertPojoEquals(reqVO, route);
    }

    @Test
    public void testUpdateRoute_success() {
        // mock 数据
        RouteDO dbRoute = randomPojo(RouteDO.class);
        routeMapper.insert(dbRoute);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RouteUpdateReqVO reqVO = randomPojo(RouteUpdateReqVO.class, o -> {
            o.setId(dbRoute.getId()); // 设置更新的 ID
        });

        // 调用
        routeService.updateRoute(reqVO);
        // 校验是否更新正确
        RouteDO route = routeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, route);
    }

    @Test
    public void testUpdateRoute_notExists() {
        // 准备参数
        RouteUpdateReqVO reqVO = randomPojo(RouteUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> routeService.updateRoute(reqVO), ROUTE_NOT_EXISTS);
    }

    @Test
    public void testDeleteRoute_success() {
        // mock 数据
        RouteDO dbRoute = randomPojo(RouteDO.class);
        routeMapper.insert(dbRoute);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRoute.getId();

        // 调用
        routeService.deleteRoute(id);
       // 校验数据不存在了
       assertNull(routeMapper.selectById(id));
    }

    @Test
    public void testDeleteRoute_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> routeService.deleteRoute(id), ROUTE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRoutePage() {
       // mock 数据
       RouteDO dbRoute = randomPojo(RouteDO.class, o -> { // 等会查询到
           o.setRouteCode(null);
           o.setRouteName(null);
           o.setRouteDesc(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       routeMapper.insert(dbRoute);
       // 测试 routeCode 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setRouteCode(null)));
       // 测试 routeName 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setRouteName(null)));
       // 测试 routeDesc 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setRouteDesc(null)));
       // 测试 enableFlag 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setCreateTime(null)));
       // 准备参数
       RoutePageReqVO reqVO = new RoutePageReqVO();
       reqVO.setRouteCode(null);
       reqVO.setRouteName(null);
       reqVO.setRouteDesc(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<RouteDO> pageResult = routeService.getRoutePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRoute, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRouteList() {
       // mock 数据
       RouteDO dbRoute = randomPojo(RouteDO.class, o -> { // 等会查询到
           o.setRouteCode(null);
           o.setRouteName(null);
           o.setRouteDesc(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       routeMapper.insert(dbRoute);
       // 测试 routeCode 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setRouteCode(null)));
       // 测试 routeName 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setRouteName(null)));
       // 测试 routeDesc 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setRouteDesc(null)));
       // 测试 enableFlag 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       routeMapper.insert(cloneIgnoreId(dbRoute, o -> o.setCreateTime(null)));
       // 准备参数
       RouteExportReqVO reqVO = new RouteExportReqVO();
       reqVO.setRouteCode(null);
       reqVO.setRouteName(null);
       reqVO.setRouteDesc(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<RouteDO> list = routeService.getRouteList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRoute, list.get(0));
    }

}
