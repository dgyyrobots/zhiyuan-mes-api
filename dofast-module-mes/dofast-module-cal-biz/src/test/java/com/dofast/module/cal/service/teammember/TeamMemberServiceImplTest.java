package com.dofast.module.cal.service.teammember;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cal.controller.admin.teammember.vo.*;
import com.dofast.module.cal.dal.dataobject.teammember.TeamMemberDO;
import com.dofast.module.cal.dal.mysql.teammember.TeamMemberMapper;
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
 * {@link TeamMemberServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(TeamMemberServiceImpl.class)
public class TeamMemberServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TeamMemberServiceImpl teamMemberService;

    @Resource
    private TeamMemberMapper teamMemberMapper;

    @Test
    public void testCreateTeamMember_success() {
        // 准备参数
        TeamMemberCreateReqVO reqVO = randomPojo(TeamMemberCreateReqVO.class);

        // 调用
        Long teamMemberId = teamMemberService.createTeamMember(reqVO);
        // 断言
        assertNotNull(teamMemberId);
        // 校验记录的属性是否正确
        TeamMemberDO teamMember = teamMemberMapper.selectById(teamMemberId);
        assertPojoEquals(reqVO, teamMember);
    }

    @Test
    public void testUpdateTeamMember_success() {
        // mock 数据
        TeamMemberDO dbTeamMember = randomPojo(TeamMemberDO.class);
        teamMemberMapper.insert(dbTeamMember);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TeamMemberUpdateReqVO reqVO = randomPojo(TeamMemberUpdateReqVO.class, o -> {
            o.setId(dbTeamMember.getId()); // 设置更新的 ID
        });

        // 调用
        teamMemberService.updateTeamMember(reqVO);
        // 校验是否更新正确
        TeamMemberDO teamMember = teamMemberMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, teamMember);
    }

    @Test
    public void testUpdateTeamMember_notExists() {
        // 准备参数
        TeamMemberUpdateReqVO reqVO = randomPojo(TeamMemberUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> teamMemberService.updateTeamMember(reqVO), TEAM_MEMBER_NOT_EXISTS);
    }

    @Test
    public void testDeleteTeamMember_success() {
        // mock 数据
        TeamMemberDO dbTeamMember = randomPojo(TeamMemberDO.class);
        teamMemberMapper.insert(dbTeamMember);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTeamMember.getId();

        // 调用
        teamMemberService.deleteTeamMember(id);
       // 校验数据不存在了
       assertNull(teamMemberMapper.selectById(id));
    }

    @Test
    public void testDeleteTeamMember_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> teamMemberService.deleteTeamMember(id), TEAM_MEMBER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTeamMemberPage() {
       // mock 数据
       TeamMemberDO dbTeamMember = randomPojo(TeamMemberDO.class, o -> { // 等会查询到
           o.setTeamId(null);
           o.setUserId(null);
           o.setUserName(null);
           o.setNickName(null);
           o.setTel(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       teamMemberMapper.insert(dbTeamMember);
       // 测试 teamId 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setTeamId(null)));
       // 测试 userId 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setUserId(null)));
       // 测试 userName 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setUserName(null)));
       // 测试 nickName 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setNickName(null)));
       // 测试 tel 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setTel(null)));
       // 测试 remark 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setCreateTime(null)));
       // 准备参数
       TeamMemberPageReqVO reqVO = new TeamMemberPageReqVO();
       reqVO.setTeamId(null);
       reqVO.setUserId(null);
       reqVO.setUserName(null);
       reqVO.setNickName(null);
       reqVO.setTel(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<TeamMemberDO> pageResult = teamMemberService.getTeamMemberPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTeamMember, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTeamMemberList() {
       // mock 数据
       TeamMemberDO dbTeamMember = randomPojo(TeamMemberDO.class, o -> { // 等会查询到
           o.setTeamId(null);
           o.setUserId(null);
           o.setUserName(null);
           o.setNickName(null);
           o.setTel(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       teamMemberMapper.insert(dbTeamMember);
       // 测试 teamId 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setTeamId(null)));
       // 测试 userId 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setUserId(null)));
       // 测试 userName 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setUserName(null)));
       // 测试 nickName 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setNickName(null)));
       // 测试 tel 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setTel(null)));
       // 测试 remark 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       teamMemberMapper.insert(cloneIgnoreId(dbTeamMember, o -> o.setCreateTime(null)));
       // 准备参数
       TeamMemberExportReqVO reqVO = new TeamMemberExportReqVO();
       reqVO.setTeamId(null);
       reqVO.setUserId(null);
       reqVO.setUserName(null);
       reqVO.setNickName(null);
       reqVO.setTel(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<TeamMemberDO> list = teamMemberService.getTeamMemberList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTeamMember, list.get(0));
    }

}
