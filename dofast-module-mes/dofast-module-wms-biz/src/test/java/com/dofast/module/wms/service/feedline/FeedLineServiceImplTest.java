package com.dofast.module.wms.service.feedline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.feedline.vo.*;
import com.dofast.module.wms.dal.dataobject.feedline.FeedLineDO;
import com.dofast.module.wms.dal.mysql.feedline.FeedLineMapper;
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
 * {@link FeedLineServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(FeedLineServiceImpl.class)
public class FeedLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FeedLineServiceImpl feedLineService;

    @Resource
    private FeedLineMapper feedLineMapper;

    @Test
    public void testCreateFeedLine_success() {
        // 准备参数
        FeedLineCreateReqVO reqVO = randomPojo(FeedLineCreateReqVO.class);

        // 调用
        Long feedLineId = feedLineService.createFeedLine(reqVO);
        // 断言
        assertNotNull(feedLineId);
        // 校验记录的属性是否正确
        FeedLineDO feedLine = feedLineMapper.selectById(feedLineId);
        assertPojoEquals(reqVO, feedLine);
    }

    @Test
    public void testUpdateFeedLine_success() {
        // mock 数据
        FeedLineDO dbFeedLine = randomPojo(FeedLineDO.class);
        feedLineMapper.insert(dbFeedLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FeedLineUpdateReqVO reqVO = randomPojo(FeedLineUpdateReqVO.class, o -> {
            o.setId(dbFeedLine.getId()); // 设置更新的 ID
        });

        // 调用
        feedLineService.updateFeedLine(reqVO);
        // 校验是否更新正确
        FeedLineDO feedLine = feedLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, feedLine);
    }

    @Test
    public void testUpdateFeedLine_notExists() {
        // 准备参数
        FeedLineUpdateReqVO reqVO = randomPojo(FeedLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> feedLineService.updateFeedLine(reqVO), FEED_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteFeedLine_success() {
        // mock 数据
        FeedLineDO dbFeedLine = randomPojo(FeedLineDO.class);
        feedLineMapper.insert(dbFeedLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbFeedLine.getId();

        // 调用
        feedLineService.deleteFeedLine(id);
       // 校验数据不存在了
       assertNull(feedLineMapper.selectById(id));
    }

    @Test
    public void testDeleteFeedLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> feedLineService.deleteFeedLine(id), FEED_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFeedLinePage() {
       // mock 数据
       FeedLineDO dbFeedLine = randomPojo(FeedLineDO.class, o -> { // 等会查询到
           o.setIssueId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantity(null);
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
           o.setWorkorderCode(null);
           o.setTaskCode(null);
           o.setTaskName(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
       });
       feedLineMapper.insert(dbFeedLine);
       // 测试 issueId 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setIssueId(null)));
       // 测试 materialStockId 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantity 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setQuantity(null)));
       // 测试 batchCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setCreateTime(null)));
       // 测试 workorderCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setWorkorderCode(null)));
       // 测试 taskCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setTaskCode(null)));
       // 测试 taskName 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setTaskName(null)));
       // 测试 workstationCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setWorkstationName(null)));
       // 准备参数
       FeedLinePageReqVO reqVO = new FeedLinePageReqVO();
       reqVO.setIssueId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantity(null);
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
       reqVO.setWorkorderCode(null);
       reqVO.setTaskCode(null);
       reqVO.setTaskName(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);

       // 调用
       PageResult<FeedLineDO> pageResult = feedLineService.getFeedLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbFeedLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFeedLineList() {
       // mock 数据
       FeedLineDO dbFeedLine = randomPojo(FeedLineDO.class, o -> { // 等会查询到
           o.setIssueId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantity(null);
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
           o.setWorkorderCode(null);
           o.setTaskCode(null);
           o.setTaskName(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
       });
       feedLineMapper.insert(dbFeedLine);
       // 测试 issueId 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setIssueId(null)));
       // 测试 materialStockId 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantity 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setQuantity(null)));
       // 测试 batchCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setCreateTime(null)));
       // 测试 workorderCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setWorkorderCode(null)));
       // 测试 taskCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setTaskCode(null)));
       // 测试 taskName 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setTaskName(null)));
       // 测试 workstationCode 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       feedLineMapper.insert(cloneIgnoreId(dbFeedLine, o -> o.setWorkstationName(null)));
       // 准备参数
       FeedLineExportReqVO reqVO = new FeedLineExportReqVO();
       reqVO.setIssueId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantity(null);
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
       reqVO.setWorkorderCode(null);
       reqVO.setTaskCode(null);
       reqVO.setTaskName(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);

       // 调用
       List<FeedLineDO> list = feedLineService.getFeedLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbFeedLine, list.get(0));
    }

}
