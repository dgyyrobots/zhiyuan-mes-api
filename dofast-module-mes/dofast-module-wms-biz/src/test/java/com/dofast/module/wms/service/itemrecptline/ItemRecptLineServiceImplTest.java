package com.dofast.module.wms.service.itemrecptline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.itemrecptline.vo.*;
import com.dofast.module.wms.dal.dataobject.itemrecptline.ItemRecptLineDO;
import com.dofast.module.wms.dal.mysql.itemrecptline.ItemRecptLineMapper;
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
 * {@link ItemRecptLineServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(ItemRecptLineServiceImpl.class)
public class ItemRecptLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ItemRecptLineServiceImpl itemRecptLineService;

    @Resource
    private ItemRecptLineMapper itemRecptLineMapper;

    @Test
    public void testCreateItemRecptLine_success() {
        // 准备参数
        ItemRecptLineCreateReqVO reqVO = randomPojo(ItemRecptLineCreateReqVO.class);

        // 调用
        Long itemRecptLineId = itemRecptLineService.createItemRecptLine(reqVO);
        // 断言
        assertNotNull(itemRecptLineId);
        // 校验记录的属性是否正确
        ItemRecptLineDO itemRecptLine = itemRecptLineMapper.selectById(itemRecptLineId);
        assertPojoEquals(reqVO, itemRecptLine);
    }

    @Test
    public void testUpdateItemRecptLine_success() {
        // mock 数据
        ItemRecptLineDO dbItemRecptLine = randomPojo(ItemRecptLineDO.class);
        itemRecptLineMapper.insert(dbItemRecptLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ItemRecptLineUpdateReqVO reqVO = randomPojo(ItemRecptLineUpdateReqVO.class, o -> {
            o.setId(dbItemRecptLine.getId()); // 设置更新的 ID
        });

        // 调用
        itemRecptLineService.updateItemRecptLine(reqVO);
        // 校验是否更新正确
        ItemRecptLineDO itemRecptLine = itemRecptLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, itemRecptLine);
    }

    @Test
    public void testUpdateItemRecptLine_notExists() {
        // 准备参数
        ItemRecptLineUpdateReqVO reqVO = randomPojo(ItemRecptLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> itemRecptLineService.updateItemRecptLine(reqVO), ITEM_RECPT_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteItemRecptLine_success() {
        // mock 数据
        ItemRecptLineDO dbItemRecptLine = randomPojo(ItemRecptLineDO.class);
        itemRecptLineMapper.insert(dbItemRecptLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbItemRecptLine.getId();

        // 调用
        itemRecptLineService.deleteItemRecptLine(id);
       // 校验数据不存在了
       assertNull(itemRecptLineMapper.selectById(id));
    }

    @Test
    public void testDeleteItemRecptLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> itemRecptLineService.deleteItemRecptLine(id), ITEM_RECPT_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetItemRecptLinePage() {
       // mock 数据
       ItemRecptLineDO dbItemRecptLine = randomPojo(ItemRecptLineDO.class, o -> { // 等会查询到
           o.setRecptId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityRecived(null);
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
           o.setExpireDate(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       itemRecptLineMapper.insert(dbItemRecptLine);
       // 测试 recptId 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setRecptId(null)));
       // 测试 itemId 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityRecived 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setQuantityRecived(null)));
       // 测试 batchCode 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAreaName(null)));
       // 测试 expireDate 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setExpireDate(null)));
       // 测试 remark 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setCreateTime(null)));
       // 准备参数
       ItemRecptLinePageReqVO reqVO = new ItemRecptLinePageReqVO();
       reqVO.setRecptId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityRecived(null);
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
       reqVO.setExpireDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ItemRecptLineDO> pageResult = itemRecptLineService.getItemRecptLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbItemRecptLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetItemRecptLineList() {
       // mock 数据
       ItemRecptLineDO dbItemRecptLine = randomPojo(ItemRecptLineDO.class, o -> { // 等会查询到
           o.setRecptId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityRecived(null);
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
           o.setExpireDate(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       itemRecptLineMapper.insert(dbItemRecptLine);
       // 测试 recptId 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setRecptId(null)));
       // 测试 itemId 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityRecived 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setQuantityRecived(null)));
       // 测试 batchCode 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAreaName(null)));
       // 测试 expireDate 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setExpireDate(null)));
       // 测试 remark 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       itemRecptLineMapper.insert(cloneIgnoreId(dbItemRecptLine, o -> o.setCreateTime(null)));
       // 准备参数
       ItemRecptLineExportReqVO reqVO = new ItemRecptLineExportReqVO();
       reqVO.setRecptId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityRecived(null);
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
       reqVO.setExpireDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ItemRecptLineDO> list = itemRecptLineService.getItemRecptLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbItemRecptLine, list.get(0));
    }

}
