package com.dofast.module.wms.service.package1;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.package1.vo.*;
import com.dofast.module.wms.dal.dataobject.package1.PackageDO;
import com.dofast.module.wms.dal.mysql.package1.PackageMapper;
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
 * {@link PackageServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(PackageServiceImpl.class)
public class PackageServiceImplTest extends BaseDbUnitTest {

    @Resource
    private PackageServiceImpl packageService;

    @Resource
    private PackageMapper packageMapper;

    @Test
    public void testCreatePackage_success() {
        // 准备参数
        PackageCreateReqVO reqVO = randomPojo(PackageCreateReqVO.class);

        // 调用
        Long packageId = packageService.createPackage(reqVO);
        // 断言
        assertNotNull(packageId);
        // 校验记录的属性是否正确
        PackageDO packageDO = packageMapper.selectById(packageId);
        assertPojoEquals(reqVO, packageDO);
    }

    @Test
    public void testUpdatePackage_success() {
        // mock 数据
        PackageDO dbPackage = randomPojo(PackageDO.class);
        packageMapper.insert(dbPackage);// @Sql: 先插入出一条存在的数据
        // 准备参数
        PackageUpdateReqVO reqVO = randomPojo(PackageUpdateReqVO.class, o -> {
            o.setId(dbPackage.getId()); // 设置更新的 ID
        });

        // 调用
        packageService.updatePackage(reqVO);
        // 校验是否更新正确
        PackageDO packageDO = packageMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, packageDO);
    }

    @Test
    public void testUpdatePackage_notExists() {
        // 准备参数
        PackageUpdateReqVO reqVO = randomPojo(PackageUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> packageService.updatePackage(reqVO), PACKAGE_NOT_EXISTS);
    }

    @Test
    public void testDeletePackage_success() {
        // mock 数据
        PackageDO dbPackage = randomPojo(PackageDO.class);
        packageMapper.insert(dbPackage);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbPackage.getId();

        // 调用
        packageService.deletePackage(id);
       // 校验数据不存在了
       assertNull(packageMapper.selectById(id));
    }

    @Test
    public void testDeletePackage_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> packageService.deletePackage(id), PACKAGE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPackagePage() {
       // mock 数据
       PackageDO dbPackage = randomPojo(PackageDO.class, o -> { // 等会查询到
           o.setParentId(null);
           o.setAncestors(null);
           o.setPackageCode(null);
           o.setBarcodeId(null);
           o.setBarcodeContent(null);
           o.setBarcodeUrl(null);
           o.setPackageDate(null);
           o.setSoCode(null);
           o.setInvoiceCode(null);
           o.setClientId(null);
           o.setClientCode(null);
           o.setClientName(null);
           o.setClientNick(null);
           o.setPackageLength(null);
           o.setPackageWidth(null);
           o.setPackageHeight(null);
           o.setSizeUnit(null);
           o.setNetWeight(null);
           o.setCrossWeight(null);
           o.setWeightUnit(null);
           o.setInspector(null);
           o.setInspectorName(null);
           o.setStatus(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       packageMapper.insert(dbPackage);
       // 测试 parentId 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setParentId(null)));
       // 测试 ancestors 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setAncestors(null)));
       // 测试 packageCode 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setPackageCode(null)));
       // 测试 barcodeId 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setBarcodeId(null)));
       // 测试 barcodeContent 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setBarcodeContent(null)));
       // 测试 barcodeUrl 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setBarcodeUrl(null)));
       // 测试 packageDate 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setPackageDate(null)));
       // 测试 soCode 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setSoCode(null)));
       // 测试 invoiceCode 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setInvoiceCode(null)));
       // 测试 clientId 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setClientName(null)));
       // 测试 clientNick 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setClientNick(null)));
       // 测试 packageLength 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setPackageLength(null)));
       // 测试 packageWidth 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setPackageWidth(null)));
       // 测试 packageHeight 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setPackageHeight(null)));
       // 测试 sizeUnit 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setSizeUnit(null)));
       // 测试 netWeight 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setNetWeight(null)));
       // 测试 crossWeight 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setCrossWeight(null)));
       // 测试 weightUnit 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setWeightUnit(null)));
       // 测试 inspector 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setInspector(null)));
       // 测试 inspectorName 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setInspectorName(null)));
       // 测试 status 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setStatus(null)));
       // 测试 enableFlag 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setCreateTime(null)));
       // 准备参数
       PackagePageReqVO reqVO = new PackagePageReqVO();
       reqVO.setParentId(null);
       reqVO.setAncestors(null);
       reqVO.setPackageCode(null);
       reqVO.setBarcodeId(null);
       reqVO.setBarcodeContent(null);
       reqVO.setBarcodeUrl(null);
       reqVO.setPackageDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setSoCode(null);
       reqVO.setInvoiceCode(null);
       reqVO.setClientId(null);
       reqVO.setClientCode(null);
       reqVO.setClientName(null);
       reqVO.setClientNick(null);
       reqVO.setPackageLength(null);
       reqVO.setPackageWidth(null);
       reqVO.setPackageHeight(null);
       reqVO.setSizeUnit(null);
       reqVO.setNetWeight(null);
       reqVO.setCrossWeight(null);
       reqVO.setWeightUnit(null);
       reqVO.setInspector(null);
       reqVO.setInspectorName(null);
       reqVO.setStatus(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<PackageDO> pageResult = packageService.getPackagePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbPackage, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPackageList() {
       // mock 数据
       PackageDO dbPackage = randomPojo(PackageDO.class, o -> { // 等会查询到
           o.setParentId(null);
           o.setAncestors(null);
           o.setPackageCode(null);
           o.setBarcodeId(null);
           o.setBarcodeContent(null);
           o.setBarcodeUrl(null);
           o.setPackageDate(null);
           o.setSoCode(null);
           o.setInvoiceCode(null);
           o.setClientId(null);
           o.setClientCode(null);
           o.setClientName(null);
           o.setClientNick(null);
           o.setPackageLength(null);
           o.setPackageWidth(null);
           o.setPackageHeight(null);
           o.setSizeUnit(null);
           o.setNetWeight(null);
           o.setCrossWeight(null);
           o.setWeightUnit(null);
           o.setInspector(null);
           o.setInspectorName(null);
           o.setStatus(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       packageMapper.insert(dbPackage);
       // 测试 parentId 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setParentId(null)));
       // 测试 ancestors 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setAncestors(null)));
       // 测试 packageCode 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setPackageCode(null)));
       // 测试 barcodeId 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setBarcodeId(null)));
       // 测试 barcodeContent 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setBarcodeContent(null)));
       // 测试 barcodeUrl 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setBarcodeUrl(null)));
       // 测试 packageDate 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setPackageDate(null)));
       // 测试 soCode 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setSoCode(null)));
       // 测试 invoiceCode 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setInvoiceCode(null)));
       // 测试 clientId 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setClientName(null)));
       // 测试 clientNick 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setClientNick(null)));
       // 测试 packageLength 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setPackageLength(null)));
       // 测试 packageWidth 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setPackageWidth(null)));
       // 测试 packageHeight 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setPackageHeight(null)));
       // 测试 sizeUnit 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setSizeUnit(null)));
       // 测试 netWeight 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setNetWeight(null)));
       // 测试 crossWeight 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setCrossWeight(null)));
       // 测试 weightUnit 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setWeightUnit(null)));
       // 测试 inspector 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setInspector(null)));
       // 测试 inspectorName 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setInspectorName(null)));
       // 测试 status 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setStatus(null)));
       // 测试 enableFlag 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       packageMapper.insert(cloneIgnoreId(dbPackage, o -> o.setCreateTime(null)));
       // 准备参数
       PackageExportReqVO reqVO = new PackageExportReqVO();
       reqVO.setParentId(null);
       reqVO.setAncestors(null);
       reqVO.setPackageCode(null);
       reqVO.setBarcodeId(null);
       reqVO.setBarcodeContent(null);
       reqVO.setBarcodeUrl(null);
       reqVO.setPackageDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setSoCode(null);
       reqVO.setInvoiceCode(null);
       reqVO.setClientId(null);
       reqVO.setClientCode(null);
       reqVO.setClientName(null);
       reqVO.setClientNick(null);
       reqVO.setPackageLength(null);
       reqVO.setPackageWidth(null);
       reqVO.setPackageHeight(null);
       reqVO.setSizeUnit(null);
       reqVO.setNetWeight(null);
       reqVO.setCrossWeight(null);
       reqVO.setWeightUnit(null);
       reqVO.setInspector(null);
       reqVO.setInspectorName(null);
       reqVO.setStatus(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<PackageDO> list = packageService.getPackageList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbPackage, list.get(0));
    }

}
