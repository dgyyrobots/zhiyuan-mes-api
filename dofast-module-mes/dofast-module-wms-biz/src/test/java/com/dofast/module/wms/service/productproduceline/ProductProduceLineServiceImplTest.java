package com.dofast.module.wms.service.productproduceline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.productproduceline.vo.*;
import com.dofast.module.wms.dal.dataobject.productproduceline.ProductProduceLineDO;
import com.dofast.module.wms.dal.mysql.productproduceline.ProductProduceLineMapper;
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
 * {@link ProductProduceLineServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(ProductProduceLineServiceImpl.class)
public class ProductProduceLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ProductProduceLineServiceImpl productProduceLineService;

    @Resource
    private ProductProduceLineMapper productProduceLineMapper;

    @Test
    public void testCreateProductProduceLine_success() {
        // 准备参数
        ProductProduceLineCreateReqVO reqVO = randomPojo(ProductProduceLineCreateReqVO.class);

        // 调用
        Long productProduceLineId = productProduceLineService.createProductProduceLine(reqVO);
        // 断言
        assertNotNull(productProduceLineId);
        // 校验记录的属性是否正确
        ProductProduceLineDO productProduceLine = productProduceLineMapper.selectById(productProduceLineId);
        assertPojoEquals(reqVO, productProduceLine);
    }

    @Test
    public void testUpdateProductProduceLine_success() {
        // mock 数据
        ProductProduceLineDO dbProductProduceLine = randomPojo(ProductProduceLineDO.class);
        productProduceLineMapper.insert(dbProductProduceLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ProductProduceLineUpdateReqVO reqVO = randomPojo(ProductProduceLineUpdateReqVO.class, o -> {
            o.setId(dbProductProduceLine.getId()); // 设置更新的 ID
        });

        // 调用
        productProduceLineService.updateProductProduceLine(reqVO);
        // 校验是否更新正确
        ProductProduceLineDO productProduceLine = productProduceLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, productProduceLine);
    }

    @Test
    public void testUpdateProductProduceLine_notExists() {
        // 准备参数
        ProductProduceLineUpdateReqVO reqVO = randomPojo(ProductProduceLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> productProduceLineService.updateProductProduceLine(reqVO), PRODUCT_PRODUCE_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteProductProduceLine_success() {
        // mock 数据
        ProductProduceLineDO dbProductProduceLine = randomPojo(ProductProduceLineDO.class);
        productProduceLineMapper.insert(dbProductProduceLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbProductProduceLine.getId();

        // 调用
        productProduceLineService.deleteProductProduceLine(id);
       // 校验数据不存在了
       assertNull(productProduceLineMapper.selectById(id));
    }

    @Test
    public void testDeleteProductProduceLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> productProduceLineService.deleteProductProduceLine(id), PRODUCT_PRODUCE_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProductProduceLinePage() {
       // mock 数据
       ProductProduceLineDO dbProductProduceLine = randomPojo(ProductProduceLineDO.class, o -> { // 等会查询到
           o.setRecordId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityProduce(null);
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
       productProduceLineMapper.insert(dbProductProduceLine);
       // 测试 recordId 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setRecordId(null)));
       // 测试 itemId 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityProduce 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setQuantityProduce(null)));
       // 测试 batchCode 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setCreateTime(null)));
       // 准备参数
       ProductProduceLinePageReqVO reqVO = new ProductProduceLinePageReqVO();
       reqVO.setRecordId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityProduce(null);
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
       PageResult<ProductProduceLineDO> pageResult = productProduceLineService.getProductProduceLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbProductProduceLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProductProduceLineList() {
       // mock 数据
       ProductProduceLineDO dbProductProduceLine = randomPojo(ProductProduceLineDO.class, o -> { // 等会查询到
           o.setRecordId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityProduce(null);
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
       productProduceLineMapper.insert(dbProductProduceLine);
       // 测试 recordId 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setRecordId(null)));
       // 测试 itemId 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityProduce 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setQuantityProduce(null)));
       // 测试 batchCode 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       productProduceLineMapper.insert(cloneIgnoreId(dbProductProduceLine, o -> o.setCreateTime(null)));
       // 准备参数
       ProductProduceLineExportReqVO reqVO = new ProductProduceLineExportReqVO();
       reqVO.setRecordId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityProduce(null);
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
       List<ProductProduceLineDO> list = productProduceLineService.getProductProduceLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbProductProduceLine, list.get(0));
    }

}
