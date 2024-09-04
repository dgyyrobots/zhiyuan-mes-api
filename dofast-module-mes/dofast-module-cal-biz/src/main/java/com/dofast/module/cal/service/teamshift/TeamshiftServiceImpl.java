package com.dofast.module.cal.service.teamshift;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.cal.controller.admin.teamshift.vo.*;
import com.dofast.module.cal.dal.dataobject.teamshift.TeamshiftDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.cal.convert.teamshift.TeamshiftConvert;
import com.dofast.module.cal.dal.mysql.teamshift.TeamshiftMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.cal.enums.ErrorCodeConstants.*;

/**
 * 班组排班 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class TeamshiftServiceImpl implements TeamshiftService {

    @Resource
    private TeamshiftMapper teamshiftMapper;

    @Override
    public Long createTeamshift(TeamshiftCreateReqVO createReqVO) {
        // 插入
        TeamshiftDO teamshift = TeamshiftConvert.INSTANCE.convert(createReqVO);
        teamshiftMapper.insert(teamshift);
        // 返回
        return teamshift.getId();
    }

    @Override
    public void updateTeamshift(TeamshiftUpdateReqVO updateReqVO) {
        // 校验存在
        validateTeamshiftExists(updateReqVO.getId());
        // 更新
        TeamshiftDO updateObj = TeamshiftConvert.INSTANCE.convert(updateReqVO);
        teamshiftMapper.updateById(updateObj);
    }

    @Override
    public void deleteTeamshift(Long id) {
        // 校验存在
        validateTeamshiftExists(id);
        // 删除
        teamshiftMapper.deleteById(id);
    }

    private void validateTeamshiftExists(Long id) {
        if (teamshiftMapper.selectById(id) == null) {
            throw exception(TEAMSHIFT_NOT_EXISTS);
        }
    }

    @Override
    public TeamshiftDO getTeamshift(Long id) {
        return teamshiftMapper.selectById(id);
    }

    @Override
    public List<TeamshiftDO> getTeamshiftList(Collection<Long> ids) {
        return teamshiftMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TeamshiftDO> getTeamshiftPage(TeamshiftPageReqVO pageReqVO) {
        return teamshiftMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TeamshiftDO> getTeamshiftList(TeamshiftExportReqVO exportReqVO) {
        return teamshiftMapper.selectList(exportReqVO);
    }

}
