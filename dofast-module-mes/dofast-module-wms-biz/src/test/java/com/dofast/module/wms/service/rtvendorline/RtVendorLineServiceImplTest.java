package com.dofast.module.wms.service.rtvendorline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.rtvendorline.vo.*;
import com.dofast.module.wms.dal.dataobject.rtvendorline.RtVendorLineDO;
import com.dofast.module.wms.dal.mysql.rtvendorline.RtVendorLineMapper;
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
 * {@link RtVendorLineServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(RtVendorLineServiceImpl.class)
public class RtVendorLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RtVendorLineServiceImpl rtVendorLineService;

    @Resource
    private RtVendorLineMapper rtVendorLineMapper;

    @Test
    public void testCreateRtVendorLine_success() {
        // 准备参数
        RtVendorLineCreateReqVO reqVO = randomPojo(RtVendorLineCreateReqVO.class);

        // 调用
        Long rtVendorLineId = rtVendorLineService.createRtVendorLine(reqVO);
        // 断言
        assertNotNull(rtVendorLineId);
        // 校验记录的属性是否正确
        RtVendorLineDO rtVendorLine = rtVendorLineMapper.selectById(rtVendorLineId);
        assertPojoEquals(reqVO, rtVendorLine);
    }

    @Test
    public void testUpdateRtVendorLine_success() {
        // mock 数据
        RtVendorLineDO dbRtVendorLine = randomPojo(RtVendorLineDO.class);
        rtVendorLineMapper.insert(dbRtVendorLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RtVendorLineUpdateReqVO reqVO = randomPojo(RtVendorLineUpdateReqVO.class, o -> {
            o.setId(dbRtVendorLine.getId()); // 设置更新的 ID
        });

        // 调用
        rtVendorLineService.updateRtVendorLine(reqVO);
        // 校验是否更新正确
        RtVendorLineDO rtVendorLine = rtVendorLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, rtVendorLine);
    }

    @Test
    public void testUpdateRtVendorLine_notExists() {
        // 准备参数
        RtVendorLineUpdateReqVO reqVO = randomPojo(RtVendorLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> rtVendorLineService.updateRtVendorLine(reqVO), RT_VENDOR_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteRtVendorLine_success() {
        // mock 数据
        RtVendorLineDO dbRtVendorLine = randomPojo(RtVendorLineDO.class);
        rtVendorLineMapper.insert(dbRtVendorLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRtVendorLine.getId();

        // 调用
        rtVendorLineService.deleteRtVendorLine(id);
       // 校验数据不存在了
       assertNull(rtVendorLineMapper.selectById(id));
    }

    @Test
    public void testDeleteRtVendorLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> rtVendorLineService.deleteRtVendorLine(id), RT_VENDOR_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRtVendorLinePage() {
       // mock 数据
       RtVendorLineDO dbRtVendorLine = randomPojo(RtVendorLineDO.class, o -> { // 等会查询到
           o.setRtId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityRted(null);
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
       rtVendorLineMapper.insert(dbRtVendorLine);
       // 测试 rtId 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setRtId(null)));
       // 测试 materialStockId 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityRted 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setQuantityRted(null)));
       // 测试 batchCode 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setCreateTime(null)));
       // 准备参数
       RtVendorLinePageReqVO reqVO = new RtVendorLinePageReqVO();
       reqVO.setRtId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityRted(null);
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
       PageResult<RtVendorLineDO> pageResult = rtVendorLineService.getRtVendorLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRtVendorLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRtVendorLineList() {
       // mock 数据
       RtVendorLineDO dbRtVendorLine = randomPojo(RtVendorLineDO.class, o -> { // 等会查询到
           o.setRtId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityRted(null);
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
       rtVendorLineMapper.insert(dbRtVendorLine);
       // 测试 rtId 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setRtId(null)));
       // 测试 materialStockId 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityRted 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setQuantityRted(null)));
       // 测试 batchCode 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       rtVendorLineMapper.insert(cloneIgnoreId(dbRtVendorLine, o -> o.setCreateTime(null)));
       // 准备参数
       RtVendorLineExportReqVO reqVO = new RtVendorLineExportReqVO();
       reqVO.setRtId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityRted(null);
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
       List<RtVendorLineDO> list = rtVendorLineService.getRtVendorLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRtVendorLine, list.get(0));
    }

}
