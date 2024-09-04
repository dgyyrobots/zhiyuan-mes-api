package com.dofast.module.cal.service.team;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.cal.controller.admin.team.vo.*;
import com.dofast.module.cal.dal.dataobject.team.TeamDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.cal.convert.team.TeamConvert;
import com.dofast.module.cal.dal.mysql.team.TeamMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.cal.enums.ErrorCodeConstants.*;

/**
 * 班组 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class TeamServiceImpl implements TeamService {

    @Resource
    private TeamMapper teamMapper;

    @Override
    public Long createTeam(TeamCreateReqVO createReqVO) {
        // 插入
        TeamDO team = TeamConvert.INSTANCE.convert(createReqVO);
        teamMapper.insert(team);
        // 返回
        return team.getId();
    }

    @Override
    public void updateTeam(TeamUpdateReqVO updateReqVO) {
        // 校验存在
        validateTeamExists(updateReqVO.getId());
        // 更新
        TeamDO updateObj = TeamConvert.INSTANCE.convert(updateReqVO);
        teamMapper.updateById(updateObj);
    }

    @Override
    public void deleteTeam(Long id) {
        // 校验存在
        validateTeamExists(id);
        // 删除
        teamMapper.deleteById(id);
    }

    private void validateTeamExists(Long id) {
        if (teamMapper.selectById(id) == null) {
            throw exception(TEAM_NOT_EXISTS);
        }
    }

    @Override
    public TeamDO getTeam(Long id) {
        return teamMapper.selectById(id);
    }

    @Override
    public List<TeamDO> getTeamList(Collection<Long> ids) {
        if (ids != null) {
            return teamMapper.selectBatchIds(ids);
        } else {
            return teamMapper.selectList();
        }
    }

    @Override
    public PageResult<TeamDO> getTeamPage(TeamPageReqVO pageReqVO) {
        return teamMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TeamDO> getTeamList(TeamExportReqVO exportReqVO) {
        return teamMapper.selectList(exportReqVO);
    }

}
