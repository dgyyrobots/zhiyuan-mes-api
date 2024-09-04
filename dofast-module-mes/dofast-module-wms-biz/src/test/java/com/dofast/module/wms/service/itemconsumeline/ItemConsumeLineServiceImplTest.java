package com.dofast.module.wms.service.itemconsumeline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.itemconsumeline.vo.*;
import com.dofast.module.wms.dal.dataobject.itemconsumeline.ItemConsumeLineDO;
import com.dofast.module.wms.dal.mysql.itemconsumeline.ItemConsumeLineMapper;
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
 * {@link ItemConsumeLineServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(ItemConsumeLineServiceImpl.class)
public class ItemConsumeLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ItemConsumeLineServiceImpl itemConsumeLineService;

    @Resource
    private ItemConsumeLineMapper itemConsumeLineMapper;

    @Test
    public void testCreateItemConsumeLine_success() {
        // 准备参数
        ItemConsumeLineCreateReqVO reqVO = randomPojo(ItemConsumeLineCreateReqVO.class);

        // 调用
        Long itemConsumeLineId = itemConsumeLineService.createItemConsumeLine(reqVO);
        // 断言
        assertNotNull(itemConsumeLineId);
        // 校验记录的属性是否正确
        ItemConsumeLineDO itemConsumeLine = itemConsumeLineMapper.selectById(itemConsumeLineId);
        assertPojoEquals(reqVO, itemConsumeLine);
    }

    @Test
    public void testUpdateItemConsumeLine_success() {
        // mock 数据
        ItemConsumeLineDO dbItemConsumeLine = randomPojo(ItemConsumeLineDO.class);
        itemConsumeLineMapper.insert(dbItemConsumeLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ItemConsumeLineUpdateReqVO reqVO = randomPojo(ItemConsumeLineUpdateReqVO.class, o -> {
            o.setId(dbItemConsumeLine.getId()); // 设置更新的 ID
        });

        // 调用
        itemConsumeLineService.updateItemConsumeLine(reqVO);
        // 校验是否更新正确
        ItemConsumeLineDO itemConsumeLine = itemConsumeLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, itemConsumeLine);
    }

    @Test
    public void testUpdateItemConsumeLine_notExists() {
        // 准备参数
        ItemConsumeLineUpdateReqVO reqVO = randomPojo(ItemConsumeLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> itemConsumeLineService.updateItemConsumeLine(reqVO), ITEM_CONSUME_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteItemConsumeLine_success() {
        // mock 数据
        ItemConsumeLineDO dbItemConsumeLine = randomPojo(ItemConsumeLineDO.class);
        itemConsumeLineMapper.insert(dbItemConsumeLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbItemConsumeLine.getId();

        // 调用
        itemConsumeLineService.deleteItemConsumeLine(id);
       // 校验数据不存在了
       assertNull(itemConsumeLineMapper.selectById(id));
    }

    @Test
    public void testDeleteItemConsumeLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> itemConsumeLineService.deleteItemConsumeLine(id), ITEM_CONSUME_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetItemConsumeLinePage() {
       // mock 数据
       ItemConsumeLineDO dbItemConsumeLine = randomPojo(ItemConsumeLineDO.class, o -> { // 等会查询到
           o.setRecordId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityConsume(null);
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
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       itemConsumeLineMapper.insert(dbItemConsumeLine);
       // 测试 recordId 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setRecordId(null)));
       // 测试 materialStockId 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityConsume 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setQuantityConsume(null)));
       // 测试 batchCode 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setCreateTime(null)));
       // 准备参数
       ItemConsumeLinePageReqVO reqVO = new ItemConsumeLinePageReqVO();
       reqVO.setRecordId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityConsume(null);
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
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ItemConsumeLineDO> pageResult = itemConsumeLineService.getItemConsumeLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbItemConsumeLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetItemConsumeLineList() {
       // mock 数据
       ItemConsumeLineDO dbItemConsumeLine = randomPojo(ItemConsumeLineDO.class, o -> { // 等会查询到
           o.setRecordId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityConsume(null);
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
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       itemConsumeLineMapper.insert(dbItemConsumeLine);
       // 测试 recordId 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setRecordId(null)));
       // 测试 materialStockId 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityConsume 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setQuantityConsume(null)));
       // 测试 batchCode 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       itemConsumeLineMapper.insert(cloneIgnoreId(dbItemConsumeLine, o -> o.setCreateTime(null)));
       // 准备参数
       ItemConsumeLineExportReqVO reqVO = new ItemConsumeLineExportReqVO();
       reqVO.setRecordId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityConsume(null);
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
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ItemConsumeLineDO> list = itemConsumeLineService.getItemConsumeLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbItemConsumeLine, list.get(0));
    }

}
