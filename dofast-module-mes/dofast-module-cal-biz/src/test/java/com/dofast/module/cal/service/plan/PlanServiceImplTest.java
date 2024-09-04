package com.dofast.module.cal.service.plan;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cal.controller.admin.plan.vo.*;
import com.dofast.module.cal.dal.dataobject.plan.PlanDO;
import com.dofast.module.cal.dal.mysql.plan.PlanMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.cal.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link PlanServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(PlanServiceImpl.class)
public class PlanServiceImplTest extends BaseDbUnitTest {

    @Resource
    private PlanServiceImpl planService;

    @Resource
    private PlanMapper planMapper;

    @Test
    public void testCreatePlan_success() {
        // 准备参数
        PlanCreateReqVO reqVO = randomPojo(PlanCreateReqVO.class);

        // 调用
        Long planId = planService.createPlan(reqVO);
        // 断言
        assertNotNull(planId);
        // 校验记录的属性是否正确
        PlanDO plan = planMapper.selectById(planId);
        assertPojoEquals(reqVO, plan);
    }

    @Test
    public void testUpdatePlan_success() {
        // mock 数据
        PlanDO dbPlan = randomPojo(PlanDO.class);
        planMapper.insert(dbPlan);// @Sql: 先插入出一条存在的数据
        // 准备参数
        PlanUpdateReqVO reqVO = randomPojo(PlanUpdateReqVO.class, o -> {
            o.setId(dbPlan.getId()); // 设置更新的 ID
        });

        // 调用
        planService.updatePlan(reqVO);
        // 校验是否更新正确
        PlanDO plan = planMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, plan);
    }

    @Test
    public void testUpdatePlan_notExists() {
        // 准备参数
        PlanUpdateReqVO reqVO = randomPojo(PlanUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> planService.updatePlan(reqVO), PLAN_NOT_EXISTS);
    }

    @Test
    public void testDeletePlan_success() {
        // mock 数据
        PlanDO dbPlan = randomPojo(PlanDO.class);
        planMapper.insert(dbPlan);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbPlan.getId();

        // 调用
        planService.deletePlan(id);
       // 校验数据不存在了
       assertNull(planMapper.selectById(id));
    }

    @Test
    public void testDeletePlan_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> planService.deletePlan(id), PLAN_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPlanPage() {
       // mock 数据
       PlanDO dbPlan = randomPojo(PlanDO.class, o -> { // 等会查询到
           o.setPlanCode(null);
           o.setPlanName(null);
           o.setCalendarType(null);
           o.setStartDate(null);
           o.setEndDate(null);
           o.setShiftType(null);
           o.setShiftMethod(null);
           o.setShiftCount(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       planMapper.insert(dbPlan);
       // 测试 planCode 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setPlanCode(null)));
       // 测试 planName 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setPlanName(null)));
       // 测试 calendarType 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setCalendarType(null)));
       // 测试 startDate 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setStartDate(null)));
       // 测试 endDate 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setEndDate(null)));
       // 测试 shiftType 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setShiftType(null)));
       // 测试 shiftMethod 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setShiftMethod(null)));
       // 测试 shiftCount 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setShiftCount(null)));
       // 测试 status 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setCreateTime(null)));
       // 准备参数
       PlanPageReqVO reqVO = new PlanPageReqVO();
       reqVO.setPlanCode(null);
       reqVO.setPlanName(null);
       reqVO.setCalendarType(null);
       reqVO.setStartDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEndDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setShiftType(null);
       reqVO.setShiftMethod(null);
       reqVO.setShiftCount(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<PlanDO> pageResult = planService.getPlanPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbPlan, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPlanList() {
       // mock 数据
       PlanDO dbPlan = randomPojo(PlanDO.class, o -> { // 等会查询到
           o.setPlanCode(null);
           o.setPlanName(null);
           o.setCalendarType(null);
           o.setStartDate(null);
           o.setEndDate(null);
           o.setShiftType(null);
           o.setShiftMethod(null);
           o.setShiftCount(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       planMapper.insert(dbPlan);
       // 测试 planCode 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setPlanCode(null)));
       // 测试 planName 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setPlanName(null)));
       // 测试 calendarType 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setCalendarType(null)));
       // 测试 startDate 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setStartDate(null)));
       // 测试 endDate 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setEndDate(null)));
       // 测试 shiftType 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setShiftType(null)));
       // 测试 shiftMethod 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setShiftMethod(null)));
       // 测试 shiftCount 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setShiftCount(null)));
       // 测试 status 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       planMapper.insert(cloneIgnoreId(dbPlan, o -> o.setCreateTime(null)));
       // 准备参数
       PlanExportReqVO reqVO = new PlanExportReqVO();
       reqVO.setPlanCode(null);
       reqVO.setPlanName(null);
       reqVO.setCalendarType(null);
       reqVO.setStartDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEndDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setShiftType(null);
       reqVO.setShiftMethod(null);
       reqVO.setShiftCount(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<PlanDO> list = planService.getPlanList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbPlan, list.get(0));
    }

}
