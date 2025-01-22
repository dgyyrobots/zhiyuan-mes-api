package com.dofast.module.wms.service.allocatedrecord;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.allocatedrecord.vo.*;
import com.dofast.module.wms.dal.dataobject.allocatedrecord.AllocatedRecordDO;
import com.dofast.module.wms.dal.mysql.allocatedrecord.AllocatedRecordMapper;
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
 * {@link AllocatedRecordServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(AllocatedRecordServiceImpl.class)
public class AllocatedRecordServiceImplTest extends BaseDbUnitTest {

    @Resource
    private AllocatedRecordServiceImpl allocatedRecordService;

    @Resource
    private AllocatedRecordMapper allocatedRecordMapper;

    @Test
    public void testCreateAllocatedRecord_success() {
        // 准备参数
        AllocatedRecordCreateReqVO reqVO = randomPojo(AllocatedRecordCreateReqVO.class);

        // 调用
        Long allocatedRecordId = allocatedRecordService.createAllocatedRecord(reqVO);
        // 断言
        assertNotNull(allocatedRecordId);
        // 校验记录的属性是否正确
        AllocatedRecordDO allocatedRecord = allocatedRecordMapper.selectById(allocatedRecordId);
        assertPojoEquals(reqVO, allocatedRecord);
    }

    @Test
    public void testUpdateAllocatedRecord_success() {
        // mock 数据
        AllocatedRecordDO dbAllocatedRecord = randomPojo(AllocatedRecordDO.class);
        allocatedRecordMapper.insert(dbAllocatedRecord);// @Sql: 先插入出一条存在的数据
        // 准备参数
        AllocatedRecordUpdateReqVO reqVO = randomPojo(AllocatedRecordUpdateReqVO.class, o -> {
            o.setId(dbAllocatedRecord.getId()); // 设置更新的 ID
        });

        // 调用
        allocatedRecordService.updateAllocatedRecord(reqVO);
        // 校验是否更新正确
        AllocatedRecordDO allocatedRecord = allocatedRecordMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, allocatedRecord);
    }

    @Test
    public void testUpdateAllocatedRecord_notExists() {
        // 准备参数
        AllocatedRecordUpdateReqVO reqVO = randomPojo(AllocatedRecordUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> allocatedRecordService.updateAllocatedRecord(reqVO), ALLOCATED_RECORD_NOT_EXISTS);
    }

    @Test
    public void testDeleteAllocatedRecord_success() {
        // mock 数据
        AllocatedRecordDO dbAllocatedRecord = randomPojo(AllocatedRecordDO.class);
        allocatedRecordMapper.insert(dbAllocatedRecord);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbAllocatedRecord.getId();

        // 调用
        allocatedRecordService.deleteAllocatedRecord(id);
       // 校验数据不存在了
       assertNull(allocatedRecordMapper.selectById(id));
    }

    @Test
    public void testDeleteAllocatedRecord_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> allocatedRecordService.deleteAllocatedRecord(id), ALLOCATED_RECORD_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetAllocatedRecordPage() {
       // mock 数据
       AllocatedRecordDO dbAllocatedRecord = randomPojo(AllocatedRecordDO.class, o -> { // 等会查询到
           o.setAllocatedId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityAllocated(null);
           o.setBatchCode(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       allocatedRecordMapper.insert(dbAllocatedRecord);
       // 测试 allocatedId 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setAllocatedId(null)));
       // 测试 materialStockId 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityAllocated 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setQuantityAllocated(null)));
       // 测试 batchCode 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setCreateTime(null)));
       // 准备参数
       AllocatedRecordPageReqVO reqVO = new AllocatedRecordPageReqVO();
       reqVO.setAllocatedId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityAllocated(null);
       reqVO.setBatchCode(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<AllocatedRecordDO> pageResult = allocatedRecordService.getAllocatedRecordPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbAllocatedRecord, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetAllocatedRecordList() {
       // mock 数据
       AllocatedRecordDO dbAllocatedRecord = randomPojo(AllocatedRecordDO.class, o -> { // 等会查询到
           o.setAllocatedId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityAllocated(null);
           o.setBatchCode(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       allocatedRecordMapper.insert(dbAllocatedRecord);
       // 测试 allocatedId 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setAllocatedId(null)));
       // 测试 materialStockId 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityAllocated 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setQuantityAllocated(null)));
       // 测试 batchCode 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       allocatedRecordMapper.insert(cloneIgnoreId(dbAllocatedRecord, o -> o.setCreateTime(null)));
       // 准备参数
       AllocatedRecordExportReqVO reqVO = new AllocatedRecordExportReqVO();
       reqVO.setAllocatedId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityAllocated(null);
       reqVO.setBatchCode(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<AllocatedRecordDO> list = allocatedRecordService.getAllocatedRecordList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbAllocatedRecord, list.get(0));
    }

}
