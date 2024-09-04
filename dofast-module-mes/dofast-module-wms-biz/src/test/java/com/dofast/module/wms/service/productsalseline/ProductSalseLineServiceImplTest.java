package com.dofast.module.wms.service.productsalseline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.productsalseline.vo.*;
import com.dofast.module.wms.dal.dataobject.productsalseline.ProductSalseLineDO;
import com.dofast.module.wms.dal.mysql.productsalseline.ProductSalseLineMapper;
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
 * {@link ProductSalseLineServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(ProductSalseLineServiceImpl.class)
public class ProductSalseLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ProductSalseLineServiceImpl productSalseLineService;

    @Resource
    private ProductSalseLineMapper productSalseLineMapper;

    @Test
    public void testCreateProductSalseLine_success() {
        // 准备参数
        ProductSalseLineCreateReqVO reqVO = randomPojo(ProductSalseLineCreateReqVO.class);

        // 调用
        Long productSalseLineId = productSalseLineService.createProductSalseLine(reqVO);
        // 断言
        assertNotNull(productSalseLineId);
        // 校验记录的属性是否正确
        ProductSalseLineDO productSalseLine = productSalseLineMapper.selectById(productSalseLineId);
        assertPojoEquals(reqVO, productSalseLine);
    }

    @Test
    public void testUpdateProductSalseLine_success() {
        // mock 数据
        ProductSalseLineDO dbProductSalseLine = randomPojo(ProductSalseLineDO.class);
        productSalseLineMapper.insert(dbProductSalseLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ProductSalseLineUpdateReqVO reqVO = randomPojo(ProductSalseLineUpdateReqVO.class, o -> {
            o.setId(dbProductSalseLine.getId()); // 设置更新的 ID
        });

        // 调用
        productSalseLineService.updateProductSalseLine(reqVO);
        // 校验是否更新正确
        ProductSalseLineDO productSalseLine = productSalseLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, productSalseLine);
    }

    @Test
    public void testUpdateProductSalseLine_notExists() {
        // 准备参数
        ProductSalseLineUpdateReqVO reqVO = randomPojo(ProductSalseLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> productSalseLineService.updateProductSalseLine(reqVO), PRODUCT_SALSE_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteProductSalseLine_success() {
        // mock 数据
        ProductSalseLineDO dbProductSalseLine = randomPojo(ProductSalseLineDO.class);
        productSalseLineMapper.insert(dbProductSalseLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbProductSalseLine.getId();

        // 调用
        productSalseLineService.deleteProductSalseLine(id);
       // 校验数据不存在了
       assertNull(productSalseLineMapper.selectById(id));
    }

    @Test
    public void testDeleteProductSalseLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> productSalseLineService.deleteProductSalseLine(id), PRODUCT_SALSE_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProductSalseLinePage() {
       // mock 数据
       ProductSalseLineDO dbProductSalseLine = randomPojo(ProductSalseLineDO.class, o -> { // 等会查询到
           o.setSalseId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantitySalse(null);
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
       productSalseLineMapper.insert(dbProductSalseLine);
       // 测试 salseId 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setSalseId(null)));
       // 测试 materialStockId 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantitySalse 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setQuantitySalse(null)));
       // 测试 batchCode 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setCreateTime(null)));
       // 准备参数
       ProductSalseLinePageReqVO reqVO = new ProductSalseLinePageReqVO();
       reqVO.setSalseId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantitySalse(null);
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
       PageResult<ProductSalseLineDO> pageResult = productSalseLineService.getProductSalseLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbProductSalseLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProductSalseLineList() {
       // mock 数据
       ProductSalseLineDO dbProductSalseLine = randomPojo(ProductSalseLineDO.class, o -> { // 等会查询到
           o.setSalseId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantitySalse(null);
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
       productSalseLineMapper.insert(dbProductSalseLine);
       // 测试 salseId 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setSalseId(null)));
       // 测试 materialStockId 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantitySalse 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setQuantitySalse(null)));
       // 测试 batchCode 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAreaName(null)));
       // 测试 remark 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       productSalseLineMapper.insert(cloneIgnoreId(dbProductSalseLine, o -> o.setCreateTime(null)));
       // 准备参数
       ProductSalseLineExportReqVO reqVO = new ProductSalseLineExportReqVO();
       reqVO.setSalseId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantitySalse(null);
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
       List<ProductSalseLineDO> list = productSalseLineService.getProductSalseLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbProductSalseLine, list.get(0));
    }

}
