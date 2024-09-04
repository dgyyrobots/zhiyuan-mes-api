package com.dofast.module.system.service.systemconfig;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.system.controller.admin.systemconfig.vo.*;
import com.dofast.module.system.dal.dataobject.systemconfig.SystemConfigDO;
import com.dofast.module.system.dal.mysql.systemconfig.SystemConfigMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.system.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link SystemConfigServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(SystemConfigServiceImpl.class)
public class SystemConfigServiceImplTest extends BaseDbUnitTest {

    @Resource
    private SystemConfigServiceImpl configService;

    @Resource
    private SystemConfigMapper configMapper;

    @Test
    public void testCreateConfig_success() {
        // 准备参数
        SystemConfigCreateReqVO reqVO = randomPojo(SystemConfigCreateReqVO.class);

        // 调用
        Integer configId = configService.createConfig(reqVO);
        // 断言
        assertNotNull(configId);
        // 校验记录的属性是否正确
        SystemConfigDO config = configMapper.selectById(configId);
        assertPojoEquals(reqVO, config);
    }

    @Test
    public void testUpdateConfig_success() {
        // mock 数据
        SystemConfigDO dbConfig = randomPojo(SystemConfigDO.class);
        configMapper.insert(dbConfig);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SystemConfigUpdateReqVO reqVO = randomPojo(SystemConfigUpdateReqVO.class, o -> {
            o.setId(dbConfig.getId()); // 设置更新的 ID
        });

        // 调用
        configService.updateConfig(reqVO);
        // 校验是否更新正确
        SystemConfigDO config = configMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, config);
    }

    @Test
    public void testUpdateConfig_notExists() {
        // 准备参数
        SystemConfigUpdateReqVO reqVO = randomPojo(SystemConfigUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> configService.updateConfig(reqVO), CONFIG_NOT_EXISTS);
    }

    @Test
    public void testDeleteConfig_success() {
        // mock 数据
        SystemConfigDO dbConfig = randomPojo(SystemConfigDO.class);
        configMapper.insert(dbConfig);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbConfig.getId();

        // 调用
        configService.deleteConfig(id);
       // 校验数据不存在了
       assertNull(configMapper.selectById(id));
    }

    @Test
    public void testDeleteConfig_notExists() {
        // 准备参数
        Integer id = 0;// randomIntegerId();

        // 调用, 并断言异常
        assertServiceException(() -> configService.deleteConfig(id), CONFIG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetConfigPage() {
       // mock 数据
       SystemConfigDO dbConfig = randomPojo(SystemConfigDO.class, o -> { // 等会查询到
           o.setAppModule(null);
           o.setConfigKey(null);
           o.setValue(null);
           o.setConfigDesc(null);
           o.setIsUse(null);
           o.setCreateTime(null);
       });
       configMapper.insert(dbConfig);
       // 测试 appModule 不匹配
       configMapper.insert(cloneIgnoreId(dbConfig, o -> o.setAppModule(null)));
       // 测试 configKey 不匹配
       configMapper.insert(cloneIgnoreId(dbConfig, o -> o.setConfigKey(null)));
       // 测试 value 不匹配
       configMapper.insert(cloneIgnoreId(dbConfig, o -> o.setValue(null)));
       // 测试 configDesc 不匹配
       configMapper.insert(cloneIgnoreId(dbConfig, o -> o.setConfigDesc(null)));
       // 测试 isUse 不匹配
       configMapper.insert(cloneIgnoreId(dbConfig, o -> o.setIsUse(null)));
       // 测试 createTime 不匹配
       configMapper.insert(cloneIgnoreId(dbConfig, o -> o.setCreateTime(null)));
       // 准备参数
       SystemConfigPageReqVO reqVO = new SystemConfigPageReqVO();
       reqVO.setAppModule(null);
       reqVO.setConfigKey(null);
       reqVO.setValue(null);
       reqVO.setConfigDesc(null);
       reqVO.setIsUse(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<SystemConfigDO> pageResult = configService.getConfigPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbConfig, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetConfigList() {
       // mock 数据
       SystemConfigDO dbConfig = randomPojo(SystemConfigDO.class, o -> { // 等会查询到
           o.setAppModule(null);
           o.setConfigKey(null);
           o.setValue(null);
           o.setConfigDesc(null);
           o.setIsUse(null);
           o.setCreateTime(null);
       });
       configMapper.insert(dbConfig);
       // 测试 appModule 不匹配
       configMapper.insert(cloneIgnoreId(dbConfig, o -> o.setAppModule(null)));
       // 测试 configKey 不匹配
       configMapper.insert(cloneIgnoreId(dbConfig, o -> o.setConfigKey(null)));
       // 测试 value 不匹配
       configMapper.insert(cloneIgnoreId(dbConfig, o -> o.setValue(null)));
       // 测试 configDesc 不匹配
       configMapper.insert(cloneIgnoreId(dbConfig, o -> o.setConfigDesc(null)));
       // 测试 isUse 不匹配
       configMapper.insert(cloneIgnoreId(dbConfig, o -> o.setIsUse(null)));
       // 测试 createTime 不匹配
       configMapper.insert(cloneIgnoreId(dbConfig, o -> o.setCreateTime(null)));
       // 准备参数
       SystemConfigExportReqVO reqVO = new SystemConfigExportReqVO();
       reqVO.setAppModule(null);
       reqVO.setConfigKey(null);
       reqVO.setValue(null);
       reqVO.setConfigDesc(null);
       reqVO.setIsUse(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<SystemConfigDO> list = configService.getConfigList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbConfig, list.get(0));
    }

}
