package com.dofast.module.mes.service.electroplatelog;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.mes.controller.admin.electroplatelog.vo.*;
import com.dofast.module.mes.dal.dataobject.electroplatelog.ElectroplateLogDO;
import com.dofast.module.mes.dal.mysql.electroplatelog.ElectroplateLogMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link ElectroplateLogServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(ElectroplateLogServiceImpl.class)
public class ElectroplateLogServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ElectroplateLogServiceImpl electroplateLogService;

    @Resource
    private ElectroplateLogMapper electroplateLogMapper;

    @Test
    public void testCreateElectroplateLog_success() {
        // 准备参数
        ElectroplateLogCreateReqVO reqVO = randomPojo(ElectroplateLogCreateReqVO.class);

        // 调用
        Long electroplateLogId = electroplateLogService.createElectroplateLog(reqVO);
        // 断言
        assertNotNull(electroplateLogId);
        // 校验记录的属性是否正确
        ElectroplateLogDO electroplateLog = electroplateLogMapper.selectById(electroplateLogId);
        assertPojoEquals(reqVO, electroplateLog);
    }

    @Test
    public void testUpdateElectroplateLog_success() {
        // mock 数据
        ElectroplateLogDO dbElectroplateLog = randomPojo(ElectroplateLogDO.class);
        electroplateLogMapper.insert(dbElectroplateLog);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ElectroplateLogUpdateReqVO reqVO = randomPojo(ElectroplateLogUpdateReqVO.class, o -> {
            o.setId(dbElectroplateLog.getId()); // 设置更新的 ID
        });

        // 调用
        electroplateLogService.updateElectroplateLog(reqVO);
        // 校验是否更新正确
        ElectroplateLogDO electroplateLog = electroplateLogMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, electroplateLog);
    }

    @Test
    public void testUpdateElectroplateLog_notExists() {
        // 准备参数
        ElectroplateLogUpdateReqVO reqVO = randomPojo(ElectroplateLogUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> electroplateLogService.updateElectroplateLog(reqVO), ELECTROPLATE_LOG_NOT_EXISTS);
    }

    @Test
    public void testDeleteElectroplateLog_success() {
        // mock 数据
        ElectroplateLogDO dbElectroplateLog = randomPojo(ElectroplateLogDO.class);
        electroplateLogMapper.insert(dbElectroplateLog);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbElectroplateLog.getId();

        // 调用
        electroplateLogService.deleteElectroplateLog(id);
       // 校验数据不存在了
       assertNull(electroplateLogMapper.selectById(id));
    }

    @Test
    public void testDeleteElectroplateLog_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> electroplateLogService.deleteElectroplateLog(id), ELECTROPLATE_LOG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetElectroplateLogPage() {
       // mock 数据
       ElectroplateLogDO dbElectroplateLog = randomPojo(ElectroplateLogDO.class, o -> { // 等会查询到
           o.setMachineryId(null);
           o.setMachineryCode(null);
           o.setMachineryName(null);
           o.setProportion(null);
           o.setTemperature(null);
           o.setPhValue(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       electroplateLogMapper.insert(dbElectroplateLog);
       // 测试 machineryId 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setMachineryId(null)));
       // 测试 machineryCode 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setMachineryCode(null)));
       // 测试 machineryName 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setMachineryName(null)));
       // 测试 proportion 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setProportion(null)));
       // 测试 temperature 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setTemperature(null)));
       // 测试 phValue 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setPhValue(null)));
       // 测试 remark 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setCreateTime(null)));
       // 准备参数
       ElectroplateLogPageReqVO reqVO = new ElectroplateLogPageReqVO();
       reqVO.setMachineryId(null);
       reqVO.setMachineryCode(null);
       reqVO.setMachineryName(null);
       reqVO.setProportion(null);
       reqVO.setTemperature(null);
       reqVO.setPhValue(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ElectroplateLogDO> pageResult = electroplateLogService.getElectroplateLogPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbElectroplateLog, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetElectroplateLogList() {
       // mock 数据
       ElectroplateLogDO dbElectroplateLog = randomPojo(ElectroplateLogDO.class, o -> { // 等会查询到
           o.setMachineryId(null);
           o.setMachineryCode(null);
           o.setMachineryName(null);
           o.setProportion(null);
           o.setTemperature(null);
           o.setPhValue(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       electroplateLogMapper.insert(dbElectroplateLog);
       // 测试 machineryId 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setMachineryId(null)));
       // 测试 machineryCode 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setMachineryCode(null)));
       // 测试 machineryName 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setMachineryName(null)));
       // 测试 proportion 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setProportion(null)));
       // 测试 temperature 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setTemperature(null)));
       // 测试 phValue 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setPhValue(null)));
       // 测试 remark 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       electroplateLogMapper.insert(cloneIgnoreId(dbElectroplateLog, o -> o.setCreateTime(null)));
       // 准备参数
       ElectroplateLogExportReqVO reqVO = new ElectroplateLogExportReqVO();
       reqVO.setMachineryId(null);
       reqVO.setMachineryCode(null);
       reqVO.setMachineryName(null);
       reqVO.setProportion(null);
       reqVO.setTemperature(null);
       reqVO.setPhValue(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ElectroplateLogDO> list = electroplateLogService.getElectroplateLogList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbElectroplateLog, list.get(0));
    }

}
