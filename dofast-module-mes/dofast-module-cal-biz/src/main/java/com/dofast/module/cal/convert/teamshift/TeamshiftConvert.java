package com.dofast.module.cal.convert.teamshift;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cal.controller.admin.teamshift.vo.*;
import com.dofast.module.cal.dal.dataobject.teamshift.TeamshiftDO;

/**
 * 班组排班 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface TeamshiftConvert {

    TeamshiftConvert INSTANCE = Mappers.getMapper(TeamshiftConvert.class);

    TeamshiftDO convert(TeamshiftCreateReqVO bean);

    TeamshiftDO convert(TeamshiftUpdateReqVO bean);

    TeamshiftRespVO convert(TeamshiftDO bean);

    List<TeamshiftRespVO> convertList(List<TeamshiftDO> list);

    PageResult<TeamshiftRespVO> convertPage(PageResult<TeamshiftDO> page);

    List<TeamshiftExcelVO> convertList02(List<TeamshiftDO> list);

}
