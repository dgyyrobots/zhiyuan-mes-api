package com.dofast.module.pro.service.workorderbom;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.workorderbom.vo.*;
import com.dofast.module.pro.dal.dataobject.workorderbom.WorkorderBomDO;
import com.dofast.module.pro.dal.mysql.workorderbom.WorkorderBomMapper;
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
 * {@link WorkorderBomServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(WorkorderBomServiceImpl.class)
public class WorkorderBomServiceImplTest extends BaseDbUnitTest {

    @Resource
    private WorkorderBomServiceImpl workorderBomService;

    @Resource
    private WorkorderBomMapper workorderBomMapper;

    @Test
    public void testCreateWorkorderBom_success() {
        // 准备参数
        WorkorderBomCreateReqVO reqVO = randomPojo(WorkorderBomCreateReqVO.class);

        // 调用
        Long workorderBomId = workorderBomService.createWorkorderBom(reqVO);
        // 断言
        assertNotNull(workorderBomId);
        // 校验记录的属性是否正确
        WorkorderBomDO workorderBom = workorderBomMapper.selectById(workorderBomId);
        assertPojoEquals(reqVO, workorderBom);
    }

    @Test
    public void testUpdateWorkorderBom_success() {
        // mock 数据
        WorkorderBomDO dbWorkorderBom = randomPojo(WorkorderBomDO.class);
        workorderBomMapper.insert(dbWorkorderBom);// @Sql: 先插入出一条存在的数据
        // 准备参数
        WorkorderBomUpdateReqVO reqVO = randomPojo(WorkorderBomUpdateReqVO.class, o -> {
            o.setId(dbWorkorderBom.getId()); // 设置更新的 ID
        });

        // 调用
        workorderBomService.updateWorkorderBom(reqVO);
        // 校验是否更新正确
        WorkorderBomDO workorderBom = workorderBomMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, workorderBom);
    }

    @Test
    public void testUpdateWorkorderBom_notExists() {
        // 准备参数
        WorkorderBomUpdateReqVO reqVO = randomPojo(WorkorderBomUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> workorderBomService.updateWorkorderBom(reqVO), WORKORDER_BOM_NOT_EXISTS);
    }

    @Test
    public void testDeleteWorkorderBom_success() {
        // mock 数据
        WorkorderBomDO dbWorkorderBom = randomPojo(WorkorderBomDO.class);
        workorderBomMapper.insert(dbWorkorderBom);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbWorkorderBom.getId();

        // 调用
        workorderBomService.deleteWorkorderBom(id);
       // 校验数据不存在了
       assertNull(workorderBomMapper.selectById(id));
    }

    @Test
    public void testDeleteWorkorderBom_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> workorderBomService.deleteWorkorderBom(id), WORKORDER_BOM_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetWorkorderBomPage() {
       // mock 数据
       WorkorderBomDO dbWorkorderBom = randomPojo(WorkorderBomDO.class, o -> { // 等会查询到
           o.setWorkorderId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setItemSpc(null);
           o.setUnitOfMeasure(null);
           o.setItemOrProduct(null);
           o.setQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       workorderBomMapper.insert(dbWorkorderBom);
       // 测试 workorderId 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setWorkorderId(null)));
       // 测试 itemId 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setItemName(null)));
       // 测试 itemSpc 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setItemSpc(null)));
       // 测试 unitOfMeasure 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setUnitOfMeasure(null)));
       // 测试 itemOrProduct 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setItemOrProduct(null)));
       // 测试 quantity 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setQuantity(null)));
       // 测试 remark 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setCreateTime(null)));
       // 准备参数
       WorkorderBomPageReqVO reqVO = new WorkorderBomPageReqVO();
       reqVO.setWorkorderId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setItemSpc(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setItemOrProduct(null);
       reqVO.setQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<WorkorderBomDO> pageResult = workorderBomService.getWorkorderBomPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbWorkorderBom, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetWorkorderBomList() {
       // mock 数据
       WorkorderBomDO dbWorkorderBom = randomPojo(WorkorderBomDO.class, o -> { // 等会查询到
           o.setWorkorderId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setItemSpc(null);
           o.setUnitOfMeasure(null);
           o.setItemOrProduct(null);
           o.setQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       workorderBomMapper.insert(dbWorkorderBom);
       // 测试 workorderId 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setWorkorderId(null)));
       // 测试 itemId 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setItemName(null)));
       // 测试 itemSpc 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setItemSpc(null)));
       // 测试 unitOfMeasure 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setUnitOfMeasure(null)));
       // 测试 itemOrProduct 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setItemOrProduct(null)));
       // 测试 quantity 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setQuantity(null)));
       // 测试 remark 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       workorderBomMapper.insert(cloneIgnoreId(dbWorkorderBom, o -> o.setCreateTime(null)));
       // 准备参数
       WorkorderBomExportReqVO reqVO = new WorkorderBomExportReqVO();
       reqVO.setWorkorderId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setItemSpc(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setItemOrProduct(null);
       reqVO.setQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<WorkorderBomDO> list = workorderBomService.getWorkorderBomList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbWorkorderBom, list.get(0));
    }

}
