package com.dofast.module.cal.convert.team;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cal.controller.admin.team.vo.*;
import com.dofast.module.cal.dal.dataobject.team.TeamDO;

/**
 * 班组 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface TeamConvert {

    TeamConvert INSTANCE = Mappers.getMapper(TeamConvert.class);

    TeamDO convert(TeamCreateReqVO bean);

    TeamDO convert(TeamUpdateReqVO bean);

    TeamRespVO convert(TeamDO bean);

    List<TeamRespVO> convertList(List<TeamDO> list);

    PageResult<TeamRespVO> convertPage(PageResult<TeamDO> page);

    List<TeamExcelVO> convertList02(List<TeamDO> list);

}
