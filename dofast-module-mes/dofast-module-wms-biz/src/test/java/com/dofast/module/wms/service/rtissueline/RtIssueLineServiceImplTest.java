package com.dofast.module.wms.service.rtissueline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.rtissueline.vo.*;
import com.dofast.module.wms.dal.dataobject.rtissueline.RtIssueLineDO;
import com.dofast.module.wms.dal.mysql.rtissueline.RtIssueLineMapper;
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
 * {@link RtIssueLineServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(RtIssueLineServiceImpl.class)
public class RtIssueLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RtIssueLineServiceImpl rtIssueLineService;

    @Resource
    private RtIssueLineMapper rtIssueLineMapper;

    @Test
    public void testCreateRtIssueLine_success() {
        // 准备参数
        RtIssueLineCreateReqVO reqVO = randomPojo(RtIssueLineCreateReqVO.class);

        // 调用
        Long rtIssueLineId = rtIssueLineService.createRtIssueLine(reqVO);
        // 断言
        assertNotNull(rtIssueLineId);
        // 校验记录的属性是否正确
        RtIssueLineDO rtIssueLine = rtIssueLineMapper.selectById(rtIssueLineId);
        assertPojoEquals(reqVO, rtIssueLine);
    }

    @Test
    public void testUpdateRtIssueLine_success() {
        // mock 数据
        RtIssueLineDO dbRtIssueLine = randomPojo(RtIssueLineDO.class);
        rtIssueLineMapper.insert(dbRtIssueLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RtIssueLineUpdateReqVO reqVO = randomPojo(RtIssueLineUpdateReqVO.class, o -> {
            o.setId(dbRtIssueLine.getId()); // 设置更新的 ID
        });

        // 调用
        rtIssueLineService.updateRtIssueLine(reqVO);
        // 校验是否更新正确
        RtIssueLineDO rtIssueLine = rtIssueLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, rtIssueLine);
    }

    @Test
    public void testUpdateRtIssueLine_notExists() {
        // 准备参数
        RtIssueLineUpdateReqVO reqVO = randomPojo(RtIssueLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> rtIssueLineService.updateRtIssueLine(reqVO), RT_ISSUE_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteRtIssueLine_success() {
        // mock 数据
        RtIssueLineDO dbRtIssueLine = randomPojo(RtIssueLineDO.class);
        rtIssueLineMapper.insert(dbRtIssueLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRtIssueLine.getId();

        // 调用
        rtIssueLineService.deleteRtIssueLine(id);
       // 校验数据不存在了
       assertNull(rtIssueLineMapper.selectById(id));
    }

    @Test
    public void testDeleteRtIssueLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> rtIssueLineService.deleteRtIssueLine(id), RT_ISSUE_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRtIssueLinePage() {
       // mock 数据
       RtIssueLineDO dbRtIssueLine = randomPojo(RtIssueLineDO.class, o -> { // 等会查询到
           o.setRtId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityRt(null);
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
       rtIssueLineMapper.insert(dbRtIssueLine);
       // 测试 rtId 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setRtId(null)));
       // 测试 materialStockId 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityRt 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setQuantityRt(null)));
       // 测试 batchCode 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setCreateTime(null)));
       // 准备参数
       RtIssueLinePageReqVO reqVO = new RtIssueLinePageReqVO();
       reqVO.setRtId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityRt(null);
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
       PageResult<RtIssueLineDO> pageResult = rtIssueLineService.getRtIssueLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRtIssueLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRtIssueLineList() {
       // mock 数据
       RtIssueLineDO dbRtIssueLine = randomPojo(RtIssueLineDO.class, o -> { // 等会查询到
           o.setRtId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityRt(null);
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
       rtIssueLineMapper.insert(dbRtIssueLine);
       // 测试 rtId 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setRtId(null)));
       // 测试 materialStockId 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityRt 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setQuantityRt(null)));
       // 测试 batchCode 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       rtIssueLineMapper.insert(cloneIgnoreId(dbRtIssueLine, o -> o.setCreateTime(null)));
       // 准备参数
       RtIssueLineExportReqVO reqVO = new RtIssueLineExportReqVO();
       reqVO.setRtId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityRt(null);
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
       List<RtIssueLineDO> list = rtIssueLineService.getRtIssueLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRtIssueLine, list.get(0));
    }

}
