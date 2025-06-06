package com.dofast.module.wms.service.materialstock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.materialstock.vo.*;
import com.dofast.module.wms.dal.dataobject.materialstock.MaterialStockDO;
import com.dofast.module.wms.dal.mysql.materialstock.MaterialStockMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
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
 * {@link MaterialStockServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(MaterialStockServiceImpl.class)
public class MaterialStockServiceImplTest extends BaseDbUnitTest {

    @Resource
    private MaterialStockServiceImpl materialStockService;

    @Resource
    private MaterialStockMapper materialStockMapper;

    @Test
    public void testCreateMaterialStock_success() {
        // 准备参数
        MaterialStockCreateReqVO reqVO = randomPojo(MaterialStockCreateReqVO.class);

        // 调用
        Long materialStockId = materialStockService.createMaterialStock(reqVO);
        // 断言
        assertNotNull(materialStockId);
        // 校验记录的属性是否正确
        MaterialStockDO materialStock = materialStockMapper.selectById(materialStockId);
        assertPojoEquals(reqVO, materialStock);
    }

    @Test
    public void testUpdateMaterialStock_success() {
        // mock 数据
        MaterialStockDO dbMaterialStock = randomPojo(MaterialStockDO.class);
        materialStockMapper.insert(dbMaterialStock);// @Sql: 先插入出一条存在的数据
        // 准备参数
        MaterialStockUpdateReqVO reqVO = randomPojo(MaterialStockUpdateReqVO.class, o -> {
            o.setId(dbMaterialStock.getId()); // 设置更新的 ID
        });

        // 调用
        materialStockService.updateMaterialStock(reqVO);
        // 校验是否更新正确
        MaterialStockDO materialStock = materialStockMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, materialStock);
    }

    @Test
    public void testUpdateMaterialStock_notExists() {
        // 准备参数
        MaterialStockUpdateReqVO reqVO = randomPojo(MaterialStockUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> materialStockService.updateMaterialStock(reqVO), MATERIAL_STOCK_NOT_EXISTS);
    }

    @Test
    public void testDeleteMaterialStock_success() {
        // mock 数据
        MaterialStockDO dbMaterialStock = randomPojo(MaterialStockDO.class);
        materialStockMapper.insert(dbMaterialStock);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbMaterialStock.getId();

        // 调用
        materialStockService.deleteMaterialStock(id);
       // 校验数据不存在了
       assertNull(materialStockMapper.selectById(id));
    }

    @Test
    public void testDeleteMaterialStock_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> materialStockService.deleteMaterialStock(id), MATERIAL_STOCK_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMaterialStockPage() {
       // mock 数据
       MaterialStockDO dbMaterialStock = randomPojo(MaterialStockDO.class, o -> { // 等会查询到
           o.setItemTypeId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
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
           o.setVendorId(null);
           o.setVendorCode(null);
           o.setVendorName(null);
           o.setVendorNick(null);
           o.setQuantityOnhand(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setRecptDate(null);
           o.setExpireDate(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       materialStockMapper.insert(dbMaterialStock);
       // 测试 itemTypeId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setItemTypeId(null)));
       // 测试 itemId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setUnitOfMeasure(null)));
       // 测试 batchCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAreaName(null)));
       // 测试 vendorId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setVendorId(null)));
       // 测试 vendorCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setVendorCode(null)));
       // 测试 vendorName 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setVendorName(null)));
       // 测试 vendorNick 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setVendorNick(null)));
       // 测试 quantityOnhand 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setQuantityOnhand(null)));
       // 测试 workorderId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setWorkorderCode(null)));
       // 测试 recptDate 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setRecptDate(null)));
       // 测试 expireDate 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setExpireDate(null)));
       // 测试 attr1 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setCreateTime(null)));
       // 准备参数
       MaterialStockPageReqVO reqVO = new MaterialStockPageReqVO();
       reqVO.setItemTypeId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
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
       reqVO.setVendorId(null);
       reqVO.setVendorCode(null);
       reqVO.setVendorName(null);
       reqVO.setVendorNick(null);
       reqVO.setQuantityOnhand(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setRecptDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setExpireDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<MaterialStockDO> pageResult = materialStockService.getMaterialStockPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbMaterialStock, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMaterialStockList() {
       // mock 数据
       MaterialStockDO dbMaterialStock = randomPojo(MaterialStockDO.class, o -> { // 等会查询到
           o.setItemTypeId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
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
           o.setVendorId(null);
           o.setVendorCode(null);
           o.setVendorName(null);
           o.setVendorNick(null);
           o.setQuantityOnhand(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setRecptDate(null);
           o.setExpireDate(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       materialStockMapper.insert(dbMaterialStock);
       // 测试 itemTypeId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setItemTypeId(null)));
       // 测试 itemId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setUnitOfMeasure(null)));
       // 测试 batchCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAreaName(null)));
       // 测试 vendorId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setVendorId(null)));
       // 测试 vendorCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setVendorCode(null)));
       // 测试 vendorName 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setVendorName(null)));
       // 测试 vendorNick 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setVendorNick(null)));
       // 测试 quantityOnhand 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setQuantityOnhand(null)));
       // 测试 workorderId 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setWorkorderCode(null)));
       // 测试 recptDate 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setRecptDate(null)));
       // 测试 expireDate 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setExpireDate(null)));
       // 测试 attr1 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       materialStockMapper.insert(cloneIgnoreId(dbMaterialStock, o -> o.setCreateTime(null)));
       // 准备参数
       MaterialStockExportReqVO reqVO = new MaterialStockExportReqVO();
       reqVO.setItemTypeId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
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
       reqVO.setVendorId(null);
       reqVO.setVendorCode(null);
       reqVO.setVendorName(null);
       reqVO.setVendorNick(null);
       reqVO.setQuantityOnhand(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setRecptDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setExpireDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<MaterialStockDO> list = materialStockService.getMaterialStockList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbMaterialStock, list.get(0));
    }

}
