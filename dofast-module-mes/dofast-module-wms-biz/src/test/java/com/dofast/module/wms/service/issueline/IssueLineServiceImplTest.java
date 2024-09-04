package com.dofast.module.wms.service.issueline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.issueline.vo.*;
import com.dofast.module.wms.dal.dataobject.issueline.IssueLineDO;
import com.dofast.module.wms.dal.mysql.issueline.IssueLineMapper;
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
 * {@link IssueLineServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(IssueLineServiceImpl.class)
public class IssueLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private IssueLineServiceImpl issueLineService;

    @Resource
    private IssueLineMapper issueLineMapper;

    @Test
    public void testCreateIssueLine_success() {
        // 准备参数
        IssueLineCreateReqVO reqVO = randomPojo(IssueLineCreateReqVO.class);

        // 调用
        Long issueLineId = issueLineService.createIssueLine(reqVO);
        // 断言
        assertNotNull(issueLineId);
        // 校验记录的属性是否正确
        IssueLineDO issueLine = issueLineMapper.selectById(issueLineId);
        assertPojoEquals(reqVO, issueLine);
    }

    @Test
    public void testUpdateIssueLine_success() {
        // mock 数据
        IssueLineDO dbIssueLine = randomPojo(IssueLineDO.class);
        issueLineMapper.insert(dbIssueLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        IssueLineUpdateReqVO reqVO = randomPojo(IssueLineUpdateReqVO.class, o -> {
            o.setId(dbIssueLine.getId()); // 设置更新的 ID
        });

        // 调用
        issueLineService.updateIssueLine(reqVO);
        // 校验是否更新正确
        IssueLineDO issueLine = issueLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, issueLine);
    }

    @Test
    public void testUpdateIssueLine_notExists() {
        // 准备参数
        IssueLineUpdateReqVO reqVO = randomPojo(IssueLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> issueLineService.updateIssueLine(reqVO), ISSUE_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteIssueLine_success() {
        // mock 数据
        IssueLineDO dbIssueLine = randomPojo(IssueLineDO.class);
        issueLineMapper.insert(dbIssueLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbIssueLine.getId();

        // 调用
        issueLineService.deleteIssueLine(id);
       // 校验数据不存在了
       assertNull(issueLineMapper.selectById(id));
    }

    @Test
    public void testDeleteIssueLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> issueLineService.deleteIssueLine(id), ISSUE_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIssueLinePage() {
       // mock 数据
       IssueLineDO dbIssueLine = randomPojo(IssueLineDO.class, o -> { // 等会查询到
           o.setIssueId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityIssued(null);
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
       issueLineMapper.insert(dbIssueLine);
       // 测试 issueId 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setIssueId(null)));
       // 测试 materialStockId 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityIssued 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setQuantityIssued(null)));
       // 测试 batchCode 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setCreateTime(null)));
       // 准备参数
       IssueLinePageReqVO reqVO = new IssueLinePageReqVO();
       reqVO.setIssueId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityIssued(null);
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
       PageResult<IssueLineDO> pageResult = issueLineService.getIssueLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbIssueLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIssueLineList() {
       // mock 数据
       IssueLineDO dbIssueLine = randomPojo(IssueLineDO.class, o -> { // 等会查询到
           o.setIssueId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityIssued(null);
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
       issueLineMapper.insert(dbIssueLine);
       // 测试 issueId 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setIssueId(null)));
       // 测试 materialStockId 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityIssued 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setQuantityIssued(null)));
       // 测试 batchCode 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       issueLineMapper.insert(cloneIgnoreId(dbIssueLine, o -> o.setCreateTime(null)));
       // 准备参数
       IssueLineExportReqVO reqVO = new IssueLineExportReqVO();
       reqVO.setIssueId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityIssued(null);
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
       List<IssueLineDO> list = issueLineService.getIssueLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbIssueLine, list.get(0));
    }

}
