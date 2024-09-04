package com.dofast.module.cal.service.planteam;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cal.controller.admin.planteam.vo.*;
import com.dofast.module.cal.dal.dataobject.planteam.PlanTeamDO;
import com.dofast.module.cal.dal.mysql.planteam.PlanTeamMapper;
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
 * {@link PlanTeamServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(PlanTeamServiceImpl.class)
public class PlanTeamServiceImplTest extends BaseDbUnitTest {

    @Resource
    private PlanTeamServiceImpl planTeamService;

    @Resource
    private PlanTeamMapper planTeamMapper;

    @Test
    public void testCreatePlanTeam_success() {
        // 准备参数
        PlanTeamCreateReqVO reqVO = randomPojo(PlanTeamCreateReqVO.class);

        // 调用
        Long planTeamId = planTeamService.createPlanTeam(reqVO);
        // 断言
        assertNotNull(planTeamId);
        // 校验记录的属性是否正确
        PlanTeamDO planTeam = planTeamMapper.selectById(planTeamId);
        assertPojoEquals(reqVO, planTeam);
    }

    @Test
    public void testUpdatePlanTeam_success() {
        // mock 数据
        PlanTeamDO dbPlanTeam = randomPojo(PlanTeamDO.class);
        planTeamMapper.insert(dbPlanTeam);// @Sql: 先插入出一条存在的数据
        // 准备参数
        PlanTeamUpdateReqVO reqVO = randomPojo(PlanTeamUpdateReqVO.class, o -> {
            o.setId(dbPlanTeam.getId()); // 设置更新的 ID
        });

        // 调用
        planTeamService.updatePlanTeam(reqVO);
        // 校验是否更新正确
        PlanTeamDO planTeam = planTeamMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, planTeam);
    }

    @Test
    public void testUpdatePlanTeam_notExists() {
        // 准备参数
        PlanTeamUpdateReqVO reqVO = randomPojo(PlanTeamUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> planTeamService.updatePlanTeam(reqVO), PLAN_TEAM_NOT_EXISTS);
    }

    @Test
    public void testDeletePlanTeam_success() {
        // mock 数据
        PlanTeamDO dbPlanTeam = randomPojo(PlanTeamDO.class);
        planTeamMapper.insert(dbPlanTeam);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbPlanTeam.getId();

        // 调用
        planTeamService.deletePlanTeam(id);
       // 校验数据不存在了
       assertNull(planTeamMapper.selectById(id));
    }

    @Test
    public void testDeletePlanTeam_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> planTeamService.deletePlanTeam(id), PLAN_TEAM_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPlanTeamPage() {
       // mock 数据
       PlanTeamDO dbPlanTeam = randomPojo(PlanTeamDO.class, o -> { // 等会查询到
           o.setPlanId(null);
           o.setTeamId(null);
           o.setTeamCode(null);
           o.setTeamName(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       planTeamMapper.insert(dbPlanTeam);
       // 测试 planId 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setPlanId(null)));
       // 测试 teamId 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setTeamId(null)));
       // 测试 teamCode 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setTeamCode(null)));
       // 测试 teamName 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setTeamName(null)));
       // 测试 remark 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setCreateTime(null)));
       // 准备参数
       PlanTeamPageReqVO reqVO = new PlanTeamPageReqVO();
       reqVO.setPlanId(null);
       reqVO.setTeamId(null);
       reqVO.setTeamCode(null);
       reqVO.setTeamName(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<PlanTeamDO> pageResult = planTeamService.getPlanTeamPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbPlanTeam, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPlanTeamList() {
       // mock 数据
       PlanTeamDO dbPlanTeam = randomPojo(PlanTeamDO.class, o -> { // 等会查询到
           o.setPlanId(null);
           o.setTeamId(null);
           o.setTeamCode(null);
           o.setTeamName(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       planTeamMapper.insert(dbPlanTeam);
       // 测试 planId 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setPlanId(null)));
       // 测试 teamId 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setTeamId(null)));
       // 测试 teamCode 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setTeamCode(null)));
       // 测试 teamName 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setTeamName(null)));
       // 测试 remark 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       planTeamMapper.insert(cloneIgnoreId(dbPlanTeam, o -> o.setCreateTime(null)));
       // 准备参数
       PlanTeamExportReqVO reqVO = new PlanTeamExportReqVO();
       reqVO.setPlanId(null);
       reqVO.setTeamId(null);
       reqVO.setTeamCode(null);
       reqVO.setTeamName(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<PlanTeamDO> list = planTeamService.getPlanTeamList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbPlanTeam, list.get(0));
    }

}
