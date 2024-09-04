package com.dofast.module.wms.service.storagearea;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.storagearea.vo.*;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.mysql.storagearea.StorageAreaMapper;
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
 * {@link StorageAreaServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(StorageAreaServiceImpl.class)
public class StorageAreaServiceImplTest extends BaseDbUnitTest {

    @Resource
    private StorageAreaServiceImpl storageAreaService;

    @Resource
    private StorageAreaMapper storageAreaMapper;

    @Test
    public void testCreateStorageArea_success() {
        // 准备参数
        StorageAreaCreateReqVO reqVO = randomPojo(StorageAreaCreateReqVO.class);

        // 调用
        Long storageAreaId = storageAreaService.createStorageArea(reqVO);
        // 断言
        assertNotNull(storageAreaId);
        // 校验记录的属性是否正确
        StorageAreaDO storageArea = storageAreaMapper.selectById(storageAreaId);
        assertPojoEquals(reqVO, storageArea);
    }

    @Test
    public void testUpdateStorageArea_success() {
        // mock 数据
        StorageAreaDO dbStorageArea = randomPojo(StorageAreaDO.class);
        storageAreaMapper.insert(dbStorageArea);// @Sql: 先插入出一条存在的数据
        // 准备参数
        StorageAreaUpdateReqVO reqVO = randomPojo(StorageAreaUpdateReqVO.class, o -> {
            o.setId(dbStorageArea.getId()); // 设置更新的 ID
        });

        // 调用
        storageAreaService.updateStorageArea(reqVO);
        // 校验是否更新正确
        StorageAreaDO storageArea = storageAreaMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, storageArea);
    }

    @Test
    public void testUpdateStorageArea_notExists() {
        // 准备参数
        StorageAreaUpdateReqVO reqVO = randomPojo(StorageAreaUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> storageAreaService.updateStorageArea(reqVO), STORAGE_AREA_NOT_EXISTS);
    }

    @Test
    public void testDeleteStorageArea_success() {
        // mock 数据
        StorageAreaDO dbStorageArea = randomPojo(StorageAreaDO.class);
        storageAreaMapper.insert(dbStorageArea);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbStorageArea.getId();

        // 调用
        storageAreaService.deleteStorageArea(id);
       // 校验数据不存在了
       assertNull(storageAreaMapper.selectById(id));
    }

    @Test
    public void testDeleteStorageArea_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> storageAreaService.deleteStorageArea(id), STORAGE_AREA_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetStorageAreaPage() {
       // mock 数据
       StorageAreaDO dbStorageArea = randomPojo(StorageAreaDO.class, o -> { // 等会查询到
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setLocationId(null);
           o.setArea(null);
           o.setMaxLoa(null);
           o.setPositionX(null);
           o.setPositionY(null);
           o.setPositionZ(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       storageAreaMapper.insert(dbStorageArea);
       // 测试 areaCode 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setAreaName(null)));
       // 测试 locationId 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setLocationId(null)));
       // 测试 area 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setArea(null)));
       // 测试 maxLoa 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setMaxLoa(null)));
       // 测试 positionX 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setPositionX(null)));
       // 测试 positionY 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setPositionY(null)));
       // 测试 positionZ 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setPositionZ(null)));
       // 测试 enableFlag 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setCreateTime(null)));
       // 准备参数
       StorageAreaPageReqVO reqVO = new StorageAreaPageReqVO();
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setLocationId(null);
       reqVO.setArea(null);
       reqVO.setMaxLoa(null);
       reqVO.setPositionX(null);
       reqVO.setPositionY(null);
       reqVO.setPositionZ(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<StorageAreaDO> pageResult = storageAreaService.getStorageAreaPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbStorageArea, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetStorageAreaList() {
       // mock 数据
       StorageAreaDO dbStorageArea = randomPojo(StorageAreaDO.class, o -> { // 等会查询到
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setLocationId(null);
           o.setArea(null);
           o.setMaxLoa(null);
           o.setPositionX(null);
           o.setPositionY(null);
           o.setPositionZ(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       storageAreaMapper.insert(dbStorageArea);
       // 测试 areaCode 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setAreaName(null)));
       // 测试 locationId 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setLocationId(null)));
       // 测试 area 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setArea(null)));
       // 测试 maxLoa 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setMaxLoa(null)));
       // 测试 positionX 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setPositionX(null)));
       // 测试 positionY 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setPositionY(null)));
       // 测试 positionZ 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setPositionZ(null)));
       // 测试 enableFlag 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       storageAreaMapper.insert(cloneIgnoreId(dbStorageArea, o -> o.setCreateTime(null)));
       // 准备参数
       StorageAreaExportReqVO reqVO = new StorageAreaExportReqVO();
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setLocationId(null);
       reqVO.setArea(null);
       reqVO.setMaxLoa(null);
       reqVO.setPositionX(null);
       reqVO.setPositionY(null);
       reqVO.setPositionZ(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<StorageAreaDO> list = storageAreaService.getStorageAreaList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbStorageArea, list.get(0));
    }

}
