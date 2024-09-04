package com.dofast.module.qms.service.oqc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.qms.controller.admin.oqc.vo.*;
import com.dofast.module.qms.dal.dataobject.oqc.OqcDO;
import com.dofast.module.qms.dal.mysql.oqc.OqcMapper;
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
 * {@link OqcServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(OqcServiceImpl.class)
public class OqcServiceImplTest extends BaseDbUnitTest {

    @Resource
    private OqcServiceImpl oqcService;

    @Resource
    private OqcMapper oqcMapper;

    @Test
    public void testCreateOqc_success() {
        // 准备参数
        OqcCreateReqVO reqVO = randomPojo(OqcCreateReqVO.class);

        // 调用
        Long oqcId = oqcService.createOqc(reqVO);
        // 断言
        assertNotNull(oqcId);
        // 校验记录的属性是否正确
        OqcDO oqc = oqcMapper.selectById(oqcId);
        assertPojoEquals(reqVO, oqc);
    }

    @Test
    public void testUpdateOqc_success() {
        // mock 数据
        OqcDO dbOqc = randomPojo(OqcDO.class);
        oqcMapper.insert(dbOqc);// @Sql: 先插入出一条存在的数据
        // 准备参数
        OqcUpdateReqVO reqVO = randomPojo(OqcUpdateReqVO.class, o -> {
            o.setId(dbOqc.getId()); // 设置更新的 ID
        });

        // 调用
        oqcService.updateOqc(reqVO);
        // 校验是否更新正确
        OqcDO oqc = oqcMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, oqc);
    }

    @Test
    public void testUpdateOqc_notExists() {
        // 准备参数
        OqcUpdateReqVO reqVO = randomPojo(OqcUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> oqcService.updateOqc(reqVO), OQC_NOT_EXISTS);
    }

    @Test
    public void testDeleteOqc_success() {
        // mock 数据
        OqcDO dbOqc = randomPojo(OqcDO.class);
        oqcMapper.insert(dbOqc);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbOqc.getId();

        // 调用
        oqcService.deleteOqc(id);
       // 校验数据不存在了
       assertNull(oqcMapper.selectById(id));
    }

    @Test
    public void testDeleteOqc_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> oqcService.deleteOqc(id), OQC_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOqcPage() {
       // mock 数据
       OqcDO dbOqc = randomPojo(OqcDO.class, o -> { // 等会查询到
           o.setOqcCode(null);
           o.setOqcName(null);
           o.setTemplateId(null);
           o.setClientId(null);
           o.setClientCode(null);
           o.setClientName(null);
           o.setBatchCode(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityMinCheck(null);
           o.setQuantityMaxUnqualified(null);
           o.setQuantityOut(null);
           o.setQuantityCheck(null);
           o.setQuantityUnqualified(null);
           o.setQuantityQuanlified(null);
           o.setCrRate(null);
           o.setMajRate(null);
           o.setMinRate(null);
           o.setCrQuantity(null);
           o.setMajQuantity(null);
           o.setMinQuantity(null);
           o.setCheckResult(null);
           o.setOutDate(null);
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
       oqcMapper.insert(dbOqc);
       // 测试 oqcCode 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setOqcCode(null)));
       // 测试 oqcName 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setOqcName(null)));
       // 测试 templateId 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setTemplateId(null)));
       // 测试 clientId 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setClientName(null)));
       // 测试 batchCode 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setBatchCode(null)));
       // 测试 itemId 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityMinCheck 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setQuantityMinCheck(null)));
       // 测试 quantityMaxUnqualified 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setQuantityMaxUnqualified(null)));
       // 测试 quantityOut 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setQuantityOut(null)));
       // 测试 quantityCheck 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setQuantityCheck(null)));
       // 测试 quantityUnqualified 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setQuantityUnqualified(null)));
       // 测试 quantityQuanlified 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setQuantityQuanlified(null)));
       // 测试 crRate 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setCrRate(null)));
       // 测试 majRate 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setMajRate(null)));
       // 测试 minRate 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setMinRate(null)));
       // 测试 crQuantity 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setCrQuantity(null)));
       // 测试 majQuantity 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setMajQuantity(null)));
       // 测试 minQuantity 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setMinQuantity(null)));
       // 测试 checkResult 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setCheckResult(null)));
       // 测试 outDate 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setOutDate(null)));
       // 测试 inspectDate 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setInspectDate(null)));
       // 测试 inspector 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setInspector(null)));
       // 测试 status 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setCreateTime(null)));
       // 准备参数
       OqcPageReqVO reqVO = new OqcPageReqVO();
       reqVO.setOqcCode(null);
       reqVO.setOqcName(null);
       reqVO.setTemplateId(null);
       reqVO.setClientId(null);
       reqVO.setClientCode(null);
       reqVO.setClientName(null);
       reqVO.setBatchCode(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityMinCheck(null);
       reqVO.setQuantityMaxUnqualified(null);
       reqVO.setQuantityOut(null);
       reqVO.setQuantityCheck(null);
       reqVO.setQuantityUnqualified(null);
       reqVO.setQuantityQuanlified(null);
       reqVO.setCrRate(null);
       reqVO.setMajRate(null);
       reqVO.setMinRate(null);
       reqVO.setCrQuantity(null);
       reqVO.setMajQuantity(null);
       reqVO.setMinQuantity(null);
       reqVO.setCheckResult(null);
       reqVO.setOutDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
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
       PageResult<OqcDO> pageResult = oqcService.getOqcPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbOqc, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOqcList() {
       // mock 数据
       OqcDO dbOqc = randomPojo(OqcDO.class, o -> { // 等会查询到
           o.setOqcCode(null);
           o.setOqcName(null);
           o.setTemplateId(null);
           o.setClientId(null);
           o.setClientCode(null);
           o.setClientName(null);
           o.setBatchCode(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityMinCheck(null);
           o.setQuantityMaxUnqualified(null);
           o.setQuantityOut(null);
           o.setQuantityCheck(null);
           o.setQuantityUnqualified(null);
           o.setQuantityQuanlified(null);
           o.setCrRate(null);
           o.setMajRate(null);
           o.setMinRate(null);
           o.setCrQuantity(null);
           o.setMajQuantity(null);
           o.setMinQuantity(null);
           o.setCheckResult(null);
           o.setOutDate(null);
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
       oqcMapper.insert(dbOqc);
       // 测试 oqcCode 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setOqcCode(null)));
       // 测试 oqcName 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setOqcName(null)));
       // 测试 templateId 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setTemplateId(null)));
       // 测试 clientId 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setClientName(null)));
       // 测试 batchCode 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setBatchCode(null)));
       // 测试 itemId 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityMinCheck 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setQuantityMinCheck(null)));
       // 测试 quantityMaxUnqualified 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setQuantityMaxUnqualified(null)));
       // 测试 quantityOut 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setQuantityOut(null)));
       // 测试 quantityCheck 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setQuantityCheck(null)));
       // 测试 quantityUnqualified 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setQuantityUnqualified(null)));
       // 测试 quantityQuanlified 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setQuantityQuanlified(null)));
       // 测试 crRate 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setCrRate(null)));
       // 测试 majRate 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setMajRate(null)));
       // 测试 minRate 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setMinRate(null)));
       // 测试 crQuantity 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setCrQuantity(null)));
       // 测试 majQuantity 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setMajQuantity(null)));
       // 测试 minQuantity 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setMinQuantity(null)));
       // 测试 checkResult 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setCheckResult(null)));
       // 测试 outDate 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setOutDate(null)));
       // 测试 inspectDate 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setInspectDate(null)));
       // 测试 inspector 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setInspector(null)));
       // 测试 status 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       oqcMapper.insert(cloneIgnoreId(dbOqc, o -> o.setCreateTime(null)));
       // 准备参数
       OqcExportReqVO reqVO = new OqcExportReqVO();
       reqVO.setOqcCode(null);
       reqVO.setOqcName(null);
       reqVO.setTemplateId(null);
       reqVO.setClientId(null);
       reqVO.setClientCode(null);
       reqVO.setClientName(null);
       reqVO.setBatchCode(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityMinCheck(null);
       reqVO.setQuantityMaxUnqualified(null);
       reqVO.setQuantityOut(null);
       reqVO.setQuantityCheck(null);
       reqVO.setQuantityUnqualified(null);
       reqVO.setQuantityQuanlified(null);
       reqVO.setCrRate(null);
       reqVO.setMajRate(null);
       reqVO.setMinRate(null);
       reqVO.setCrQuantity(null);
       reqVO.setMajQuantity(null);
       reqVO.setMinQuantity(null);
       reqVO.setCheckResult(null);
       reqVO.setOutDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
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
       List<OqcDO> list = oqcService.getOqcList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbOqc, list.get(0));
    }

}
