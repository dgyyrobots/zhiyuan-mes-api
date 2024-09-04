package com.dofast.module.wms.service.itemrecpt;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.itemrecpt.vo.*;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptDO;
import com.dofast.module.wms.dal.mysql.itemrecpt.ItemRecptMapper;
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
 * {@link ItemRecptServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(ItemRecptServiceImpl.class)
public class ItemRecptServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ItemRecptServiceImpl itemRecptService;

    @Resource
    private ItemRecptMapper itemRecptMapper;

    @Test
    public void testCreateItemRecpt_success() {
        // 准备参数
        ItemRecptCreateReqVO reqVO = randomPojo(ItemRecptCreateReqVO.class);

        // 调用
        Long itemRecptId = itemRecptService.createItemRecpt(reqVO);
        // 断言
        assertNotNull(itemRecptId);
        // 校验记录的属性是否正确
        ItemRecptDO itemRecpt = itemRecptMapper.selectById(itemRecptId);
        assertPojoEquals(reqVO, itemRecpt);
    }

    @Test
    public void testUpdateItemRecpt_success() {
        // mock 数据
        ItemRecptDO dbItemRecpt = randomPojo(ItemRecptDO.class);
        itemRecptMapper.insert(dbItemRecpt);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ItemRecptUpdateReqVO reqVO = randomPojo(ItemRecptUpdateReqVO.class, o -> {
            o.setId(dbItemRecpt.getId()); // 设置更新的 ID
        });

        // 调用
        itemRecptService.updateItemRecpt(reqVO);
        // 校验是否更新正确
        ItemRecptDO itemRecpt = itemRecptMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, itemRecpt);
    }

    @Test
    public void testUpdateItemRecpt_notExists() {
        // 准备参数
        ItemRecptUpdateReqVO reqVO = randomPojo(ItemRecptUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> itemRecptService.updateItemRecpt(reqVO), ITEM_RECPT_NOT_EXISTS);
    }

    @Test
    public void testDeleteItemRecpt_success() {
        // mock 数据
        ItemRecptDO dbItemRecpt = randomPojo(ItemRecptDO.class);
        itemRecptMapper.insert(dbItemRecpt);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbItemRecpt.getId();

        // 调用
        itemRecptService.deleteItemRecpt(id);
       // 校验数据不存在了
       assertNull(itemRecptMapper.selectById(id));
    }

    @Test
    public void testDeleteItemRecpt_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> itemRecptService.deleteItemRecpt(id), ITEM_RECPT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetItemRecptPage() {
       // mock 数据
       ItemRecptDO dbItemRecpt = randomPojo(ItemRecptDO.class, o -> { // 等会查询到
           o.setRecptCode(null);
           o.setRecptName(null);
           o.setIqcId(null);
           o.setIqcCode(null);
           o.setPoCode(null);
           o.setVendorId(null);
           o.setVendorCode(null);
           o.setVendorName(null);
           o.setVendorNick(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setRecptDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       itemRecptMapper.insert(dbItemRecpt);
       // 测试 recptCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setRecptCode(null)));
       // 测试 recptName 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setRecptName(null)));
       // 测试 iqcId 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setIqcId(null)));
       // 测试 iqcCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setIqcCode(null)));
       // 测试 poCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setPoCode(null)));
       // 测试 vendorId 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setVendorId(null)));
       // 测试 vendorCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setVendorCode(null)));
       // 测试 vendorName 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setVendorName(null)));
       // 测试 vendorNick 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setVendorNick(null)));
       // 测试 warehouseId 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAreaName(null)));
       // 测试 recptDate 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setRecptDate(null)));
       // 测试 status 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setCreateTime(null)));
       // 准备参数
       ItemRecptPageReqVO reqVO = new ItemRecptPageReqVO();
       reqVO.setRecptCode(null);
       reqVO.setRecptName(null);
       reqVO.setIqcId(null);
       reqVO.setIqcCode(null);
       reqVO.setPoCode(null);
       reqVO.setVendorId(null);
       reqVO.setVendorCode(null);
       reqVO.setVendorName(null);
       reqVO.setVendorNick(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setRecptDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ItemRecptDO> pageResult = itemRecptService.getItemRecptPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbItemRecpt, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetItemRecptList() {
       // mock 数据
       ItemRecptDO dbItemRecpt = randomPojo(ItemRecptDO.class, o -> { // 等会查询到
           o.setRecptCode(null);
           o.setRecptName(null);
           o.setIqcId(null);
           o.setIqcCode(null);
           o.setPoCode(null);
           o.setVendorId(null);
           o.setVendorCode(null);
           o.setVendorName(null);
           o.setVendorNick(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setRecptDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       itemRecptMapper.insert(dbItemRecpt);
       // 测试 recptCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setRecptCode(null)));
       // 测试 recptName 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setRecptName(null)));
       // 测试 iqcId 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setIqcId(null)));
       // 测试 iqcCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setIqcCode(null)));
       // 测试 poCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setPoCode(null)));
       // 测试 vendorId 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setVendorId(null)));
       // 测试 vendorCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setVendorCode(null)));
       // 测试 vendorName 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setVendorName(null)));
       // 测试 vendorNick 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setVendorNick(null)));
       // 测试 warehouseId 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAreaName(null)));
       // 测试 recptDate 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setRecptDate(null)));
       // 测试 status 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       itemRecptMapper.insert(cloneIgnoreId(dbItemRecpt, o -> o.setCreateTime(null)));
       // 准备参数
       ItemRecptExportReqVO reqVO = new ItemRecptExportReqVO();
       reqVO.setRecptCode(null);
       reqVO.setRecptName(null);
       reqVO.setIqcId(null);
       reqVO.setIqcCode(null);
       reqVO.setPoCode(null);
       reqVO.setVendorId(null);
       reqVO.setVendorCode(null);
       reqVO.setVendorName(null);
       reqVO.setVendorNick(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setRecptDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ItemRecptDO> list = itemRecptService.getItemRecptList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbItemRecpt, list.get(0));
    }

}
