package com.dofast.module.wms.service.productsalse;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.productsalse.vo.*;
import com.dofast.module.wms.dal.dataobject.productsalse.ProductSalseDO;
import com.dofast.module.wms.dal.mysql.productsalse.ProductSalseMapper;
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
 * {@link ProductSalseServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(ProductSalseServiceImpl.class)
public class ProductSalseServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ProductSalseServiceImpl productSalseService;

    @Resource
    private ProductSalseMapper productSalseMapper;

    @Test
    public void testCreateProductSalse_success() {
        // 准备参数
        ProductSalseCreateReqVO reqVO = randomPojo(ProductSalseCreateReqVO.class);

        // 调用
        Long productSalseId = productSalseService.createProductSalse(reqVO);
        // 断言
        assertNotNull(productSalseId);
        // 校验记录的属性是否正确
        ProductSalseDO productSalse = productSalseMapper.selectById(productSalseId);
        assertPojoEquals(reqVO, productSalse);
    }

    @Test
    public void testUpdateProductSalse_success() {
        // mock 数据
        ProductSalseDO dbProductSalse = randomPojo(ProductSalseDO.class);
        productSalseMapper.insert(dbProductSalse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ProductSalseUpdateReqVO reqVO = randomPojo(ProductSalseUpdateReqVO.class, o -> {
            o.setId(dbProductSalse.getId()); // 设置更新的 ID
        });

        // 调用
        productSalseService.updateProductSalse(reqVO);
        // 校验是否更新正确
        ProductSalseDO productSalse = productSalseMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, productSalse);
    }

    @Test
    public void testUpdateProductSalse_notExists() {
        // 准备参数
        ProductSalseUpdateReqVO reqVO = randomPojo(ProductSalseUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> productSalseService.updateProductSalse(reqVO), PRODUCT_SALSE_NOT_EXISTS);
    }

    @Test
    public void testDeleteProductSalse_success() {
        // mock 数据
        ProductSalseDO dbProductSalse = randomPojo(ProductSalseDO.class);
        productSalseMapper.insert(dbProductSalse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbProductSalse.getId();

        // 调用
        productSalseService.deleteProductSalse(id);
       // 校验数据不存在了
       assertNull(productSalseMapper.selectById(id));
    }

    @Test
    public void testDeleteProductSalse_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> productSalseService.deleteProductSalse(id), PRODUCT_SALSE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProductSalsePage() {
       // mock 数据
       ProductSalseDO dbProductSalse = randomPojo(ProductSalseDO.class, o -> { // 等会查询到
           o.setSalseCode(null);
           o.setSalseName(null);
           o.setOqcId(null);
           o.setOqcCode(null);
           o.setSoCode(null);
           o.setClientId(null);
           o.setClientCode(null);
           o.setClientName(null);
           o.setClientNick(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setSalseDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       productSalseMapper.insert(dbProductSalse);
       // 测试 salseCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setSalseCode(null)));
       // 测试 salseName 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setSalseName(null)));
       // 测试 oqcId 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setOqcId(null)));
       // 测试 oqcCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setOqcCode(null)));
       // 测试 soCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setSoCode(null)));
       // 测试 clientId 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setClientName(null)));
       // 测试 clientNick 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setClientNick(null)));
       // 测试 warehouseId 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAreaName(null)));
       // 测试 salseDate 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setSalseDate(null)));
       // 测试 status 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setCreateTime(null)));
       // 准备参数
       ProductSalsePageReqVO reqVO = new ProductSalsePageReqVO();
       reqVO.setSalseCode(null);
       reqVO.setSalseName(null);
       reqVO.setOqcId(null);
       reqVO.setOqcCode(null);
       reqVO.setSoCode(null);
       reqVO.setClientId(null);
       reqVO.setClientCode(null);
       reqVO.setClientName(null);
       reqVO.setClientNick(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setSalseDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ProductSalseDO> pageResult = productSalseService.getProductSalsePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbProductSalse, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProductSalseList() {
       // mock 数据
       ProductSalseDO dbProductSalse = randomPojo(ProductSalseDO.class, o -> { // 等会查询到
           o.setSalseCode(null);
           o.setSalseName(null);
           o.setOqcId(null);
           o.setOqcCode(null);
           o.setSoCode(null);
           o.setClientId(null);
           o.setClientCode(null);
           o.setClientName(null);
           o.setClientNick(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setSalseDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       productSalseMapper.insert(dbProductSalse);
       // 测试 salseCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setSalseCode(null)));
       // 测试 salseName 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setSalseName(null)));
       // 测试 oqcId 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setOqcId(null)));
       // 测试 oqcCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setOqcCode(null)));
       // 测试 soCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setSoCode(null)));
       // 测试 clientId 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setClientName(null)));
       // 测试 clientNick 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setClientNick(null)));
       // 测试 warehouseId 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAreaName(null)));
       // 测试 salseDate 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setSalseDate(null)));
       // 测试 status 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       productSalseMapper.insert(cloneIgnoreId(dbProductSalse, o -> o.setCreateTime(null)));
       // 准备参数
       ProductSalseExportReqVO reqVO = new ProductSalseExportReqVO();
       reqVO.setSalseCode(null);
       reqVO.setSalseName(null);
       reqVO.setOqcId(null);
       reqVO.setOqcCode(null);
       reqVO.setSoCode(null);
       reqVO.setClientId(null);
       reqVO.setClientCode(null);
       reqVO.setClientName(null);
       reqVO.setClientNick(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setSalseDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ProductSalseDO> list = productSalseService.getProductSalseList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbProductSalse, list.get(0));
    }

}
