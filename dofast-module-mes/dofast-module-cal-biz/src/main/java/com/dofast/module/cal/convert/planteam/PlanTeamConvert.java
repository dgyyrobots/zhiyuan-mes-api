package com.dofast.module.cal.convert.planteam;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cal.controller.admin.planteam.vo.*;
import com.dofast.module.cal.dal.dataobject.planteam.PlanTeamDO;

/**
 * 计划班组 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface PlanTeamConvert {

    PlanTeamConvert INSTANCE = Mappers.getMapper(PlanTeamConvert.class);

    PlanTeamDO convert(PlanTeamCreateReqVO bean);

    PlanTeamDO convert(PlanTeamUpdateReqVO bean);

    PlanTeamRespVO convert(PlanTeamDO bean);

    List<PlanTeamRespVO> convertList(List<PlanTeamDO> list);

    PageResult<PlanTeamRespVO> convertPage(PageResult<PlanTeamDO> page);

    List<PlanTeamExcelVO> convertList02(List<PlanTeamDO> list);

}
