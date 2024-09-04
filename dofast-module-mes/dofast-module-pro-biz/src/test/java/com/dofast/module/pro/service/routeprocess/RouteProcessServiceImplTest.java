package com.dofast.module.pro.service.routeprocess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.routeprocess.vo.*;
import com.dofast.module.pro.dal.dataobject.routeprocess.RouteProcessDO;
import com.dofast.module.pro.dal.mysql.routeprocess.RouteProcessMapper;
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
 * {@link RouteProcessServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(RouteProcessServiceImpl.class)
public class RouteProcessServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RouteProcessServiceImpl routeProcessService;

    @Resource
    private RouteProcessMapper routeProcessMapper;

    @Test
    public void testCreateRouteProcess_success() {
        // 准备参数
        RouteProcessCreateReqVO reqVO = randomPojo(RouteProcessCreateReqVO.class);

        // 调用
        Long routeProcessId = routeProcessService.createRouteProcess(reqVO);
        // 断言
        assertNotNull(routeProcessId);
        // 校验记录的属性是否正确
        RouteProcessDO routeProcess = routeProcessMapper.selectById(routeProcessId);
        assertPojoEquals(reqVO, routeProcess);
    }

    @Test
    public void testUpdateRouteProcess_success() {
        // mock 数据
        RouteProcessDO dbRouteProcess = randomPojo(RouteProcessDO.class);
        routeProcessMapper.insert(dbRouteProcess);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RouteProcessUpdateReqVO reqVO = randomPojo(RouteProcessUpdateReqVO.class, o -> {
            o.setId(dbRouteProcess.getId()); // 设置更新的 ID
        });

        // 调用
        routeProcessService.updateRouteProcess(reqVO);
        // 校验是否更新正确
        RouteProcessDO routeProcess = routeProcessMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, routeProcess);
    }

    @Test
    public void testUpdateRouteProcess_notExists() {
        // 准备参数
        RouteProcessUpdateReqVO reqVO = randomPojo(RouteProcessUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> routeProcessService.updateRouteProcess(reqVO), ROUTE_PROCESS_NOT_EXISTS);
    }

    @Test
    public void testDeleteRouteProcess_success() {
        // mock 数据
        RouteProcessDO dbRouteProcess = randomPojo(RouteProcessDO.class);
        routeProcessMapper.insert(dbRouteProcess);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRouteProcess.getId();

        // 调用
        routeProcessService.deleteRouteProcess(id);
       // 校验数据不存在了
       assertNull(routeProcessMapper.selectById(id));
    }

    @Test
    public void testDeleteRouteProcess_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> routeProcessService.deleteRouteProcess(id), ROUTE_PROCESS_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRouteProcessPage() {
       // mock 数据
       RouteProcessDO dbRouteProcess = randomPojo(RouteProcessDO.class, o -> { // 等会查询到
           o.setRouteId(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setOrderNum(null);
           o.setNextProcessId(null);
           o.setNextProcessCode(null);
           o.setNextProcessName(null);
           o.setLinkType(null);
           o.setDefaultPreTime(null);
           o.setDefaultSufTime(null);
           o.setColorCode(null);
           o.setKeyFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       routeProcessMapper.insert(dbRouteProcess);
       // 测试 routeId 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setRouteId(null)));
       // 测试 processId 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setProcessName(null)));
       // 测试 orderNum 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setOrderNum(null)));
       // 测试 nextProcessId 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setNextProcessId(null)));
       // 测试 nextProcessCode 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setNextProcessCode(null)));
       // 测试 nextProcessName 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setNextProcessName(null)));
       // 测试 linkType 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setLinkType(null)));
       // 测试 defaultPreTime 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setDefaultPreTime(null)));
       // 测试 defaultSufTime 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setDefaultSufTime(null)));
       // 测试 colorCode 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setColorCode(null)));
       // 测试 keyFlag 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setKeyFlag(null)));
       // 测试 remark 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setCreateTime(null)));
       // 准备参数
       RouteProcessPageReqVO reqVO = new RouteProcessPageReqVO();
       reqVO.setRouteId(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setOrderNum(null);
       reqVO.setNextProcessId(null);
       reqVO.setNextProcessCode(null);
       reqVO.setNextProcessName(null);
       reqVO.setLinkType(null);
       reqVO.setColorCode(null);
       reqVO.setKeyFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);

       // 调用
       PageResult<RouteProcessDO> pageResult = routeProcessService.getRouteProcessPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRouteProcess, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRouteProcessList() {
       // mock 数据
       RouteProcessDO dbRouteProcess = randomPojo(RouteProcessDO.class, o -> { // 等会查询到
           o.setRouteId(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setOrderNum(null);
           o.setNextProcessId(null);
           o.setNextProcessCode(null);
           o.setNextProcessName(null);
           o.setLinkType(null);
           o.setDefaultPreTime(null);
           o.setDefaultSufTime(null);
           o.setColorCode(null);
           o.setKeyFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       routeProcessMapper.insert(dbRouteProcess);
       // 测试 routeId 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setRouteId(null)));
       // 测试 processId 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setProcessName(null)));
       // 测试 orderNum 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setOrderNum(null)));
       // 测试 nextProcessId 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setNextProcessId(null)));
       // 测试 nextProcessCode 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setNextProcessCode(null)));
       // 测试 nextProcessName 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setNextProcessName(null)));
       // 测试 linkType 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setLinkType(null)));
       // 测试 defaultPreTime 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setDefaultPreTime(null)));
       // 测试 defaultSufTime 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setDefaultSufTime(null)));
       // 测试 colorCode 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setColorCode(null)));
       // 测试 keyFlag 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setKeyFlag(null)));
       // 测试 remark 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       routeProcessMapper.insert(cloneIgnoreId(dbRouteProcess, o -> o.setCreateTime(null)));
       // 准备参数
       RouteProcessExportReqVO reqVO = new RouteProcessExportReqVO();
       reqVO.setRouteId(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setOrderNum(null);
       reqVO.setNextProcessId(null);
       reqVO.setNextProcessCode(null);
       reqVO.setNextProcessName(null);
       reqVO.setLinkType(null);
       reqVO.setColorCode(null);
       reqVO.setKeyFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);

       // 调用
       List<RouteProcessDO> list = routeProcessService.getRouteProcessList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRouteProcess, list.get(0));
    }

}
