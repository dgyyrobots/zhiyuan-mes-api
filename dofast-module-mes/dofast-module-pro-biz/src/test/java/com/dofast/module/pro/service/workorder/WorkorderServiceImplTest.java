package com.dofast.module.pro.service.workorder;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.workorder.vo.*;
import com.dofast.module.pro.dal.dataobject.workorder.WorkorderDO;
import com.dofast.module.pro.dal.mysql.workorder.WorkorderMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link WorkorderServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(WorkorderServiceImpl.class)
public class WorkorderServiceImplTest extends BaseDbUnitTest {

    @Resource
    private WorkorderServiceImpl workorderService;

    @Resource
    private WorkorderMapper workorderMapper;

    @Test
    public void testCreateWorkorder_success() {
        // 准备参数
        WorkorderCreateReqVO reqVO = randomPojo(WorkorderCreateReqVO.class);

        // 调用
        Long workorderId = workorderService.createWorkorder(reqVO);
        // 断言
        assertNotNull(workorderId);
        // 校验记录的属性是否正确
        WorkorderDO workorder = workorderMapper.selectById(workorderId);
        assertPojoEquals(reqVO, workorder);
    }

    @Test
    public void testUpdateWorkorder_success() {
        // mock 数据
        WorkorderDO dbWorkorder = randomPojo(WorkorderDO.class);
        workorderMapper.insert(dbWorkorder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        WorkorderUpdateReqVO reqVO = randomPojo(WorkorderUpdateReqVO.class, o -> {
            o.setId(dbWorkorder.getId()); // 设置更新的 ID
        });

        // 调用
        workorderService.updateWorkorder(reqVO);
        // 校验是否更新正确
        WorkorderDO workorder = workorderMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, workorder);
    }

    @Test
    public void testUpdateWorkorder_notExists() {
        // 准备参数
        WorkorderUpdateReqVO reqVO = randomPojo(WorkorderUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> workorderService.updateWorkorder(reqVO), WORKORDER_NOT_EXISTS);
    }

    @Test
    public void testDeleteWorkorder_success() {
        // mock 数据
        WorkorderDO dbWorkorder = randomPojo(WorkorderDO.class);
        workorderMapper.insert(dbWorkorder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbWorkorder.getId();

        // 调用
        workorderService.deleteWorkorder(id);
       // 校验数据不存在了
       assertNull(workorderMapper.selectById(id));
    }

    @Test
    public void testDeleteWorkorder_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> workorderService.deleteWorkorder(id), WORKORDER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetWorkorderPage() {
       // mock 数据
       WorkorderDO dbWorkorder = randomPojo(WorkorderDO.class, o -> { // 等会查询到
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setOrderSource(null);
           o.setSourceCode(null);
           o.setProductId(null);
           o.setProductCode(null);
           o.setProductName(null);
           o.setProductSpc(null);
           o.setUnitOfMeasure(null);
           o.setQuantity(null);
           o.setQuantityProduced(null);
           o.setQuantityChanged(null);
           o.setQuantityScheduled(null);
           o.setClientId(null);
           o.setClientCode(null);
           o.setClientName(null);
           o.setBatchCode(null);
           o.setRequestDate(null);
           o.setParentId(null);
           o.setAncestors(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       workorderMapper.insert(dbWorkorder);
       // 测试 workorderCode 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setWorkorderName(null)));
       // 测试 orderSource 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setOrderSource(null)));
       // 测试 sourceCode 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setSourceCode(null)));
       // 测试 productId 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setProductId(null)));
       // 测试 productCode 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setProductCode(null)));
       // 测试 productName 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setProductName(null)));
       // 测试 productSpc 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setProductSpc(null)));
       // 测试 unitOfMeasure 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setUnitOfMeasure(null)));
       // 测试 quantity 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setQuantity(null)));
       // 测试 quantityProduced 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setQuantityProduced(null)));
       // 测试 quantityChanged 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setQuantityChanged(null)));
       // 测试 quantityScheduled 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setQuantityScheduled(null)));
       // 测试 clientId 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setClientName(null)));
       // 测试 batchCode 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setBatchCode(null)));
       // 测试 requestDate 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setRequestDate(null)));
       // 测试 parentId 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setParentId(null)));
       // 测试 ancestors 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setAncestors(null)));
       // 测试 status 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setCreateTime(null)));
       // 准备参数
       WorkorderPageReqVO reqVO = new WorkorderPageReqVO();
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setOrderSource(null);
       reqVO.setSourceCode(null);
       reqVO.setProductId(null);
       reqVO.setProductCode(null);
       reqVO.setProductName(null);
       reqVO.setProductSpc(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantity(null);
       reqVO.setQuantityProduced(null);
       reqVO.setQuantityChanged(null);
       reqVO.setQuantityScheduled(null);
       reqVO.setClientId(null);
       reqVO.setClientCode(null);
       reqVO.setClientName(null);
       reqVO.setBatchCode(null);
       reqVO.setRequestDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setParentId(null);
       reqVO.setAncestors(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<WorkorderDO> pageResult = workorderService.getWorkorderPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbWorkorder, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetWorkorderList() {
       // mock 数据
       WorkorderDO dbWorkorder = randomPojo(WorkorderDO.class, o -> { // 等会查询到
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setOrderSource(null);
           o.setSourceCode(null);
           o.setProductId(null);
           o.setProductCode(null);
           o.setProductName(null);
           o.setProductSpc(null);
           o.setUnitOfMeasure(null);
           o.setQuantity(null);
           o.setQuantityProduced(null);
           o.setQuantityChanged(null);
           o.setQuantityScheduled(null);
           o.setClientId(null);
           o.setClientCode(null);
           o.setClientName(null);
           o.setBatchCode(null);
           o.setRequestDate(null);
           o.setParentId(null);
           o.setAncestors(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       workorderMapper.insert(dbWorkorder);
       // 测试 workorderCode 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setWorkorderName(null)));
       // 测试 orderSource 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setOrderSource(null)));
       // 测试 sourceCode 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setSourceCode(null)));
       // 测试 productId 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setProductId(null)));
       // 测试 productCode 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setProductCode(null)));
       // 测试 productName 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setProductName(null)));
       // 测试 productSpc 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setProductSpc(null)));
       // 测试 unitOfMeasure 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setUnitOfMeasure(null)));
       // 测试 quantity 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setQuantity(null)));
       // 测试 quantityProduced 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setQuantityProduced(null)));
       // 测试 quantityChanged 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setQuantityChanged(null)));
       // 测试 quantityScheduled 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setQuantityScheduled(null)));
       // 测试 clientId 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setClientName(null)));
       // 测试 batchCode 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setBatchCode(null)));
       // 测试 requestDate 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setRequestDate(null)));
       // 测试 parentId 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setParentId(null)));
       // 测试 ancestors 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setAncestors(null)));
       // 测试 status 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       workorderMapper.insert(cloneIgnoreId(dbWorkorder, o -> o.setCreateTime(null)));
       // 准备参数
       WorkorderExportReqVO reqVO = new WorkorderExportReqVO();
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setOrderSource(null);
       reqVO.setSourceCode(null);
       reqVO.setProductId(null);
       reqVO.setProductCode(null);
       reqVO.setProductName(null);
       reqVO.setProductSpc(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantity(null);
       reqVO.setQuantityProduced(null);
       reqVO.setQuantityChanged(null);
       reqVO.setQuantityScheduled(null);
       reqVO.setClientId(null);
       reqVO.setClientCode(null);
       reqVO.setClientName(null);
       reqVO.setBatchCode(null);
       reqVO.setParentId(null);
       reqVO.setAncestors(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);

       // 调用
       List<WorkorderDO> list = workorderService.getWorkorderList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbWorkorder, list.get(0));
    }

}
