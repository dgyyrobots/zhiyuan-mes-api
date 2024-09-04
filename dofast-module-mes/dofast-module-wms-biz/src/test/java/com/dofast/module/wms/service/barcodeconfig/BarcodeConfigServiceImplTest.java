package com.dofast.module.wms.service.barcodeconfig;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.barcodeconfig.vo.*;
import com.dofast.module.wms.dal.dataobject.barcodeconfig.BarcodeConfigDO;
import com.dofast.module.wms.dal.mysql.barcodeconfig.BarcodeConfigMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link BarcodeConfigServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(BarcodeConfigServiceImpl.class)
public class BarcodeConfigServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BarcodeConfigServiceImpl barcodeConfigService;

    @Resource
    private BarcodeConfigMapper barcodeConfigMapper;

    @Test
    public void testCreateBarcodeConfig_success() {
        // 准备参数
        BarcodeConfigCreateReqVO reqVO = randomPojo(BarcodeConfigCreateReqVO.class);

        // 调用
        Long barcodeConfigId = barcodeConfigService.createBarcodeConfig(reqVO);
        // 断言
        assertNotNull(barcodeConfigId);
        // 校验记录的属性是否正确
        BarcodeConfigDO barcodeConfig = barcodeConfigMapper.selectById(barcodeConfigId);
        assertPojoEquals(reqVO, barcodeConfig);
    }

    @Test
    public void testUpdateBarcodeConfig_success() {
        // mock 数据
        BarcodeConfigDO dbBarcodeConfig = randomPojo(BarcodeConfigDO.class);
        barcodeConfigMapper.insert(dbBarcodeConfig);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BarcodeConfigUpdateReqVO reqVO = randomPojo(BarcodeConfigUpdateReqVO.class, o -> {
            o.setId(dbBarcodeConfig.getId()); // 设置更新的 ID
        });

        // 调用
        barcodeConfigService.updateBarcodeConfig(reqVO);
        // 校验是否更新正确
        BarcodeConfigDO barcodeConfig = barcodeConfigMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, barcodeConfig);
    }

    @Test
    public void testUpdateBarcodeConfig_notExists() {
        // 准备参数
        BarcodeConfigUpdateReqVO reqVO = randomPojo(BarcodeConfigUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> barcodeConfigService.updateBarcodeConfig(reqVO), BARCODE_CONFIG_NOT_EXISTS);
    }

    @Test
    public void testDeleteBarcodeConfig_success() {
        // mock 数据
        BarcodeConfigDO dbBarcodeConfig = randomPojo(BarcodeConfigDO.class);
        barcodeConfigMapper.insert(dbBarcodeConfig);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbBarcodeConfig.getId();

        // 调用
        barcodeConfigService.deleteBarcodeConfig(id);
       // 校验数据不存在了
       assertNull(barcodeConfigMapper.selectById(id));
    }

    @Test
    public void testDeleteBarcodeConfig_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> barcodeConfigService.deleteBarcodeConfig(id), BARCODE_CONFIG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBarcodeConfigPage() {
       // mock 数据
       BarcodeConfigDO dbBarcodeConfig = randomPojo(BarcodeConfigDO.class, o -> { // 等会查询到
           o.setBarcodeFormart(null);
           o.setBarcodeType(null);
           o.setContentFormart(null);
           o.setContentExample(null);
           o.setAutoGenFlag(null);
           o.setDefaultTemplate(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       barcodeConfigMapper.insert(dbBarcodeConfig);
       // 测试 barcodeFormart 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setBarcodeFormart(null)));
       // 测试 barcodeType 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setBarcodeType(null)));
       // 测试 contentFormart 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setContentFormart(null)));
       // 测试 contentExample 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setContentExample(null)));
       // 测试 autoGenFlag 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setAutoGenFlag(null)));
       // 测试 defaultTemplate 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setDefaultTemplate(null)));
       // 测试 enableFlag 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setCreateTime(null)));
       // 准备参数
       BarcodeConfigPageReqVO reqVO = new BarcodeConfigPageReqVO();
       reqVO.setBarcodeFormart(null);
       reqVO.setBarcodeType(null);
       reqVO.setContentFormart(null);
       reqVO.setContentExample(null);
       reqVO.setAutoGenFlag(null);
       reqVO.setDefaultTemplate(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<BarcodeConfigDO> pageResult = barcodeConfigService.getBarcodeConfigPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbBarcodeConfig, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBarcodeConfigList() {
       // mock 数据
       BarcodeConfigDO dbBarcodeConfig = randomPojo(BarcodeConfigDO.class, o -> { // 等会查询到
           o.setBarcodeFormart(null);
           o.setBarcodeType(null);
           o.setContentFormart(null);
           o.setContentExample(null);
           o.setAutoGenFlag(null);
           o.setDefaultTemplate(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       barcodeConfigMapper.insert(dbBarcodeConfig);
       // 测试 barcodeFormart 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setBarcodeFormart(null)));
       // 测试 barcodeType 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setBarcodeType(null)));
       // 测试 contentFormart 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setContentFormart(null)));
       // 测试 contentExample 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setContentExample(null)));
       // 测试 autoGenFlag 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setAutoGenFlag(null)));
       // 测试 defaultTemplate 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setDefaultTemplate(null)));
       // 测试 enableFlag 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       barcodeConfigMapper.insert(cloneIgnoreId(dbBarcodeConfig, o -> o.setCreateTime(null)));
       // 准备参数
       BarcodeConfigExportReqVO reqVO = new BarcodeConfigExportReqVO();
       reqVO.setBarcodeFormart(null);
       reqVO.setBarcodeType(null);
       reqVO.setContentFormart(null);
       reqVO.setContentExample(null);
       reqVO.setAutoGenFlag(null);
       reqVO.setDefaultTemplate(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<BarcodeConfigDO> list = barcodeConfigService.getBarcodeConfigList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbBarcodeConfig, list.get(0));
    }

}
