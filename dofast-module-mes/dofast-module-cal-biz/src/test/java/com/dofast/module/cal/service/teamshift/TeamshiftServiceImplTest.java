package com.dofast.module.cal.service.teamshift;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cal.controller.admin.teamshift.vo.*;
import com.dofast.module.cal.dal.dataobject.teamshift.TeamshiftDO;
import com.dofast.module.cal.dal.mysql.teamshift.TeamshiftMapper;
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
 * {@link TeamshiftServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(TeamshiftServiceImpl.class)
public class TeamshiftServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TeamshiftServiceImpl teamshiftService;

    @Resource
    private TeamshiftMapper teamshiftMapper;

    @Test
    public void testCreateTeamshift_success() {
        // 准备参数
        TeamshiftCreateReqVO reqVO = randomPojo(TeamshiftCreateReqVO.class);

        // 调用
        Long teamshiftId = teamshiftService.createTeamshift(reqVO);
        // 断言
        assertNotNull(teamshiftId);
        // 校验记录的属性是否正确
        TeamshiftDO teamshift = teamshiftMapper.selectById(teamshiftId);
        assertPojoEquals(reqVO, teamshift);
    }

    @Test
    public void testUpdateTeamshift_success() {
        // mock 数据
        TeamshiftDO dbTeamshift = randomPojo(TeamshiftDO.class);
        teamshiftMapper.insert(dbTeamshift);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TeamshiftUpdateReqVO reqVO = randomPojo(TeamshiftUpdateReqVO.class, o -> {
            o.setId(dbTeamshift.getId()); // 设置更新的 ID
        });

        // 调用
        teamshiftService.updateTeamshift(reqVO);
        // 校验是否更新正确
        TeamshiftDO teamshift = teamshiftMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, teamshift);
    }

    @Test
    public void testUpdateTeamshift_notExists() {
        // 准备参数
        TeamshiftUpdateReqVO reqVO = randomPojo(TeamshiftUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> teamshiftService.updateTeamshift(reqVO), TEAMSHIFT_NOT_EXISTS);
    }

    @Test
    public void testDeleteTeamshift_success() {
        // mock 数据
        TeamshiftDO dbTeamshift = randomPojo(TeamshiftDO.class);
        teamshiftMapper.insert(dbTeamshift);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTeamshift.getId();

        // 调用
        teamshiftService.deleteTeamshift(id);
       // 校验数据不存在了
       assertNull(teamshiftMapper.selectById(id));
    }

    @Test
    public void testDeleteTeamshift_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> teamshiftService.deleteTeamshift(id), TEAMSHIFT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTeamshiftPage() {
       // mock 数据
       TeamshiftDO dbTeamshift = randomPojo(TeamshiftDO.class, o -> { // 等会查询到
           o.setTheDay(null);
           o.setTeamId(null);
           o.setTeamName(null);
           o.setShiftId(null);
           o.setShiftName(null);
           o.setOrderNum(null);
           o.setPlanId(null);
           o.setCalendarType(null);
           o.setShiftType(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       teamshiftMapper.insert(dbTeamshift);
       // 测试 theDay 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setTheDay(null)));
       // 测试 teamId 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setTeamId(null)));
       // 测试 teamName 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setTeamName(null)));
       // 测试 shiftId 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setShiftId(null)));
       // 测试 shiftName 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setShiftName(null)));
       // 测试 orderNum 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setOrderNum(null)));
       // 测试 planId 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setPlanId(null)));
       // 测试 calendarType 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setCalendarType(null)));
       // 测试 shiftType 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setShiftType(null)));
       // 测试 remark 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setCreateTime(null)));
       // 准备参数
       TeamshiftPageReqVO reqVO = new TeamshiftPageReqVO();
       reqVO.setTheDay(null);
       reqVO.setTeamId(null);
       reqVO.setTeamName(null);
       reqVO.setShiftId(null);
       reqVO.setShiftName(null);
       reqVO.setOrderNum(null);
       reqVO.setPlanId(null);
       reqVO.setCalendarType(null);
       reqVO.setShiftType(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<TeamshiftDO> pageResult = teamshiftService.getTeamshiftPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTeamshift, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTeamshiftList() {
       // mock 数据
       TeamshiftDO dbTeamshift = randomPojo(TeamshiftDO.class, o -> { // 等会查询到
           o.setTheDay(null);
           o.setTeamId(null);
           o.setTeamName(null);
           o.setShiftId(null);
           o.setShiftName(null);
           o.setOrderNum(null);
           o.setPlanId(null);
           o.setCalendarType(null);
           o.setShiftType(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       teamshiftMapper.insert(dbTeamshift);
       // 测试 theDay 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setTheDay(null)));
       // 测试 teamId 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setTeamId(null)));
       // 测试 teamName 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setTeamName(null)));
       // 测试 shiftId 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setShiftId(null)));
       // 测试 shiftName 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setShiftName(null)));
       // 测试 orderNum 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setOrderNum(null)));
       // 测试 planId 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setPlanId(null)));
       // 测试 calendarType 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setCalendarType(null)));
       // 测试 shiftType 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setShiftType(null)));
       // 测试 remark 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       teamshiftMapper.insert(cloneIgnoreId(dbTeamshift, o -> o.setCreateTime(null)));
       // 准备参数
       TeamshiftExportReqVO reqVO = new TeamshiftExportReqVO();
       reqVO.setTheDay(null);
       reqVO.setTeamId(null);
       reqVO.setTeamName(null);
       reqVO.setShiftId(null);
       reqVO.setShiftName(null);
       reqVO.setOrderNum(null);
       reqVO.setPlanId(null);
       reqVO.setCalendarType(null);
       reqVO.setShiftType(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<TeamshiftDO> list = teamshiftService.getTeamshiftList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTeamshift, list.get(0));
    }

}
