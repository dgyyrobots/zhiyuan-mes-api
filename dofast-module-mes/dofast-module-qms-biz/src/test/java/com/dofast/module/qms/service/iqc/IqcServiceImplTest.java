package com.dofast.module.qms.service.iqc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.qms.controller.admin.iqc.vo.*;
import com.dofast.module.qms.dal.dataobject.iqc.IqcDO;
import com.dofast.module.qms.dal.mysql.iqc.IqcMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.qms.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link IqcServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(IqcServiceImpl.class)
public class IqcServiceImplTest extends BaseDbUnitTest {

    @Resource
    private IqcServiceImpl iqcService;

    @Resource
    private IqcMapper iqcMapper;

    @Test
    public void testCreateIqc_success() {
        // 准备参数
        IqcCreateReqVO reqVO = randomPojo(IqcCreateReqVO.class);

        // 调用
        Long iqcId = iqcService.createIqc(reqVO);
        // 断言
        assertNotNull(iqcId);
        // 校验记录的属性是否正确
        IqcDO iqc = iqcMapper.selectById(iqcId);
        assertPojoEquals(reqVO, iqc);
    }

    @Test
    public void testUpdateIqc_success() {
        // mock 数据
        IqcDO dbIqc = randomPojo(IqcDO.class);
        iqcMapper.insert(dbIqc);// @Sql: 先插入出一条存在的数据
        // 准备参数
        IqcUpdateReqVO reqVO = randomPojo(IqcUpdateReqVO.class, o -> {
            o.setId(dbIqc.getId()); // 设置更新的 ID
        });

        // 调用
        iqcService.updateIqc(reqVO);
        // 校验是否更新正确
        IqcDO iqc = iqcMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, iqc);
    }

    @Test
    public void testUpdateIqc_notExists() {
        // 准备参数
        IqcUpdateReqVO reqVO = randomPojo(IqcUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> iqcService.updateIqc(reqVO), IQC_NOT_EXISTS);
    }

    @Test
    public void testDeleteIqc_success() {
        // mock 数据
        IqcDO dbIqc = randomPojo(IqcDO.class);
        iqcMapper.insert(dbIqc);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbIqc.getId();

        // 调用
        iqcService.deleteIqc(id);
       // 校验数据不存在了
       assertNull(iqcMapper.selectById(id));
    }

    @Test
    public void testDeleteIqc_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> iqcService.deleteIqc(id), IQC_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIqcPage() {
       // mock 数据
       IqcDO dbIqc = randomPojo(IqcDO.class, o -> { // 等会查询到
           o.setIqcCode(null);
           o.setIqcName(null);
           o.setTemplateId(null);
           o.setVendorId(null);
           o.setVendorCode(null);
           o.setVendorName(null);
           o.setVendorNick(null);
           o.setVendorBatch(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityMinCheck(null);
           o.setQuantityMaxUnqualified(null);
           o.setQuantityRecived(null);
           o.setQuantityCheck(null);
           o.setQuantityUnqualified(null);
           o.setCrRate(null);
           o.setMajRate(null);
           o.setMinRate(null);
           o.setCrQuantity(null);
           o.setMajQuantity(null);
           o.setMinQuantity(null);
           o.setCheckResult(null);
           o.setReciveDate(null);
           o.setInspectDate(null);
           o.setInspector(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       iqcMapper.insert(dbIqc);
       // 测试 iqcCode 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setIqcCode(null)));
       // 测试 iqcName 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setIqcName(null)));
       // 测试 templateId 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setTemplateId(null)));
       // 测试 vendorId 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setVendorId(null)));
       // 测试 vendorCode 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setVendorCode(null)));
       // 测试 vendorName 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setVendorName(null)));
       // 测试 vendorNick 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setVendorNick(null)));
       // 测试 vendorBatch 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setVendorBatch(null)));
       // 测试 itemId 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityMinCheck 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setQuantityMinCheck(null)));
       // 测试 quantityMaxUnqualified 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setQuantityMaxUnqualified(null)));
       // 测试 quantityRecived 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setQuantityRecived(null)));
       // 测试 quantityCheck 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setQuantityCheck(null)));
       // 测试 quantityUnqualified 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setQuantityUnqualified(null)));
       // 测试 crRate 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setCrRate(null)));
       // 测试 majRate 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setMajRate(null)));
       // 测试 minRate 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setMinRate(null)));
       // 测试 crQuantity 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setCrQuantity(null)));
       // 测试 majQuantity 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setMajQuantity(null)));
       // 测试 minQuantity 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setMinQuantity(null)));
       // 测试 checkResult 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setCheckResult(null)));
       // 测试 reciveDate 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setReciveDate(null)));
       // 测试 inspectDate 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setInspectDate(null)));
       // 测试 inspector 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setInspector(null)));
       // 测试 status 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setCreateTime(null)));
       // 准备参数
       IqcPageReqVO reqVO = new IqcPageReqVO();
       reqVO.setIqcCode(null);
       reqVO.setIqcName(null);
       reqVO.setTemplateId(null);
       reqVO.setVendorId(null);
       reqVO.setVendorCode(null);
       reqVO.setVendorName(null);
       reqVO.setVendorNick(null);
       reqVO.setVendorBatch(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityMinCheck(null);
       reqVO.setQuantityMaxUnqualified(null);
       reqVO.setQuantityRecived(null);
       reqVO.setQuantityCheck(null);
       reqVO.setQuantityUnqualified(null);
       reqVO.setCrRate(null);
       reqVO.setMajRate(null);
       reqVO.setMinRate(null);
       reqVO.setCrQuantity(null);
       reqVO.setMajQuantity(null);
       reqVO.setMinQuantity(null);
       reqVO.setCheckResult(null);
       reqVO.setReciveDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setInspectDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setInspector(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<IqcDO> pageResult = iqcService.getIqcPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbIqc, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIqcList() {
       // mock 数据
       IqcDO dbIqc = randomPojo(IqcDO.class, o -> { // 等会查询到
           o.setIqcCode(null);
           o.setIqcName(null);
           o.setTemplateId(null);
           o.setVendorId(null);
           o.setVendorCode(null);
           o.setVendorName(null);
           o.setVendorNick(null);
           o.setVendorBatch(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityMinCheck(null);
           o.setQuantityMaxUnqualified(null);
           o.setQuantityRecived(null);
           o.setQuantityCheck(null);
           o.setQuantityUnqualified(null);
           o.setCrRate(null);
           o.setMajRate(null);
           o.setMinRate(null);
           o.setCrQuantity(null);
           o.setMajQuantity(null);
           o.setMinQuantity(null);
           o.setCheckResult(null);
           o.setReciveDate(null);
           o.setInspectDate(null);
           o.setInspector(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       iqcMapper.insert(dbIqc);
       // 测试 iqcCode 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setIqcCode(null)));
       // 测试 iqcName 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setIqcName(null)));
       // 测试 templateId 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setTemplateId(null)));
       // 测试 vendorId 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setVendorId(null)));
       // 测试 vendorCode 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setVendorCode(null)));
       // 测试 vendorName 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setVendorName(null)));
       // 测试 vendorNick 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setVendorNick(null)));
       // 测试 vendorBatch 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setVendorBatch(null)));
       // 测试 itemId 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityMinCheck 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setQuantityMinCheck(null)));
       // 测试 quantityMaxUnqualified 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setQuantityMaxUnqualified(null)));
       // 测试 quantityRecived 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setQuantityRecived(null)));
       // 测试 quantityCheck 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setQuantityCheck(null)));
       // 测试 quantityUnqualified 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setQuantityUnqualified(null)));
       // 测试 crRate 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setCrRate(null)));
       // 测试 majRate 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setMajRate(null)));
       // 测试 minRate 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setMinRate(null)));
       // 测试 crQuantity 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setCrQuantity(null)));
       // 测试 majQuantity 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setMajQuantity(null)));
       // 测试 minQuantity 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setMinQuantity(null)));
       // 测试 checkResult 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setCheckResult(null)));
       // 测试 reciveDate 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setReciveDate(null)));
       // 测试 inspectDate 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setInspectDate(null)));
       // 测试 inspector 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setInspector(null)));
       // 测试 status 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       iqcMapper.insert(cloneIgnoreId(dbIqc, o -> o.setCreateTime(null)));
       // 准备参数
       IqcExportReqVO reqVO = new IqcExportReqVO();
       reqVO.setIqcCode(null);
       reqVO.setIqcName(null);
       reqVO.setTemplateId(null);
       reqVO.setVendorId(null);
       reqVO.setVendorCode(null);
       reqVO.setVendorName(null);
       reqVO.setVendorNick(null);
       reqVO.setVendorBatch(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityMinCheck(null);
       reqVO.setQuantityMaxUnqualified(null);
       reqVO.setQuantityRecived(null);
       reqVO.setQuantityCheck(null);
       reqVO.setQuantityUnqualified(null);
       reqVO.setCrRate(null);
       reqVO.setMajRate(null);
       reqVO.setMinRate(null);
       reqVO.setCrQuantity(null);
       reqVO.setMajQuantity(null);
       reqVO.setMinQuantity(null);
       reqVO.setCheckResult(null);
       reqVO.setReciveDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setInspectDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setInspector(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<IqcDO> list = iqcService.getIqcList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbIqc, list.get(0));
    }

}
