package com.dofast.module.cmms.service.dvcheckplan;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cmms.controller.admin.dvcheckplan.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvcheckplan.DvCheckPlanDO;
import com.dofast.module.cmms.dal.mysql.dvcheckplan.DvCheckPlanMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.cmms.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link DvCheckPlanServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DvCheckPlanServiceImpl.class)
public class DvCheckPlanServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DvCheckPlanServiceImpl dvCheckPlanService;

    @Resource
    private DvCheckPlanMapper dvCheckPlanMapper;

    @Test
    public void testCreateDvCheckPlan_success() {
        // 准备参数
        DvCheckPlanCreateReqVO reqVO = randomPojo(DvCheckPlanCreateReqVO.class);

        // 调用
        Long dvCheckPlanId = dvCheckPlanService.createDvCheckPlan(reqVO);
        // 断言
        assertNotNull(dvCheckPlanId);
        // 校验记录的属性是否正确
        DvCheckPlanDO dvCheckPlan = dvCheckPlanMapper.selectById(dvCheckPlanId);
        assertPojoEquals(reqVO, dvCheckPlan);
    }

    @Test
    public void testUpdateDvCheckPlan_success() {
        // mock 数据
        DvCheckPlanDO dbDvCheckPlan = randomPojo(DvCheckPlanDO.class);
        dvCheckPlanMapper.insert(dbDvCheckPlan);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DvCheckPlanUpdateReqVO reqVO = randomPojo(DvCheckPlanUpdateReqVO.class, o -> {
            o.setId(dbDvCheckPlan.getId()); // 设置更新的 ID
        });

        // 调用
        dvCheckPlanService.updateDvCheckPlan(reqVO);
        // 校验是否更新正确
        DvCheckPlanDO dvCheckPlan = dvCheckPlanMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, dvCheckPlan);
    }

    @Test
    public void testUpdateDvCheckPlan_notExists() {
        // 准备参数
        DvCheckPlanUpdateReqVO reqVO = randomPojo(DvCheckPlanUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> dvCheckPlanService.updateDvCheckPlan(reqVO), DV_CHECK_PLAN_NOT_EXISTS);
    }

    @Test
    public void testDeleteDvCheckPlan_success() {
        // mock 数据
        DvCheckPlanDO dbDvCheckPlan = randomPojo(DvCheckPlanDO.class);
        dvCheckPlanMapper.insert(dbDvCheckPlan);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDvCheckPlan.getId();

        // 调用
        dvCheckPlanService.deleteDvCheckPlan(id);
       // 校验数据不存在了
       assertNull(dvCheckPlanMapper.selectById(id));
    }

    @Test
    public void testDeleteDvCheckPlan_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> dvCheckPlanService.deleteDvCheckPlan(id), DV_CHECK_PLAN_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvCheckPlanPage() {
       // mock 数据
       DvCheckPlanDO dbDvCheckPlan = randomPojo(DvCheckPlanDO.class, o -> { // 等会查询到
           o.setPlanCode(null);
           o.setPlanName(null);
           o.setPlanType(null);
           o.setStartDate(null);
           o.setEndDate(null);
           o.setCycleType(null);
           o.setCycleCount(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvCheckPlanMapper.insert(dbDvCheckPlan);
       // 测试 planCode 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setPlanCode(null)));
       // 测试 planName 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setPlanName(null)));
       // 测试 planType 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setPlanType(null)));
       // 测试 startDate 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setStartDate(null)));
       // 测试 endDate 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setEndDate(null)));
       // 测试 cycleType 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setCycleType(null)));
       // 测试 cycleCount 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setCycleCount(null)));
       // 测试 status 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setCreateTime(null)));
       // 准备参数
       DvCheckPlanPageReqVO reqVO = new DvCheckPlanPageReqVO();
       reqVO.setPlanCode(null);
       reqVO.setPlanName(null);
       reqVO.setPlanType(null);
       reqVO.setStartDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEndDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCycleType(null);
       reqVO.setCycleCount(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DvCheckPlanDO> pageResult = dvCheckPlanService.getDvCheckPlanPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDvCheckPlan, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvCheckPlanList() {
       // mock 数据
       DvCheckPlanDO dbDvCheckPlan = randomPojo(DvCheckPlanDO.class, o -> { // 等会查询到
           o.setPlanCode(null);
           o.setPlanName(null);
           o.setPlanType(null);
           o.setStartDate(null);
           o.setEndDate(null);
           o.setCycleType(null);
           o.setCycleCount(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvCheckPlanMapper.insert(dbDvCheckPlan);
       // 测试 planCode 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setPlanCode(null)));
       // 测试 planName 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setPlanName(null)));
       // 测试 planType 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setPlanType(null)));
       // 测试 startDate 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setStartDate(null)));
       // 测试 endDate 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setEndDate(null)));
       // 测试 cycleType 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setCycleType(null)));
       // 测试 cycleCount 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setCycleCount(null)));
       // 测试 status 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvCheckPlanMapper.insert(cloneIgnoreId(dbDvCheckPlan, o -> o.setCreateTime(null)));
       // 准备参数
       DvCheckPlanExportReqVO reqVO = new DvCheckPlanExportReqVO();
       reqVO.setPlanCode(null);
       reqVO.setPlanName(null);
       reqVO.setPlanType(null);
       reqVO.setStartDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEndDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCycleType(null);
       reqVO.setCycleCount(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<DvCheckPlanDO> list = dvCheckPlanService.getDvCheckPlanList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDvCheckPlan, list.get(0));
    }

}
