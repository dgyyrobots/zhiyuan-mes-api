package com.dofast.module.wms.service.electroformline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.electroformline.vo.*;
import com.dofast.module.wms.dal.dataobject.electroformline.ElectroformLineDO;
import com.dofast.module.wms.dal.mysql.electroformline.ElectroformLineMapper;
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
 * {@link ElectroformLineServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(ElectroformLineServiceImpl.class)
public class ElectroformLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ElectroformLineServiceImpl electroformLineService;

    @Resource
    private ElectroformLineMapper electroformLineMapper;

    @Test
    public void testCreateElectroformLine_success() {
        // 准备参数
        ElectroformLineCreateReqVO reqVO = randomPojo(ElectroformLineCreateReqVO.class);

        // 调用
        Long electroformLineId = electroformLineService.createElectroformLine(reqVO);
        // 断言
        assertNotNull(electroformLineId);
        // 校验记录的属性是否正确
        ElectroformLineDO electroformLine = electroformLineMapper.selectById(electroformLineId);
        assertPojoEquals(reqVO, electroformLine);
    }

    @Test
    public void testUpdateElectroformLine_success() {
        // mock 数据
        ElectroformLineDO dbElectroformLine = randomPojo(ElectroformLineDO.class);
        electroformLineMapper.insert(dbElectroformLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ElectroformLineUpdateReqVO reqVO = randomPojo(ElectroformLineUpdateReqVO.class, o -> {
            o.setId(dbElectroformLine.getId()); // 设置更新的 ID
        });

        // 调用
        electroformLineService.updateElectroformLine(reqVO);
        // 校验是否更新正确
        ElectroformLineDO electroformLine = electroformLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, electroformLine);
    }

    @Test
    public void testUpdateElectroformLine_notExists() {
        // 准备参数
        ElectroformLineUpdateReqVO reqVO = randomPojo(ElectroformLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> electroformLineService.updateElectroformLine(reqVO), ELECTROFORM_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteElectroformLine_success() {
        // mock 数据
        ElectroformLineDO dbElectroformLine = randomPojo(ElectroformLineDO.class);
        electroformLineMapper.insert(dbElectroformLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbElectroformLine.getId();

        // 调用
        electroformLineService.deleteElectroformLine(id);
       // 校验数据不存在了
       assertNull(electroformLineMapper.selectById(id));
    }

    @Test
    public void testDeleteElectroformLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> electroformLineService.deleteElectroformLine(id), ELECTROFORM_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetElectroformLinePage() {
       // mock 数据
       ElectroformLineDO dbElectroformLine = randomPojo(ElectroformLineDO.class, o -> { // 等会查询到
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
           o.setCreateTime(null);
       });
       electroformLineMapper.insert(dbElectroformLine);
       // 测试 issueId 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setIssueId(null)));
       // 测试 materialStockId 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityIssued 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setQuantityIssued(null)));
       // 测试 batchCode 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setCreateTime(null)));
       // 准备参数
       ElectroformLinePageReqVO reqVO = new ElectroformLinePageReqVO();
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
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ElectroformLineDO> pageResult = electroformLineService.getElectroformLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbElectroformLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetElectroformLineList() {
       // mock 数据
       ElectroformLineDO dbElectroformLine = randomPojo(ElectroformLineDO.class, o -> { // 等会查询到
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
           o.setCreateTime(null);
       });
       electroformLineMapper.insert(dbElectroformLine);
       // 测试 issueId 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setIssueId(null)));
       // 测试 materialStockId 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityIssued 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setQuantityIssued(null)));
       // 测试 batchCode 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       electroformLineMapper.insert(cloneIgnoreId(dbElectroformLine, o -> o.setCreateTime(null)));
       // 准备参数
       ElectroformLineExportReqVO reqVO = new ElectroformLineExportReqVO();
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
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ElectroformLineDO> list = electroformLineService.getElectroformLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbElectroformLine, list.get(0));
    }

}
