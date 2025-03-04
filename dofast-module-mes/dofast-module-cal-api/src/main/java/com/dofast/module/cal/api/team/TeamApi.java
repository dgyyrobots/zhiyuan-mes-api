package com.dofast.module.cal.api.team;

import com.dofast.module.cal.api.team.dto.TeamDTO;

public interface TeamApi {

    public TeamDTO getTeamById(Long teamId);

    public TeamDTO getTeamByCode(String teamCode);

}
