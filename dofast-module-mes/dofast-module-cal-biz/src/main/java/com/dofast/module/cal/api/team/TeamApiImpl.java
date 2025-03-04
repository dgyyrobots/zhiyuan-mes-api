package com.dofast.module.cal.api.team;

import com.dofast.module.cal.api.team.dto.TeamDTO;
import com.dofast.module.cal.convert.team.TeamConvert;
import com.dofast.module.cal.dal.dataobject.team.TeamDO;
import com.dofast.module.cal.service.team.TeamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeamApiImpl implements TeamApi {

    @Resource
    private TeamService teamService;

    @Override
    public TeamDTO getTeamById(Long teamId) {
       TeamDO teamDO = teamService.getTeam(teamId);
        return TeamConvert.INSTANCE.convert01(teamDO);
    }

    @Override
    public TeamDTO getTeamByCode(String teamCode) {
        TeamDO teamDO = teamService.getTeam(teamCode);
        return TeamConvert.INSTANCE.convert01(teamDO);
    }
}
