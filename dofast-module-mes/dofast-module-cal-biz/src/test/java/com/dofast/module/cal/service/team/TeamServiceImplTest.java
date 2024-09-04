package com.dofast.module.cal.service.team;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cal.controller.admin.team.vo.*;
import com.dofast.module.cal.dal.dataobject.team.TeamDO;
import com.dofast.module.cal.dal.mysql.team.TeamMapper;
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
 * {@link TeamServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(TeamServiceImpl.class)
public class TeamServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TeamServiceImpl teamService;

    @Resource
    private TeamMapper teamMapper;

    @Test
    public void testCreateTeam_success() {
        // 准备参数
        TeamCreateReqVO reqVO = randomPojo(TeamCreateReqVO.class);

        // 调用
        Long teamId = teamService.createTeam(reqVO);
        // 断言
        assertNotNull(teamId);
        // 校验记录的属性是否正确
        TeamDO team = teamMapper.selectById(teamId);
        assertPojoEquals(reqVO, team);
    }

    @Test
    public void testUpdateTeam_success() {
        // mock 数据
        TeamDO dbTeam = randomPojo(TeamDO.class);
        teamMapper.insert(dbTeam);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TeamUpdateReqVO reqVO = randomPojo(TeamUpdateReqVO.class, o -> {
            o.setId(dbTeam.getId()); // 设置更新的 ID
        });

        // 调用
        teamService.updateTeam(reqVO);
        // 校验是否更新正确
        TeamDO team = teamMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, team);
    }

    @Test
    public void testUpdateTeam_notExists() {
        // 准备参数
        TeamUpdateReqVO reqVO = randomPojo(TeamUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> teamService.updateTeam(reqVO), TEAM_NOT_EXISTS);
    }

    @Test
    public void testDeleteTeam_success() {
        // mock 数据
        TeamDO dbTeam = randomPojo(TeamDO.class);
        teamMapper.insert(dbTeam);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTeam.getId();

        // 调用
        teamService.deleteTeam(id);
       // 校验数据不存在了
       assertNull(teamMapper.selectById(id));
    }

    @Test
    public void testDeleteTeam_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> teamService.deleteTeam(id), TEAM_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTeamPage() {
       // mock 数据
       TeamDO dbTeam = randomPojo(TeamDO.class, o -> { // 等会查询到
           o.setTeamCode(null);
           o.setTeamName(null);
           o.setCalendarType(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       teamMapper.insert(dbTeam);
       // 测试 teamCode 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setTeamCode(null)));
       // 测试 teamName 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setTeamName(null)));
       // 测试 calendarType 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setCalendarType(null)));
       // 测试 remark 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setCreateTime(null)));
       // 准备参数
       TeamPageReqVO reqVO = new TeamPageReqVO();
       reqVO.setTeamCode(null);
       reqVO.setTeamName(null);
       reqVO.setCalendarType(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<TeamDO> pageResult = teamService.getTeamPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTeam, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTeamList() {
       // mock 数据
       TeamDO dbTeam = randomPojo(TeamDO.class, o -> { // 等会查询到
           o.setTeamCode(null);
           o.setTeamName(null);
           o.setCalendarType(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       teamMapper.insert(dbTeam);
       // 测试 teamCode 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setTeamCode(null)));
       // 测试 teamName 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setTeamName(null)));
       // 测试 calendarType 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setCalendarType(null)));
       // 测试 remark 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       teamMapper.insert(cloneIgnoreId(dbTeam, o -> o.setCreateTime(null)));
       // 准备参数
       TeamExportReqVO reqVO = new TeamExportReqVO();
       reqVO.setTeamCode(null);
       reqVO.setTeamName(null);
       reqVO.setCalendarType(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<TeamDO> list = teamService.getTeamList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTeam, list.get(0));
    }

}
