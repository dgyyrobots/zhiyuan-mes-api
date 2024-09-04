package com.dofast.module.wms.service.productrecpt;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.productrecpt.vo.*;
import com.dofast.module.wms.dal.dataobject.productrecpt.ProductRecptDO;
import com.dofast.module.wms.dal.mysql.productrecpt.ProductRecptMapper;
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
 * {@link ProductRecptServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(ProductRecptServiceImpl.class)
public class ProductRecptServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ProductRecptServiceImpl productRecptService;

    @Resource
    private ProductRecptMapper productRecptMapper;

    @Test
    public void testCreateProductRecpt_success() {
        // 准备参数
        ProductRecptCreateReqVO reqVO = randomPojo(ProductRecptCreateReqVO.class);

        // 调用
        Long productRecptId = productRecptService.createProductRecpt(reqVO);
        // 断言
        assertNotNull(productRecptId);
        // 校验记录的属性是否正确
        ProductRecptDO productRecpt = productRecptMapper.selectById(productRecptId);
        assertPojoEquals(reqVO, productRecpt);
    }

    @Test
    public void testUpdateProductRecpt_success() {
        // mock 数据
        ProductRecptDO dbProductRecpt = randomPojo(ProductRecptDO.class);
        productRecptMapper.insert(dbProductRecpt);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ProductRecptUpdateReqVO reqVO = randomPojo(ProductRecptUpdateReqVO.class, o -> {
            o.setId(dbProductRecpt.getId()); // 设置更新的 ID
        });

        // 调用
        productRecptService.updateProductRecpt(reqVO);
        // 校验是否更新正确
        ProductRecptDO productRecpt = productRecptMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, productRecpt);
    }

    @Test
    public void testUpdateProductRecpt_notExists() {
        // 准备参数
        ProductRecptUpdateReqVO reqVO = randomPojo(ProductRecptUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> productRecptService.updateProductRecpt(reqVO), PRODUCT_RECPT_NOT_EXISTS);
    }

    @Test
    public void testDeleteProductRecpt_success() {
        // mock 数据
        ProductRecptDO dbProductRecpt = randomPojo(ProductRecptDO.class);
        productRecptMapper.insert(dbProductRecpt);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbProductRecpt.getId();

        // 调用
        productRecptService.deleteProductRecpt(id);
       // 校验数据不存在了
       assertNull(productRecptMapper.selectById(id));
    }

    @Test
    public void testDeleteProductRecpt_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> productRecptService.deleteProductRecpt(id), PRODUCT_RECPT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProductRecptPage() {
       // mock 数据
       ProductRecptDO dbProductRecpt = randomPojo(ProductRecptDO.class, o -> { // 等会查询到
           o.setRecptCode(null);
           o.setRecptName(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
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
       productRecptMapper.insert(dbProductRecpt);
       // 测试 recptCode 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setRecptCode(null)));
       // 测试 recptName 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setRecptName(null)));
       // 测试 workorderId 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setWorkorderName(null)));
       // 测试 itemId 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setUnitOfMeasure(null)));
       // 测试 warehouseId 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAreaName(null)));
       // 测试 recptDate 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setRecptDate(null)));
       // 测试 status 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setCreateTime(null)));
       // 准备参数
       ProductRecptPageReqVO reqVO = new ProductRecptPageReqVO();
       reqVO.setRecptCode(null);
       reqVO.setRecptName(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
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
       PageResult<ProductRecptDO> pageResult = productRecptService.getProductRecptPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbProductRecpt, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProductRecptList() {
       // mock 数据
       ProductRecptDO dbProductRecpt = randomPojo(ProductRecptDO.class, o -> { // 等会查询到
           o.setRecptCode(null);
           o.setRecptName(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
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
       productRecptMapper.insert(dbProductRecpt);
       // 测试 recptCode 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setRecptCode(null)));
       // 测试 recptName 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setRecptName(null)));
       // 测试 workorderId 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setWorkorderName(null)));
       // 测试 itemId 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setUnitOfMeasure(null)));
       // 测试 warehouseId 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAreaName(null)));
       // 测试 recptDate 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setRecptDate(null)));
       // 测试 status 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       productRecptMapper.insert(cloneIgnoreId(dbProductRecpt, o -> o.setCreateTime(null)));
       // 准备参数
       ProductRecptExportReqVO reqVO = new ProductRecptExportReqVO();
       reqVO.setRecptCode(null);
       reqVO.setRecptName(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
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
       List<ProductRecptDO> list = productRecptService.getProductRecptList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbProductRecpt, list.get(0));
    }

}
