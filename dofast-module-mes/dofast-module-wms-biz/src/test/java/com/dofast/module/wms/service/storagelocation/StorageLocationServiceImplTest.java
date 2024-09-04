package com.dofast.module.wms.service.storagelocation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.storagelocation.vo.*;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.mysql.storagelocation.StorageLocationMapper;
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
 * {@link StorageLocationServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(StorageLocationServiceImpl.class)
public class StorageLocationServiceImplTest extends BaseDbUnitTest {

    @Resource
    private StorageLocationServiceImpl storageLocationService;

    @Resource
    private StorageLocationMapper storageLocationMapper;

    @Test
    public void testCreateStorageLocation_success() {
        // 准备参数
        StorageLocationCreateReqVO reqVO = randomPojo(StorageLocationCreateReqVO.class);

        // 调用
        Long storageLocationId = storageLocationService.createStorageLocation(reqVO);
        // 断言
        assertNotNull(storageLocationId);
        // 校验记录的属性是否正确
        StorageLocationDO storageLocation = storageLocationMapper.selectById(storageLocationId);
        assertPojoEquals(reqVO, storageLocation);
    }

    @Test
    public void testUpdateStorageLocation_success() {
        // mock 数据
        StorageLocationDO dbStorageLocation = randomPojo(StorageLocationDO.class);
        storageLocationMapper.insert(dbStorageLocation);// @Sql: 先插入出一条存在的数据
        // 准备参数
        StorageLocationUpdateReqVO reqVO = randomPojo(StorageLocationUpdateReqVO.class, o -> {
            o.setId(dbStorageLocation.getId()); // 设置更新的 ID
        });

        // 调用
        storageLocationService.updateStorageLocation(reqVO);
        // 校验是否更新正确
        StorageLocationDO storageLocation = storageLocationMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, storageLocation);
    }

    @Test
    public void testUpdateStorageLocation_notExists() {
        // 准备参数
        StorageLocationUpdateReqVO reqVO = randomPojo(StorageLocationUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> storageLocationService.updateStorageLocation(reqVO), STORAGE_LOCATION_NOT_EXISTS);
    }

    @Test
    public void testDeleteStorageLocation_success() {
        // mock 数据
        StorageLocationDO dbStorageLocation = randomPojo(StorageLocationDO.class);
        storageLocationMapper.insert(dbStorageLocation);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbStorageLocation.getId();

        // 调用
        storageLocationService.deleteStorageLocation(id);
       // 校验数据不存在了
       assertNull(storageLocationMapper.selectById(id));
    }

    @Test
    public void testDeleteStorageLocation_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> storageLocationService.deleteStorageLocation(id), STORAGE_LOCATION_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetStorageLocationPage() {
       // mock 数据
       StorageLocationDO dbStorageLocation = randomPojo(StorageLocationDO.class, o -> { // 等会查询到
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setWarehouseId(null);
           o.setArea(null);
           o.setAreaFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       storageLocationMapper.insert(dbStorageLocation);
       // 测试 locationCode 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setLocationName(null)));
       // 测试 warehouseId 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setWarehouseId(null)));
       // 测试 area 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setArea(null)));
       // 测试 areaFlag 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setAreaFlag(null)));
       // 测试 remark 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setCreateTime(null)));
       // 准备参数
       StorageLocationPageReqVO reqVO = new StorageLocationPageReqVO();
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setWarehouseId(null);
       reqVO.setArea(null);
       reqVO.setAreaFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<StorageLocationDO> pageResult = storageLocationService.getStorageLocationPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbStorageLocation, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetStorageLocationList() {
       // mock 数据
       StorageLocationDO dbStorageLocation = randomPojo(StorageLocationDO.class, o -> { // 等会查询到
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setWarehouseId(null);
           o.setArea(null);
           o.setAreaFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       storageLocationMapper.insert(dbStorageLocation);
       // 测试 locationCode 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setLocationName(null)));
       // 测试 warehouseId 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setWarehouseId(null)));
       // 测试 area 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setArea(null)));
       // 测试 areaFlag 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setAreaFlag(null)));
       // 测试 remark 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       storageLocationMapper.insert(cloneIgnoreId(dbStorageLocation, o -> o.setCreateTime(null)));
       // 准备参数
       StorageLocationExportReqVO reqVO = new StorageLocationExportReqVO();
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setWarehouseId(null);
       reqVO.setArea(null);
       reqVO.setAreaFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<StorageLocationDO> list = storageLocationService.getStorageLocationList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbStorageLocation, list.get(0));
    }

}
