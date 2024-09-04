package com.dofast.module.iot.service.alertlog;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.iot.controller.admin.alertlog.vo.*;
import com.dofast.module.iot.dal.dataobject.alertlog.AlertLogDO;
import com.dofast.module.iot.dal.mysql.alertlog.AlertLogMapper;
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
 * {@link AlertLogServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(AlertLogServiceImpl.class)
public class AlertLogServiceImplTest extends BaseDbUnitTest {

    @Resource
    private AlertLogServiceImpl alertLogService;

    @Resource
    private AlertLogMapper alertLogMapper;

    @Test
    public void testCreateAlertLog_success() {
        // 准备参数
        AlertLogCreateReqVO reqVO = randomPojo(AlertLogCreateReqVO.class);

        // 调用
        Long alertLogId = alertLogService.createAlertLog(reqVO);
        // 断言
        assertNotNull(alertLogId);
        // 校验记录的属性是否正确
        AlertLogDO alertLog = alertLogMapper.selectById(alertLogId);
        assertPojoEquals(reqVO, alertLog);
    }

    @Test
    public void testUpdateAlertLog_success() {
        // mock 数据
        AlertLogDO dbAlertLog = randomPojo(AlertLogDO.class);
        alertLogMapper.insert(dbAlertLog);// @Sql: 先插入出一条存在的数据
        // 准备参数
        AlertLogUpdateReqVO reqVO = randomPojo(AlertLogUpdateReqVO.class, o -> {
            o.setId(dbAlertLog.getId()); // 设置更新的 ID
        });

        // 调用
        alertLogService.updateAlertLog(reqVO);
        // 校验是否更新正确
        AlertLogDO alertLog = alertLogMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, alertLog);
    }

    @Test
    public void testUpdateAlertLog_notExists() {
        // 准备参数
        AlertLogUpdateReqVO reqVO = randomPojo(AlertLogUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> alertLogService.updateAlertLog(reqVO), ALERT_LOG_NOT_EXISTS);
    }

    @Test
    public void testDeleteAlertLog_success() {
        // mock 数据
        AlertLogDO dbAlertLog = randomPojo(AlertLogDO.class);
        alertLogMapper.insert(dbAlertLog);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbAlertLog.getId();

        // 调用
        alertLogService.deleteAlertLog(id);
       // 校验数据不存在了
       assertNull(alertLogMapper.selectById(id));
    }

    @Test
    public void testDeleteAlertLog_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> alertLogService.deleteAlertLog(id), ALERT_LOG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetAlertLogPage() {
       // mock 数据
       AlertLogDO dbAlertLog = randomPojo(AlertLogDO.class, o -> { // 等会查询到
           o.setAlertName(null);
           o.setAlertLevel(null);
           o.setStatus(null);
           o.setProductId(null);
           o.setProductName(null);
           o.setDeviceId(null);
           o.setDeviceName(null);
           o.setUserId(null);
           o.setUserName(null);
           o.setRemark(null);
           o.setType(null);
           o.setCreateTime(null);
       });
       alertLogMapper.insert(dbAlertLog);
       // 测试 alertName 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setAlertName(null)));
       // 测试 alertLevel 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setAlertLevel(null)));
       // 测试 status 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setStatus(null)));
       // 测试 productId 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setProductId(null)));
       // 测试 productName 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setProductName(null)));
       // 测试 deviceId 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setDeviceId(null)));
       // 测试 deviceName 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setDeviceName(null)));
       // 测试 userId 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setUserId(null)));
       // 测试 userName 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setUserName(null)));
       // 测试 remark 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setRemark(null)));
       // 测试 type 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setType(null)));
       // 测试 createTime 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setCreateTime(null)));
       // 准备参数
       AlertLogPageReqVO reqVO = new AlertLogPageReqVO();
       reqVO.setAlertName(null);
       reqVO.setAlertLevel(null);
       reqVO.setStatus(null);
       reqVO.setProductId(null);
       reqVO.setProductName(null);
       reqVO.setDeviceId(null);
       reqVO.setDeviceName(null);
       reqVO.setUserId(null);
       reqVO.setUserName(null);
       reqVO.setRemark(null);
       reqVO.setType(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<AlertLogDO> pageResult = alertLogService.getAlertLogPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbAlertLog, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetAlertLogList() {
       // mock 数据
       AlertLogDO dbAlertLog = randomPojo(AlertLogDO.class, o -> { // 等会查询到
           o.setAlertName(null);
           o.setAlertLevel(null);
           o.setStatus(null);
           o.setProductId(null);
           o.setProductName(null);
           o.setDeviceId(null);
           o.setDeviceName(null);
           o.setUserId(null);
           o.setUserName(null);
           o.setRemark(null);
           o.setType(null);
           o.setCreateTime(null);
       });
       alertLogMapper.insert(dbAlertLog);
       // 测试 alertName 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setAlertName(null)));
       // 测试 alertLevel 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setAlertLevel(null)));
       // 测试 status 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setStatus(null)));
       // 测试 productId 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setProductId(null)));
       // 测试 productName 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setProductName(null)));
       // 测试 deviceId 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setDeviceId(null)));
       // 测试 deviceName 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setDeviceName(null)));
       // 测试 userId 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setUserId(null)));
       // 测试 userName 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setUserName(null)));
       // 测试 remark 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setRemark(null)));
       // 测试 type 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setType(null)));
       // 测试 createTime 不匹配
       alertLogMapper.insert(cloneIgnoreId(dbAlertLog, o -> o.setCreateTime(null)));
       // 准备参数
       AlertLogExportReqVO reqVO = new AlertLogExportReqVO();
       reqVO.setAlertName(null);
       reqVO.setAlertLevel(null);
       reqVO.setStatus(null);
       reqVO.setProductId(null);
       reqVO.setProductName(null);
       reqVO.setDeviceId(null);
       reqVO.setDeviceName(null);
       reqVO.setUserId(null);
       reqVO.setUserName(null);
       reqVO.setRemark(null);
       reqVO.setType(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<AlertLogDO> list = alertLogService.getAlertLogList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbAlertLog, list.get(0));
    }

}
