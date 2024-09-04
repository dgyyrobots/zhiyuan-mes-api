package com.dofast.module.wms.service.packageline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.packageline.vo.*;
import com.dofast.module.wms.dal.dataobject.packageline.PackageLineDO;
import com.dofast.module.wms.dal.mysql.packageline.PackageLineMapper;
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
 * {@link PackageLineServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(PackageLineServiceImpl.class)
public class PackageLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private PackageLineServiceImpl packageLineService;

    @Resource
    private PackageLineMapper packageLineMapper;

    @Test
    public void testCreatePackageLine_success() {
        // 准备参数
        PackageLineCreateReqVO reqVO = randomPojo(PackageLineCreateReqVO.class);

        // 调用
        Long packageLineId = packageLineService.createPackageLine(reqVO);
        // 断言
        assertNotNull(packageLineId);
        // 校验记录的属性是否正确
        PackageLineDO packageLine = packageLineMapper.selectById(packageLineId);
        assertPojoEquals(reqVO, packageLine);
    }

    @Test
    public void testUpdatePackageLine_success() {
        // mock 数据
        PackageLineDO dbPackageLine = randomPojo(PackageLineDO.class);
        packageLineMapper.insert(dbPackageLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        PackageLineUpdateReqVO reqVO = randomPojo(PackageLineUpdateReqVO.class, o -> {
            o.setId(dbPackageLine.getId()); // 设置更新的 ID
        });

        // 调用
        packageLineService.updatePackageLine(reqVO);
        // 校验是否更新正确
        PackageLineDO packageLine = packageLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, packageLine);
    }

    @Test
    public void testUpdatePackageLine_notExists() {
        // 准备参数
        PackageLineUpdateReqVO reqVO = randomPojo(PackageLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> packageLineService.updatePackageLine(reqVO), PACKAGE_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeletePackageLine_success() {
        // mock 数据
        PackageLineDO dbPackageLine = randomPojo(PackageLineDO.class);
        packageLineMapper.insert(dbPackageLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbPackageLine.getId();

        // 调用
        packageLineService.deletePackageLine(id);
       // 校验数据不存在了
       assertNull(packageLineMapper.selectById(id));
    }

    @Test
    public void testDeletePackageLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> packageLineService.deletePackageLine(id), PACKAGE_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPackageLinePage() {
       // mock 数据
       PackageLineDO dbPackageLine = randomPojo(PackageLineDO.class, o -> { // 等会查询到
           o.setPackageId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityPackage(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
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
           o.setExpireDate(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       packageLineMapper.insert(dbPackageLine);
       // 测试 packageId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setPackageId(null)));
       // 测试 materialStockId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityPackage 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setQuantityPackage(null)));
       // 测试 workorderId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setWorkorderCode(null)));
       // 测试 batchCode 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAreaName(null)));
       // 测试 expireDate 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setExpireDate(null)));
       // 测试 remark 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setCreateTime(null)));
       // 准备参数
       PackageLinePageReqVO reqVO = new PackageLinePageReqVO();
       reqVO.setPackageId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityPackage(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
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
       reqVO.setExpireDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<PackageLineDO> pageResult = packageLineService.getPackageLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbPackageLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPackageLineList() {
       // mock 数据
       PackageLineDO dbPackageLine = randomPojo(PackageLineDO.class, o -> { // 等会查询到
           o.setPackageId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityPackage(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
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
           o.setExpireDate(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       packageLineMapper.insert(dbPackageLine);
       // 测试 packageId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setPackageId(null)));
       // 测试 materialStockId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityPackage 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setQuantityPackage(null)));
       // 测试 workorderId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setWorkorderCode(null)));
       // 测试 batchCode 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAreaName(null)));
       // 测试 expireDate 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setExpireDate(null)));
       // 测试 remark 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       packageLineMapper.insert(cloneIgnoreId(dbPackageLine, o -> o.setCreateTime(null)));
       // 准备参数
       PackageLineExportReqVO reqVO = new PackageLineExportReqVO();
       reqVO.setPackageId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityPackage(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
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
       reqVO.setExpireDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<PackageLineDO> list = packageLineService.getPackageLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbPackageLine, list.get(0));
    }

}
