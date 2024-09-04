package com.dofast.module.iot.service.alert;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.iot.controller.admin.alert.vo.*;
import com.dofast.module.iot.dal.dataobject.alert.AlertDO;
import com.dofast.module.iot.dal.mysql.alert.AlertMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link AlertServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(AlertServiceImpl.class)
public class AlertServiceImplTest extends BaseDbUnitTest {

    @Resource
    private AlertServiceImpl alertService;

    @Resource
    private AlertMapper alertMapper;

    @Test
    public void testCreateAlert_success() {
        // 准备参数
        AlertCreateReqVO reqVO = randomPojo(AlertCreateReqVO.class);

        // 调用
        Long alertId = alertService.createAlert(reqVO);
        // 断言
        assertNotNull(alertId);
        // 校验记录的属性是否正确
        AlertDO alert = alertMapper.selectById(alertId);
        assertPojoEquals(reqVO, alert);
    }

    @Test
    public void testUpdateAlert_success() {
        // mock 数据
        AlertDO dbAlert = randomPojo(AlertDO.class);
        alertMapper.insert(dbAlert);// @Sql: 先插入出一条存在的数据
        // 准备参数
        AlertUpdateReqVO reqVO = randomPojo(AlertUpdateReqVO.class, o -> {
            o.setId(dbAlert.getId()); // 设置更新的 ID
        });

        // 调用
        alertService.updateAlert(reqVO);
        // 校验是否更新正确
        AlertDO alert = alertMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, alert);
    }

    @Test
    public void testUpdateAlert_notExists() {
        // 准备参数
        AlertUpdateReqVO reqVO = randomPojo(AlertUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> alertService.updateAlert(reqVO), ALERT_NOT_EXISTS);
    }

    @Test
    public void testDeleteAlert_success() {
        // mock 数据
        AlertDO dbAlert = randomPojo(AlertDO.class);
        alertMapper.insert(dbAlert);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbAlert.getId();

        // 调用
        alertService.deleteAlert(id);
       // 校验数据不存在了
       assertNull(alertMapper.selectById(id));
    }

    @Test
    public void testDeleteAlert_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> alertService.deleteAlert(id), ALERT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetAlertPage() {
       // mock 数据
       AlertDO dbAlert = randomPojo(AlertDO.class, o -> { // 等会查询到
           o.setAlertName(null);
           o.setAlertLevel(null);
           o.setProductId(null);
           o.setProductName(null);
           o.setTriggers(null);
           o.setActions(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       alertMapper.insert(dbAlert);
       // 测试 alertName 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setAlertName(null)));
       // 测试 alertLevel 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setAlertLevel(null)));
       // 测试 productId 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setProductId(null)));
       // 测试 productName 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setProductName(null)));
       // 测试 triggers 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setTriggers(null)));
       // 测试 actions 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setActions(null)));
       // 测试 status 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setCreateTime(null)));
       // 准备参数
       AlertPageReqVO reqVO = new AlertPageReqVO();
       reqVO.setAlertName(null);
       reqVO.setAlertLevel(null);
       reqVO.setProductId(null);
       reqVO.setProductName(null);
       reqVO.setTriggers(null);
       reqVO.setActions(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<AlertDO> pageResult = alertService.getAlertPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbAlert, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetAlertList() {
       // mock 数据
       AlertDO dbAlert = randomPojo(AlertDO.class, o -> { // 等会查询到
           o.setAlertName(null);
           o.setAlertLevel(null);
           o.setProductId(null);
           o.setProductName(null);
           o.setTriggers(null);
           o.setActions(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       alertMapper.insert(dbAlert);
       // 测试 alertName 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setAlertName(null)));
       // 测试 alertLevel 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setAlertLevel(null)));
       // 测试 productId 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setProductId(null)));
       // 测试 productName 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setProductName(null)));
       // 测试 triggers 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setTriggers(null)));
       // 测试 actions 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setActions(null)));
       // 测试 status 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       alertMapper.insert(cloneIgnoreId(dbAlert, o -> o.setCreateTime(null)));
       // 准备参数
       AlertExportReqVO reqVO = new AlertExportReqVO();
       reqVO.setAlertName(null);
       reqVO.setAlertLevel(null);
       reqVO.setProductId(null);
       reqVO.setProductName(null);
       reqVO.setTriggers(null);
       reqVO.setActions(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<AlertDO> list = alertService.getAlertList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbAlert, list.get(0));
    }

}
