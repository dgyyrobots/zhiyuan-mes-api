package com.dofast.module.qms.service.ipqc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.qms.controller.admin.ipqc.vo.*;
import com.dofast.module.qms.dal.dataobject.ipqc.IpqcDO;
import com.dofast.module.qms.dal.mysql.ipqc.IpqcMapper;
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
 * {@link IpqcServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(IpqcServiceImpl.class)
public class IpqcServiceImplTest extends BaseDbUnitTest {

    @Resource
    private IpqcServiceImpl ipqcService;

    @Resource
    private IpqcMapper ipqcMapper;

    @Test
    public void testCreateIpqc_success() {
        // 准备参数
        IpqcCreateReqVO reqVO = randomPojo(IpqcCreateReqVO.class);

        // 调用
        Long ipqcId = ipqcService.createIpqc(reqVO);
        // 断言
        assertNotNull(ipqcId);
        // 校验记录的属性是否正确
        IpqcDO ipqc = ipqcMapper.selectById(ipqcId);
        assertPojoEquals(reqVO, ipqc);
    }

    @Test
    public void testUpdateIpqc_success() {
        // mock 数据
        IpqcDO dbIpqc = randomPojo(IpqcDO.class);
        ipqcMapper.insert(dbIpqc);// @Sql: 先插入出一条存在的数据
        // 准备参数
        IpqcUpdateReqVO reqVO = randomPojo(IpqcUpdateReqVO.class, o -> {
            o.setId(dbIpqc.getId()); // 设置更新的 ID
        });

        // 调用
        ipqcService.updateIpqc(reqVO);
        // 校验是否更新正确
        IpqcDO ipqc = ipqcMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, ipqc);
    }

    @Test
    public void testUpdateIpqc_notExists() {
        // 准备参数
        IpqcUpdateReqVO reqVO = randomPojo(IpqcUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> ipqcService.updateIpqc(reqVO), IPQC_NOT_EXISTS);
    }

    @Test
    public void testDeleteIpqc_success() {
        // mock 数据
        IpqcDO dbIpqc = randomPojo(IpqcDO.class);
        ipqcMapper.insert(dbIpqc);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbIpqc.getId();

        // 调用
        ipqcService.deleteIpqc(id);
       // 校验数据不存在了
       assertNull(ipqcMapper.selectById(id));
    }

    @Test
    public void testDeleteIpqc_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> ipqcService.deleteIpqc(id), IPQC_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIpqcPage() {
       // mock 数据
       IpqcDO dbIpqc = randomPojo(IpqcDO.class, o -> { // 等会查询到
           o.setIpqcCode(null);
           o.setIpqcName(null);
           o.setIpqcType(null);
           o.setTemplateId(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setTaskId(null);
           o.setTaskCode(null);
           o.setTaskName(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityCheck(null);
           o.setQuantityUnqualified(null);
           o.setQuantityQualified(null);
           o.setCrRate(null);
           o.setMajRate(null);
           o.setMinRate(null);
           o.setCrQuantity(null);
           o.setMajQuantity(null);
           o.setMinQuantity(null);
           o.setCheckResult(null);
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
       ipqcMapper.insert(dbIpqc);
       // 测试 ipqcCode 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setIpqcCode(null)));
       // 测试 ipqcName 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setIpqcName(null)));
       // 测试 ipqcType 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setIpqcType(null)));
       // 测试 templateId 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setTemplateId(null)));
       // 测试 workorderId 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setWorkorderName(null)));
       // 测试 taskId 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setTaskId(null)));
       // 测试 taskCode 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setTaskCode(null)));
       // 测试 taskName 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setTaskName(null)));
       // 测试 workstationId 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setWorkstationName(null)));
       // 测试 processId 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setProcessName(null)));
       // 测试 itemId 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityCheck 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setQuantityCheck(null)));
       // 测试 quantityUnqualified 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setQuantityUnqualified(null)));
       // 测试 quantityQualified 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setQuantityQualified(null)));
       // 测试 crRate 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setCrRate(null)));
       // 测试 majRate 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setMajRate(null)));
       // 测试 minRate 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setMinRate(null)));
       // 测试 crQuantity 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setCrQuantity(null)));
       // 测试 majQuantity 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setMajQuantity(null)));
       // 测试 minQuantity 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setMinQuantity(null)));
       // 测试 checkResult 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setCheckResult(null)));
       // 测试 inspectDate 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setInspectDate(null)));
       // 测试 inspector 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setInspector(null)));
       // 测试 status 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setCreateTime(null)));
       // 准备参数
       IpqcPageReqVO reqVO = new IpqcPageReqVO();
       reqVO.setIpqcCode(null);
       reqVO.setIpqcName(null);
       reqVO.setIpqcType(null);
       reqVO.setTemplateId(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setTaskId(null);
       reqVO.setTaskCode(null);
       reqVO.setTaskName(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityCheck(null);
       reqVO.setQuantityUnqualified(null);
       reqVO.setQuantityQualified(null);
       reqVO.setCrRate(null);
       reqVO.setMajRate(null);
       reqVO.setMinRate(null);
       reqVO.setCrQuantity(null);
       reqVO.setMajQuantity(null);
       reqVO.setMinQuantity(null);
       reqVO.setCheckResult(null);
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
       PageResult<IpqcDO> pageResult = ipqcService.getIpqcPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbIpqc, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIpqcList() {
       // mock 数据
       IpqcDO dbIpqc = randomPojo(IpqcDO.class, o -> { // 等会查询到
           o.setIpqcCode(null);
           o.setIpqcName(null);
           o.setIpqcType(null);
           o.setTemplateId(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setTaskId(null);
           o.setTaskCode(null);
           o.setTaskName(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityCheck(null);
           o.setQuantityUnqualified(null);
           o.setQuantityQualified(null);
           o.setCrRate(null);
           o.setMajRate(null);
           o.setMinRate(null);
           o.setCrQuantity(null);
           o.setMajQuantity(null);
           o.setMinQuantity(null);
           o.setCheckResult(null);
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
       ipqcMapper.insert(dbIpqc);
       // 测试 ipqcCode 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setIpqcCode(null)));
       // 测试 ipqcName 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setIpqcName(null)));
       // 测试 ipqcType 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setIpqcType(null)));
       // 测试 templateId 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setTemplateId(null)));
       // 测试 workorderId 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setWorkorderName(null)));
       // 测试 taskId 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setTaskId(null)));
       // 测试 taskCode 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setTaskCode(null)));
       // 测试 taskName 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setTaskName(null)));
       // 测试 workstationId 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setWorkstationName(null)));
       // 测试 processId 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setProcessName(null)));
       // 测试 itemId 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityCheck 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setQuantityCheck(null)));
       // 测试 quantityUnqualified 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setQuantityUnqualified(null)));
       // 测试 quantityQualified 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setQuantityQualified(null)));
       // 测试 crRate 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setCrRate(null)));
       // 测试 majRate 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setMajRate(null)));
       // 测试 minRate 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setMinRate(null)));
       // 测试 crQuantity 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setCrQuantity(null)));
       // 测试 majQuantity 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setMajQuantity(null)));
       // 测试 minQuantity 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setMinQuantity(null)));
       // 测试 checkResult 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setCheckResult(null)));
       // 测试 inspectDate 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setInspectDate(null)));
       // 测试 inspector 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setInspector(null)));
       // 测试 status 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       ipqcMapper.insert(cloneIgnoreId(dbIpqc, o -> o.setCreateTime(null)));
       // 准备参数
       IpqcExportReqVO reqVO = new IpqcExportReqVO();
       reqVO.setIpqcCode(null);
       reqVO.setIpqcName(null);
       reqVO.setIpqcType(null);
       reqVO.setTemplateId(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setTaskId(null);
       reqVO.setTaskCode(null);
       reqVO.setTaskName(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityCheck(null);
       reqVO.setQuantityUnqualified(null);
       reqVO.setQuantityQualified(null);
       reqVO.setCrRate(null);
       reqVO.setMajRate(null);
       reqVO.setMinRate(null);
       reqVO.setCrQuantity(null);
       reqVO.setMajQuantity(null);
       reqVO.setMinQuantity(null);
       reqVO.setCheckResult(null);
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
       List<IpqcDO> list = ipqcService.getIpqcList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbIpqc, list.get(0));
    }

}
